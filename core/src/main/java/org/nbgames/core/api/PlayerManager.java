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

import java.util.Collections;
import java.util.LinkedList;
import java.util.prefs.Preferences;
import javax.swing.DefaultListModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.openide.util.NbPreferences;

/**
 *
 * @author Patrik Karlsson
 */
public enum PlayerManager {

    INSTANCE;
    private static final String KEY_PLAYERS = "players";
    private static final String KEY_PLAYER_HANDEDNESS = "handedness";
    private static final String KEY_PLAYER_ID = "id";
    private static final String KEY_PLAYER_NAME = "name";
    private final LinkedList<Player> mPlayers = new LinkedList<Player>();
    private final Preferences mPreferences = NbPreferences.forModule(PlayerManager.class).node(KEY_PLAYERS);

    private PlayerManager() {
        load();
    }

    public LinkedList<Player> getPlayers() {
        return mPlayers;
    }

    public Player[] getPlayersArray() {
        return mPlayers.toArray(new Player[0]);
    }

    public Preferences getPreferences() {
        return mPreferences;
    }

    public DefaultListModel load(DefaultListModel model) {
        model.clear();
        load();

        for (Player player : mPlayers) {
            model.addElement(player);
        }

        return model;
    }

    public void store(DefaultListModel model) {
        mPlayers.clear();

        for (Object object : model.toArray()) {
            mPlayers.add((Player) object);
        }

        store();
    }

    private void load() {
        mPlayers.clear();
        String storedArray = mPreferences.get(KEY_PLAYERS, "");

        if (!storedArray.isEmpty()) {
            JSONArray array = (JSONArray) JSONValue.parse(storedArray);

            for (int i = 0; i < array.size(); i++) {
                JSONObject object = (JSONObject) array.get(i);
                Player player = new Player();
                player.setId(JsonHelper.getLong(object, KEY_PLAYER_ID));
                player.setName((String) object.get(KEY_PLAYER_NAME));
                String handedness = (String) object.get(KEY_PLAYER_HANDEDNESS);
                player.setHandedness(Player.Handedness.valueOf(handedness));
                mPlayers.add(player);
            }

            Collections.sort(mPlayers);
        }
    }

    private void store() {
        JSONArray array = new JSONArray();

        for (Player player : mPlayers) {
            JSONObject object = new JSONObject();
            object.put(KEY_PLAYER_ID, player.getId());
            object.put(KEY_PLAYER_NAME, player.getName());
            object.put(KEY_PLAYER_HANDEDNESS, player.getHandedness().name());
            array.add(object);
        }

        mPreferences.put(KEY_PLAYERS, array.toJSONString());
    }
}
