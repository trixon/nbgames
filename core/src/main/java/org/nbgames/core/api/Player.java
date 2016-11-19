/* 
 * Copyright 2016 Patrik Karlsson.
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
package org.nbgames.core.api;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class Player implements Comparable<Player> {

    private Handedness mHandedness = Handedness.RIGHT;
    private long mId = System.currentTimeMillis();
    private String mName = "";

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
