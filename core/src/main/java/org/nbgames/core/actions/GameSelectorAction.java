/*
 * Copyright 2016 Patrik Karlsson.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nbgames.core.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Collection;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import org.nbgames.core.api.GameProvider;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.awt.Actions;
import org.openide.awt.DropDownButtonFactory;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.Presenter;
import se.trixon.almond.nbp.NbLog;

@ActionID(
        category = "Game",
        id = "org.nbgames.core.actions.GameSelectorAction"
)
@ActionRegistration(
        displayName = "#CTL_NewGameAction", lazy = false
)
@ActionReference(path = "Toolbars/File", position = -90)
@Messages("CTL_NewGameAction=New")
public final class GameSelectorAction extends AbstractAction implements Presenter.Toolbar {

    private final JMenuItem mDummyMenuItem;
    private final JPopupMenu mPopup = new JPopupMenu();

    public GameSelectorAction() {
        mDummyMenuItem = new JMenuItem("No installed games");
        mDummyMenuItem.setEnabled(false);
        mPopup.add(mDummyMenuItem);

        mPopup.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                populate();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Action action = Actions.forID("Game", "org.nbgames.core.actions.NewGameCallbackAction");
        action.actionPerformed(e);
    }

    @Override
    public Component getToolbarPresenter() {
        JButton dropDown = DropDownButtonFactory.createDropDownButton(ImageUtilities.loadImageIcon("org/nbgames/core/res/document-new24.png", false),
                mPopup);

        dropDown.addActionListener(this);
        dropDown.setToolTipText(NbBundle.getMessage(this.getClass(), "CTL_GameNewAction"));

        return dropDown;
    }

    private void populate() {
        NbLog.d(getClass(), "populate beg");
        mPopup.removeAll();
        Collection<? extends GameProvider> gameProviders = Lookup.getDefault().lookupAll(GameProvider.class);
        gameProviders.stream().forEach((gameProvider) -> {
            NbLog.d(getClass(), gameProvider.getName());
            JLabel label = new JLabel(gameProvider.getCategory().getString());
            label.setEnabled(false);
            mPopup.add(label);
            String category = gameProvider.getActionCategory();
            String id = gameProvider.getActionId();
            JMenuItem menuItem = new JMenuItem(Actions.forID(category, id));
            String text = String.format("<html><b>%s</b><br /> <i>- %s</i></html>", gameProvider.getName(), gameProvider.getShortDescription());
            menuItem.setText(text);

            mPopup.add(menuItem);
        });

        if (gameProviders.isEmpty()) {
            mPopup.add(mDummyMenuItem);
        }
        NbLog.d(getClass(), "populate end");
    }
}
