package org.nbgames.branding;

import java.util.ResourceBundle;
import org.openide.util.ImageUtilities;
import se.trixon.almond.about.About;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class AboutInitializer {

    public static void init() {
        About.setAboutBundle(ResourceBundle.getBundle("org/nbgames/branding/about"));
        About.setLicenseBundle(ResourceBundle.getBundle("org/nbgames/branding/license"));
        About.setImageIcon(ImageUtilities.loadImageIcon("org/nbgames/branding/nbgames.png", false));
    }
}
