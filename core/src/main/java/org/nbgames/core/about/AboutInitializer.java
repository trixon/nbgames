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
package org.nbgames.core.about;

import java.util.ResourceBundle;
import org.openide.util.ImageUtilities;
import se.trixon.almond.nbp.about.AboutAction;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class AboutInitializer {

    public static void init() {
        AboutAction.setAboutBundle(ResourceBundle.getBundle("org/nbgames/core/about/about"));
        AboutAction.setLicenseBundle(ResourceBundle.getBundle("org/nbgames/core/about/license"));
        AboutAction.setImageIcon(ImageUtilities.loadImageIcon("org/nbgames/core/about/nbgames.png", false));
    }
}
