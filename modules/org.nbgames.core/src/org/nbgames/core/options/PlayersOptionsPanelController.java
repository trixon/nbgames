package org.nbgames.core.options;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
@OptionsPanelController.SubRegistration(
        id = PlayersOptionsPanelController.ID,
        position = 20,
        location = "nbGames",
        displayName = "#AdvancedOption_DisplayName_Players",
        keywords = "#AdvancedOption_Keywords_Players",
        keywordsCategory = "nbGames/Players"
)
public final class PlayersOptionsPanelController extends OptionsPanelController {

    public static final String ID = "nbGames/Players";
    private boolean mChanged;
    private PlayersPanel mPanel;
    private final PropertyChangeSupport mPcs = new PropertyChangeSupport(this);

    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        mPcs.addPropertyChangeListener(l);
    }

    @Override
    public void applyChanges() {
        getPanel().store();
        mChanged = false;
    }

    @Override
    public void cancel() {
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
        return mChanged;
    }

    @Override
    public boolean isValid() {
        return getPanel().valid();
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        mPcs.removePropertyChangeListener(l);
    }

    @Override
    public void update() {
        getPanel().load();
        mChanged = false;
    }

    void changed() {
        if (!mChanged) {
            mChanged = true;
            mPcs.firePropertyChange(OptionsPanelController.PROP_CHANGED, false, true);
        }
        mPcs.firePropertyChange(OptionsPanelController.PROP_VALID, null, null);
    }

    private PlayersPanel getPanel() {
        if (mPanel == null) {
            mPanel = new PlayersPanel(this);
        }
        return mPanel;
    }
}
