/* 
 * Copyright 2017 Patrik Karlsson.
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
import java.awt.IllegalComponentStateException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import org.nbgames.core.InstalledGames;
import org.nbgames.core.api.DictNbg;
import org.nbgames.core.api.GameController;
import org.nbgames.core.api.db.manager.PlayerManager;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.awt.Actions;
import org.openide.util.NbBundle;
import se.trixon.almond.util.Dict;

/**
 *
 * @author Patrik Karlsson
 */
@ActionID(
        category = "Game",
        id = "org.nbgames.core.actions.SelectorAction"
)
@ActionRegistration(
        displayName = "#CTL_SelectorAction"
)
@ActionReferences({
    @ActionReference(path = "Shortcuts", name = "D-G")
})

@NbBundle.Messages("CTL_SelectorAction=Selector")
public final class SelectorAction extends NbGameAction {

    private final InstalledGames mGames;
    private final JPopupMenu mPopup = new JPopupMenu();

    public SelectorAction() {
        mGames = InstalledGames.getInstance();
        mPopup.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                populateDropDown();
            }

            private void populateDropDown() {
                mPopup.removeAll();
                for (Map.Entry<String, ArrayList<GameController>> category : mGames.getGameControllersPerCategory().entrySet()) {
                    JMenuItem label = new JMenuItem(category.getKey());
                    label.setEnabled(false);
                    mPopup.add(label);

                    for (GameController controller : category.getValue()) {
                        JMenuItem menuItem = new JMenuItem();
                        String text = String.format("<html><b>%s</b><br /> <i>- %s</i></html>", controller.getName(), controller.getShortDescription());
                        menuItem.setAction(new AbstractAction(text) {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                getTopComponent().show(controller);
                            }
                        });

                        mPopup.add(menuItem);
                    }
                }

                if (mPopup.getComponentCount() == 0) {
                    JMenuItem dummyMenuItem = new JMenuItem(DictNbg.NO_INSTALLED_GAMES.toString());
                    dummyMenuItem.setEnabled(false);

                    mPopup.add(dummyMenuItem);
                }

                mPopup.add(new JSeparator());
                JMenuItem menuItem = new JMenuItem(Actions.forID("System", "org.netbeans.modules.autoupdate.ui.actions.PluginManagerAction"));
                menuItem.setText(Dict.ADD_REMOVE.toString());
                mPopup.add(menuItem);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (PlayerManager.getInstance().select().isEmpty()) {
            return;
        }

        Component component = (Component) actionEvent.getSource();
        component = getTopComponent().getSelectorButton();

        try {
            mPopup.show(component, component.getWidth() - mPopup.getWidth(), component.getHeight());

            int x = component.getLocationOnScreen().x - mPopup.getWidth() / 2 + component.getWidth() / 2;
            int y = component.getLocationOnScreen().y + component.getHeight();

            mPopup.setLocation(x, y);
        } catch (IllegalComponentStateException e) {
            component = SwingUtilities.getWindowAncestor(component);
            mPopup.show(component, component.getWidth() - mPopup.getWidth(), component.getHeight());

            int x = component.getLocationOnScreen().x - mPopup.getWidth() / 2 + component.getWidth() / 2;
            int y = component.getLocationOnScreen().y + component.getHeight() / 2 - mPopup.getHeight() / 2;

            mPopup.setLocation(x, y);
        }
    }
}
