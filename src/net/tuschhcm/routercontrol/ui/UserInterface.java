package net.tuschhcm.routercontrol.ui;

/**
 * Interface specification for the view.
 */
public interface UserInterface {
    /**
     * Tell the user interface about a preset.
     * 
     * @param number Preset number
     * @param name Preset name
     */
    public void addPreset(final int number, final String name);
    
    /**
     * Set the action handler called when a preset is selected.
     * @param action
     */
    public void setPresetSelectionAction(final Action action);
    
    /**
     * @return the selected preset.
     */
    public int getSelectedPreset();
    
    /**
     * Handle toggling the control lock
     */
    public void setToggleControlLockAction(final Action action);

    /**
     * Get the status of the control lock.
     * @return true if the lock is enabled.
     */
    public boolean getControlLockStatus();

    /**
     * Set the control lock status on the UI.
     * @param enabled true to set the controls lock.
     */
    public void setControlLockStatus(boolean enabled);
    
    /**
     * Start the user interface.
     */
    public void run();
    
    /**
     * Interface specification for an action handler.
     */
    public interface Action {
        public void onAction();
    }
}
