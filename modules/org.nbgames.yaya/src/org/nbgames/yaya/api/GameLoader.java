package org.nbgames.yaya.api;

import java.io.InputStream;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public interface GameLoader {

    String getId();

    InputStream getInputStream();
}
