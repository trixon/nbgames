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
package org.nbgames.core.options;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Patrik Karlsson
 */
public class CategoryPanel extends JTabbedPane {

    /**
     * Creates new form CategoryPanel
     */
    public CategoryPanel() {
    }

    void addTab(String title, JPanel panel) {
        add(title, panel);

    }

    void setTitle(String title) {
        try {
            setTitleAt(0, String.format("<html><b>%s</b></html>", title));
        } catch (Exception e) {
        }
    }

    void setSelectedTab(String tabName) {
        for (int i = 0; i < getTabCount(); i++) {
            if (getTitleAt(i).equalsIgnoreCase(tabName)) {
                setSelectedIndex(i);
                break;
            }
        }
    }
}
