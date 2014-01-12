package org.nbgames.core;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class Player implements Comparable<Player> {

    private Handedness mHandedness = Handedness.RIGHT;
    private long mId;
    private String mName;

    public Player() {
    }

    public Player(long id, String name, Handedness handedness) {
        mId = id;
        mName = name;
        mHandedness = handedness;
    }

    @Override
    public int compareTo(Player o) {
        return mName.compareTo(o.getName());
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

    @Override
    public String toString() {
        return mName;
    }

    public enum Handedness {

        LEFT, RIGHT;
    }
}
