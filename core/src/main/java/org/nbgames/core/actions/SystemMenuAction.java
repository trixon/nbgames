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
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.awt.Actions;
import org.openide.awt.Mnemonics;
import org.openide.util.NbBundle;
import se.trixon.almond.util.Dict;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
@ActionID(
        category = "System",
        id = "org.nbgames.core.actions.SystemMenuAction"
)
@ActionRegistration(
        displayName = "#CTL_SystemMenuAction"
)
@ActionReferences({
    @ActionReference(path = "Toolbars/System", position = 9999)
    ,
@ActionReference(path = "Shortcuts", name = "D-M")
})

@NbBundle.Messages("CTL_SystemMenuAction=System")
public final class SystemMenuAction implements ActionListener {

    private final JPopupMenu mPopup = new JPopupMenu();

    public SystemMenuAction() {
        JMenu menu;
        add(mPopup, "Window", "org.nbgames.core.StartPageTopComponent");
        add(mPopup, "Window", "org.netbeans.core.windows.actions.ToggleFullScreenAction");
        //add(mPopup, "Window", "org.netbeans.core.windows.actions.ShowEditorOnlyAction");
        mPopup.add(new JSeparator());

        add(mPopup, "System", "org.netbeans.modules.autoupdate.ui.actions.PluginManagerAction");
        add(mPopup, "Window", "org.netbeans.modules.options.OptionsWindowAction");
        //add(mPopup, "Window", "org.netbeans.core.windows.actions.ToolbarsListAction");
        mPopup.add(new JSeparator());

        menu = new JMenu(Dict.SYSTEM.toString());
        add(menu, "Window", "org.netbeans.core.windows.actions.ResetWindowsAction");
        menu.add(new JSeparator());
        //add(menu, "Window", "org.netbeans.core.io.ui.IOWindowAction");
        add(menu, "View", "org.netbeans.core.actions.LogAction");
        mPopup.add(menu);
        mPopup.add(new JSeparator());

        menu = new JMenu(Dict.HELP.toString());
        add(menu, "Help", "org.nbgames.core.actions.HelpAction");
        add(menu, "System", "org.netbeans.modules.autoupdate.ui.actions.CheckForUpdatesAction");
        add(menu, "Help", "org.netbeans.core.actions.AboutAction");
        mPopup.add(menu);
        mPopup.add(new JSeparator());

        add(mPopup, "File", "se.trixon.almond.nbp.actions.QuitAction");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Component component = (Component) actionEvent.getSource();

        mPopup.show(component, component.getWidth() - mPopup.getWidth(), component.getHeight());

        int x = component.getLocationOnScreen().x + component.getWidth() - mPopup.getWidth();
        int y = component.getLocationOnScreen().y + component.getHeight();

        mPopup.setLocation(x, y);
    }

    private void add(JComponent parentMenu, String category, String id) {
        Action a = Actions.forID(category, id);
        JMenuItem menu = new JMenuItem(a);
        Mnemonics.setLocalizedText(menu, a.getValue(Action.NAME).toString());
        parentMenu.add(menu);
    }
}
