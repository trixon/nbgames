package org.nbgames.yaya.scorecard;

import java.util.Observable;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class ScoreCardObservable extends Observable {

    public void notify(ScoreCardEvent scoreCardEvent) {
        setChanged();
        notifyObservers(scoreCardEvent);
    }

    public enum ScoreCardEvent {

        GAME_OVER, REGISTER, UNDO
    }
}
