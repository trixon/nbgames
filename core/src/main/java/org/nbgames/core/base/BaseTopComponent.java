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
package org.nbgames.core.base;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import org.nbgames.core.GlobalOptions;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public abstract class BaseTopComponent extends TopComponent {

    public BaseTopComponent() {
    }

    public BaseTopComponent(Lookup lookup) {
        super(lookup);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color upperColor = GlobalOptions.INSTANCE.getColor(GlobalOptions.ColorItem.WINDOW_UPPER);
        Color lowerColor = GlobalOptions.INSTANCE.getColor(GlobalOptions.ColorItem.WINDOW_LOWER);
        GradientPaint gp = new GradientPaint(0, 0, upperColor, 0, h, lowerColor);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}
