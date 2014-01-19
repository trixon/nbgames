package ro.nicubunu.cards;

import org.nbgames.core.card.CardBackSupplier;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
@ServiceProvider(service = CardBackSupplier.class)
public class NicusBack04 implements CardBackSupplier {

    @Override
    public String getName() {
        return NbBundle.getMessage(this.getClass(), "CTL_Back04");
    }

    @Override
    public String getPath() {
        return "ro/nicubunu/cards/backs/back04.png";
    }
}
