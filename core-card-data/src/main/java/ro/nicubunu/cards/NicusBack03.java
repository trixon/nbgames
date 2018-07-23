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
package ro.nicubunu.cards;

import org.nbgames.core.card.CardBackSupplier;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Patrik Karlström
 */
@ServiceProvider(service = CardBackSupplier.class)
public class NicusBack03 implements CardBackSupplier {

    @Override
    public String getName() {
        return NbBundle.getMessage(this.getClass(), "CTL_Back03");
    }

    @Override
    public String getPath() {
        return "ro/nicubunu/cards/backs/back03.png";
    }
}
