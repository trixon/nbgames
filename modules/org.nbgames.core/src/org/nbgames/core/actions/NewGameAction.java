/*
 * Copyright 2015 Patrik Karlsson.
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
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.awt.DropDownButtonFactory;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.Presenter;

@ActionID(
        category = "File",
        id = "org.nbgames.core.actions.NewGame2Action"
)
@ActionRegistration(
        displayName = "#CTL_NewGameAction", lazy = false
)
@ActionReference(path = "Toolbars/File", position = -90)
@Messages("CTL_NewGameAction=New")
public final class NewGameAction extends AbstractAction implements Presenter.Toolbar {

    private final JPopupMenu mPopup = new JPopupMenu();

    public NewGameAction() {
        mPopup.add(new JMenuItem("dummy"));

        mPopup.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                mPopup.removeAll();
                mPopup.add(new JMenuItem(String.valueOf(System.currentTimeMillis())));
                mPopup.add(new JMenuItem("<html><strong>Yaya</strong><br />Dice game</html>"));
                mPopup.add(new JSeparator());
                mPopup.add(new JMenuItem("<html>Gunu2<br /><i>Guess the number</i></html>"));
            }
        });
    }

    @Override
    public Component getToolbarPresenter() {
        JButton dropdown = DropDownButtonFactory.createDropDownButton(ImageUtilities.loadImageIcon("org/nbgames/core/res/document-new24.png", false),
                mPopup);

        dropdown.addActionListener(this);
        dropdown.setToolTipText(NbBundle.getMessage(this.getClass(), "CTL_GameNewAction"));
        return dropdown;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("start new game in active game");
    }
}
