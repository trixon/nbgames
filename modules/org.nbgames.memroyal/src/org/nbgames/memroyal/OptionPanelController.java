package org.nbgames.memroyal;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;

@OptionsPanelController.SubRegistration(
        id = "Memroyal",
        location = "Card",
        displayName = "#AdvancedOption_DisplayName_Option",
        keywords = "#AdvancedOption_Keywords_Option",
        keywordsCategory = "Card/Option"
)
@org.openide.util.NbBundle.Messages({"AdvancedOption_DisplayName_Option=Memroyal", "AdvancedOption_Keywords_Option=memroyal"})
/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public final class OptionPanelController extends OptionsPanelController {

    private boolean changed;
    private OptionPanel panel;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    @Override
    public void applyChanges() {
        getPanel().store();
        changed = false;
    }

    @Override
    public void cancel() {
        // need not do anything special, if no changes have been persisted yet
    }

    @Override
    public JComponent getComponent(Lookup masterLookup) {
        return getPanel();
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null; // new HelpCtx("...ID") if you have a help set
    }

    @Override
    public boolean isChanged() {
        return changed;
    }

    @Override
    public boolean isValid() {
        return getPanel().valid();
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    @Override
    public void update() {
        getPanel().load();
        changed = false;
    }

    void changed() {
        if (!changed) {
            changed = true;
            pcs.firePropertyChange(OptionsPanelController.PROP_CHANGED, false, true);
        }
        pcs.firePropertyChange(OptionsPanelController.PROP_VALID, null, null);
    }

    private OptionPanel getPanel() {
        if (panel == null) {
            panel = new OptionPanel(this);
        }
        return panel;
    }
}
