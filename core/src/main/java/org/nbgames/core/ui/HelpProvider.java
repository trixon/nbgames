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
package org.nbgames.core.ui;

import org.nbgames.core.api.service.DialogProvider;
import org.nbgames.core.api.ui.OptionsPanel;
import org.nbgames.core.api.service.PresenterProvider;

/**
 *
 * @author Patrik Karlsson
 */
public class HelpProvider implements PresenterProvider, DialogProvider {

    private final HomeProvider mHomeProvider = HomeProvider.getInstance();
    private final HelpDialog mPanel = new HelpDialog();

    public static HelpProvider getInstance() {
        return Holder.INSTANCE;
    }

    private HelpProvider() {
    }

    @Override
    public String getCopyright() {
        return mHomeProvider.getCopyright();
    }

    @Override
    public String getCredit() {
        return mHomeProvider.getCredit();
    }

    @Override
    public String getDescription() {
        return mHomeProvider.getDescription();
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
        return mHomeProvider.getLicense();
    }

    @Override
    public String getModuleName() {
        return mHomeProvider.getModuleName();
    }

    @Override
    public String getName() {
        return mHomeProvider.getName();
    }

    @Override
    public OptionsPanel getOptionsPanel() {
        return mHomeProvider.getOptionsPanel();
    }

    @Override
    public HelpDialog getPanel() {
        return mPanel;
    }

    @Override
    public String getShortDescription() {
        return mHomeProvider.getShortDescription();
    }

    @Override
    public String getVersion() {
        return mHomeProvider.getVersion();
    }

    private static class Holder {

        private static final HelpProvider INSTANCE = new HelpProvider();
    }
}
