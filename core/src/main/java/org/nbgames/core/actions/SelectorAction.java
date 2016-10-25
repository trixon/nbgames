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
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import org.nbgames.core.api.GameProvider;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.awt.Actions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import se.trixon.almond.nbp.NbLog;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
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
public final class SelectorAction implements ActionListener {

    private final JPopupMenu mPopup = new JPopupMenu();

    public SelectorAction() {
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
                    JMenuItem dummyMenuItem = new JMenuItem("No installed games");
                    dummyMenuItem.setEnabled(false);

                    mPopup.add(dummyMenuItem);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Component component = (Component) actionEvent.getSource();

        mPopup.show(component, component.getWidth() - mPopup.getWidth(), component.getHeight());

        int x = component.getLocationOnScreen().x - mPopup.getWidth() / 2 + component.getWidth() / 2;
        int y = component.getLocationOnScreen().y + component.getHeight();

        mPopup.setLocation(x, y);
    }
}
