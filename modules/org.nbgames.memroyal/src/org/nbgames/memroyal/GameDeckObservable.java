package org.nbgames.memroyal;

import java.util.Observable;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class GameDeckObservable extends Observable {

    public void notify(GameCardEvent gameCardEvent) {
        setChanged();
        notifyObservers(gameCardEvent);
    }

    public enum GameCardEvent {

        FLIP;
    }
}
