/*
 * Copyright 2017 Patrik Karlsson.
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
package org.nbgames.core.ui.home;

import javax.swing.JPanel;
import org.nbgames.core.SystemOptionsPanel;
import org.nbgames.core.api.options.NbgOptionsPanel;
import org.nbgames.core.api.service.PresenterProvider;
import se.trixon.almond.util.SystemHelper;

/**
 *
 * @author Patrik Karlsson
 */
public class HomeProvider implements PresenterProvider {

    private SystemOptionsPanel mOptionsPanel = new SystemOptionsPanel();
    private final HomePanel mPanel = new HomePanel();

    public static HomeProvider getInstance() {
        return Holder.INSTANCE;
    }

    private HomeProvider() {
    }

    @Override
    public String getCopyright() {
        return "©2013-2017 Patrik Karlsson";
    }

    @Override
    public String getCredit() {
        return "";
    }

    @Override
    public String getDescription() {
        return "Game platform";
    }

    @Override
    public String getHelp() {
        return SystemHelper.getLocalizedResourceAsString(HomeProvider.class, "help_%s.html", "help.html");
    }

    @Override
    public String getId() {
        return getClass().getName();
    }

    @Override
    public String getLicense() {
        return "Apache License, Version 2.0";
    }

    @Override
    public String getModuleName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getName() {
        return "nbGames";
    }

    @Override
    public NbgOptionsPanel getOptionsPanel() {
        return mOptionsPanel;
    }

    @Override
    public JPanel getPanel() {
        return mPanel;
    }

    @Override
    public String getShortDescription() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getVersion() {
        return "0.0.1";
    }

    private static class Holder {

        private static final HomeProvider INSTANCE = new HomeProvider();
    }
}
