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
package org.nbgames.core.presenter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import org.nbgames.core.NbGamesTopComponent;
import org.openide.windows.WindowManager;
import se.trixon.almond.nbp.Almond;
import se.trixon.almond.util.AlmondOptions;
import se.trixon.almond.util.icons.IconColor;

/**
 *
 * @author Patrik Karlsson
 */
public abstract class ContainerPanel extends JPanel {

    protected final IconColor mIconColor = AlmondOptions.getInstance().getIconColor();
    protected final int mIconSize = (int) (Almond.ICON_LARGE / 1.5);
    private final NbGamesTopComponent mTopComponent = (NbGamesTopComponent) WindowManager.getDefault().findTopComponent("NbGamesTopComponent");
    private JLabel mTitleLabel;
    private JPanel mPanel;

    protected void goBack() {
        mTopComponent.showPrevious();
    }

    public void setTitleLabel(JLabel titleLabel) {
        mTitleLabel = titleLabel;
    }

    public void setTitle(String title) {
        mTitleLabel.setText(title);
    }

    public void setPanel(JPanel panel) {
        mPanel = panel;
    }

}
