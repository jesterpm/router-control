package net.tuschhcm.routercontrol.router;

/**
 * Interface specification for a router
 */
public interface Router {
    /**
     * Send the given input to the given output.
     * 
     * @param output
     * @param input
     * 
     * @throws IllegalArgumentException if input or output are out of range.
     */
    public void switchInput(int output, int input) throws IllegalArgumentException;
    
    /**
     * Power on or power off the router
     * 
     * @param True to turn on the router 
     */
    public void setPower(boolean on);
    
    /**
     * Enable or disable physical controls
     * @param enabled true to disable the controls.
     */
    public void setLockControls(boolean enabled);
}
