package org.nbgames.core;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class Player {

    private Handedness mHandedness;
    private long mId;
    private String mName;

    public Player() {
    }

    public Player(long id, String name, Handedness handedness) {
        mId = id;
        mName = name;
        mHandedness = handedness;
    }

    public Handedness getHandedness() {
        return mHandedness;
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setHandedness(Handedness handedness) {
        mHandedness = handedness;
    }

    public void setId(long id) {
        mId = id;
    }

    public void setName(String name) {
        mName = name;
    }

    public enum Handedness {

        LEFT, RIGHT;
    }
}
