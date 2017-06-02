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
package org.nbgames.core.api.service;

import javax.swing.JPanel;
import org.nbgames.core.api.ui.OptionsPanel;

/**
 *
 * @author Patrik Karlsson
 */
public interface PresenterProvider {

    String getCopyright();

    String getCredit();

    String getDescription();

    String getHelp();

    String getId();

    String getLicense();

    String getModuleName();

    String getName();

    OptionsPanel getOptionsPanel();

    JPanel getPanel();

    String getShortDescription();

    String getVersion();
}
