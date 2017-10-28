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
package org.nbgames.core.api;

import java.awt.image.BufferedImage;
import java.util.Locale;
import java.util.ResourceBundle;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import se.trixon.almond.nbp.Monitor;
import se.trixon.almond.util.AlmondOptions;
import se.trixon.almond.util.SystemHelper;

/**
 *
 * @author Patrik Karlsson
 */
public class NbGames {

    public static final String LOG_TITLE = "core";

    public static void errln(String name, String message) {
        new Monitor(name, false, true).errln(message);
    }

    public static AlmondOptions getAlmondOptions() {
        return AlmondOptions.getInstance();
    }

    public static ResourceBundle getBundle() {
        return NbBundle.getBundle(NbGames.class);
    }

    public static BufferedImage getImage(Class c, String imagePath) {
        return (BufferedImage) ImageUtilities.loadImage(SystemHelper.getPackageAsPath(c) + imagePath);
    }

    public static BufferedImage getImage(String imagePath) {
        return getImage(NbGames.class, imagePath);
    }

    public static String getLanguageSuffix() {
        return "-" + Locale.getDefault().getLanguage();
    }

    public static void outln(String name, String message) {
        new Monitor(name, false, true).outln(message);
    }
}
