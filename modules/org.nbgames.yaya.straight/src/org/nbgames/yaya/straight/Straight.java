package org.nbgames.yaya.straight;

import java.io.InputStream;
import org.nbgames.yaya.api.GameLoader;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
@ServiceProvider(service = GameLoader.class)
public class Straight implements GameLoader {

    private final String RESOURCE_PATH = "/org/nbgames/yaya/straight/game.json";

    @Override
    public String getId() {
        return RESOURCE_PATH;
    }

    @Override
    public InputStream getInputStream() {
        return getClass().getResourceAsStream(RESOURCE_PATH);
    }

}