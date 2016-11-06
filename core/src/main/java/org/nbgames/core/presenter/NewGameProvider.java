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
import org.nbgames.core.api.DialogProvider;
import org.nbgames.core.api.PresenterProvider;

/**
 *
 * @author Patrik Karlsson
 */
public class NewGameProvider implements PresenterProvider, DialogProvider {

    private final NewGameDialog mPanel = new NewGameDialog();

    public static NewGameProvider getInstance() {
        return Holder.INSTANCE;
    }

    private NewGameProvider() {
    }

    @Override
    public String getCopyright() {
        return null;
    }

    @Override
    public String getCredit() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public String getId() {
        return getClass().getName();
    }

    @Override
    public String getLicense() {
        return null;
    }

    @Override
    public String getModuleName() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public JPanel getOptionsPanel() {
        return null;
    }

    @Override
    public NewGameDialog getPanel() {
        return mPanel;
    }

    @Override
    public String getShortDescription() {
        return null;
    }

    @Override
    public String getVersion() {
        return null;
    }

    private static class Holder {

        private static final NewGameProvider INSTANCE = new NewGameProvider();
    }
}
