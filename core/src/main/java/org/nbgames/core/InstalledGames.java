/* 
 * Copyright 2018 Patrik Karlström.
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
package org.nbgames.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeMap;
import org.nbgames.core.api.GameCategory;
import org.nbgames.core.api.GameController;
import org.openide.util.Lookup;

/**
 *
 * @author Patrik Karlström
 */
public class InstalledGames {

    private final TreeMap<String, ArrayList<GameController>> mGameControllersPerCategory = new TreeMap<>();

    public static InstalledGames getInstance() {
        return Holder.INSTANCE;
    }

    private InstalledGames() {
    }

    public TreeMap<String, ArrayList<GameController>> getGameControllersPerCategory() {
        mGameControllersPerCategory.clear();
        sortProviders();

        return mGameControllersPerCategory;
    }

    public ArrayList<GameController> getGameControllers() {
        ArrayList<GameController> controllers = new ArrayList<>();
        getGameControllersPerCategory().entrySet().forEach((category) -> {
            category.getValue().forEach((controller) -> {
                controllers.add(controller);
            });
        });

        return controllers;
    }

    private void sortProviders() {
        for (GameCategory category : GameCategory.values()) {
            mGameControllersPerCategory.put(category.getString(), new ArrayList<>());
        }

        Collection<? extends GameController> gameControllers = Lookup.getDefault().lookupAll(GameController.class);
        gameControllers.stream().forEach((gameController) -> {
            mGameControllersPerCategory.get(gameController.getCategory().getString()).add(gameController);
        });

        HashSet<String> emptyKeys = new HashSet<>();
        mGameControllersPerCategory.entrySet().forEach((category) -> {
            ArrayList<GameController> controllers = category.getValue();

            if (controllers.isEmpty()) {
                emptyKeys.add(category.getKey());
            } else {
                Collections.sort(controllers, (GameController o1, GameController o2) -> o1.getName().compareTo(o2.getName()));
            }
        });

        emptyKeys.forEach((emptyKey) -> {
            mGameControllersPerCategory.remove(emptyKey);
        });
    }

    private static class Holder {

        private static final InstalledGames INSTANCE = new InstalledGames();
    }
}
