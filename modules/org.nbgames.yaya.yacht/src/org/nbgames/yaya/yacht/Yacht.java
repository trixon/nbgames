package org.nbgames.yaya.yacht;

import java.io.InputStream;
import org.nbgames.yaya.api.GameLoader;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
@ServiceProvider(service = GameLoader.class)
public class Yacht implements GameLoader {

    private final String RESOURCE_PATH = "/org/nbgames/yaya/yacht/game.json";

    @Override
    public String getId() {
        return RESOURCE_PATH;
    }

    @Override
    public InputStream getInputStream() {
        return getClass().getResourceAsStream(RESOURCE_PATH);
    }

}
