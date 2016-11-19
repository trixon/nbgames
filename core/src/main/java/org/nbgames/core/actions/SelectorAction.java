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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import org.nbgames.core.api.DictNbg;
import org.nbgames.core.api.GameCategory;
import org.nbgames.core.api.GameController;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.awt.Actions;
import org.openide.util.Lookup;
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

    private TreeMap<String, ArrayList<GameController>> mGameControllersPerCategory = new TreeMap<>();
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
                sortProviders();
                mPopup.removeAll();

                for (Map.Entry<String, ArrayList<GameController>> category : mGameControllersPerCategory.entrySet()) {

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
        Component component = (Component) actionEvent.getSource();
        component = getTopComponent().getSelectorButton();

        mPopup.show(component, component.getWidth() - mPopup.getWidth(), component.getHeight());

        int x = component.getLocationOnScreen().x - mPopup.getWidth() / 2 + component.getWidth() / 2;
        int y = component.getLocationOnScreen().y + component.getHeight();

        mPopup.setLocation(x, y);
    }

    private void sortProviders() {
        for (GameCategory category : GameCategory.values()) {
            mGameControllersPerCategory.put(category.getString(), new ArrayList<>());
        }

        Collection<? extends GameController> gameControllers = Lookup.getDefault().lookupAll(GameController.class);
        gameControllers.stream().forEach((gameController) -> {
            mGameControllersPerCategory.get(gameController.getCategory().getString()).add(gameController);
        });

        HashSet<String> emptyKeys = new HashSet<>();
        for (Map.Entry<String, ArrayList<GameController>> category : mGameControllersPerCategory.entrySet()) {
            ArrayList<GameController> controllers = category.getValue();

            if (controllers.isEmpty()) {
                emptyKeys.add(category.getKey());
            } else {
                Collections.sort(controllers, new Comparator<GameController>() {
                    @Override
                    public int compare(GameController o1, GameController o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
            }
        }

        for (String emptyKey : emptyKeys) {
            mGameControllersPerCategory.remove(emptyKey);
        }
    }
}
