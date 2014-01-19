package org.nbgames.branding;

import java.util.ResourceBundle;
import org.openide.util.ImageUtilities;
import se.trixon.almond.about.AboutAction;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class AboutInitializer {

    public static void init() {
        AboutAction.setAboutBundle(ResourceBundle.getBundle("org/nbgames/branding/about"));
        AboutAction.setLicenseBundle(ResourceBundle.getBundle("org/nbgames/branding/license"));
        AboutAction.setImageIcon(ImageUtilities.loadImageIcon("org/nbgames/branding/nbgames.png", false));
    }
}
