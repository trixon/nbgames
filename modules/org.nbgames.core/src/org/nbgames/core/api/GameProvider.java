/* 
 * Copyright 2015 Patrik Karlsson.
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

import org.nbgames.core.GameCategory;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public interface GameProvider {

    String getActionCategory();

    String getActionId();

    GameCategory getCategory();

    String getCopyright();

    String getCredit();

    String getDescription();

    String getLicense();

    String getModuleName();

    String getName();

    String getOptionsPath();

    String getShortDescription();

    String getVersion();
}
