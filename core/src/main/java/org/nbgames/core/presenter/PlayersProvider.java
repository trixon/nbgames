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
package org.nbgames.core.presenter;

import javax.swing.JPanel;
import org.nbgames.core.api.PresenterProvider;

/**
 *
 * @author Patrik Karlsson
 */
public class PlayersProvider implements PresenterProvider {

    private final HomeProvider mHomeProvider = HomeProvider.getInstance();
    private final PlayersPanel mPanel = new PlayersPanel();

    public static PlayersProvider getInstance() {
        return Holder.INSTANCE;
    }

    private PlayersProvider() {
    }

    @Override
    public String getCopyright() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCredit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getHelp() {
        return mHomeProvider.getHelp();
    }

    @Override
    public String getId() {
        return getClass().getName();
    }

    @Override
    public String getLicense() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        return mHomeProvider.getName();
    }

    @Override
    public JPanel getOptionsPanel() {
        return mHomeProvider.getOptionsPanel();
    }

    @Override
    public JPanel getPanel() {
        return mPanel;
    }

    @Override
    public String getShortDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getVersion() {
        return mHomeProvider.getVersion();
    }

    private static class Holder {

        private static final PlayersProvider INSTANCE = new PlayersProvider();
    }
}