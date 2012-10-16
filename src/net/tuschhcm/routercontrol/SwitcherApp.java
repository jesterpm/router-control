package net.tuschhcm.routercontrol;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import gnu.io.CommPortIdentifier;

import net.tuschhcm.routercontrol.router.Router;
import net.tuschhcm.routercontrol.router.ShinyBow5544Router;
import net.tuschhcm.routercontrol.ui.ConsoleUI;
import net.tuschhcm.routercontrol.ui.UserInterface;
import net.tuschhcm.routercontrol.ui.UserInterface.Action;

public class SwitcherApp {
    /**
     * The router I control.
     */
    private final Router mRouter;
    
    /**
     * My user interface.
     */
    private final UserInterface mUI;
    
    /**
     * My list of presets.
     */
    private final Map<Integer, Preset> mPresets;
    
    /**
     * Create a switcher app.
     * @param portName the port name for the router.
     */
    public SwitcherApp(final String portName) {
        mUI = new ConsoleUI();
        mRouter = new ShinyBow5544Router(portName);
        mPresets = new HashMap<Integer, Preset>();
        
        // Setup UI actions
        mUI.setPresetSelectionAction(new Action() {
            public void onAction() {
                handlePresetSelected();
            }
        });
        
        mUI.setToggleControlLockAction(new Action() {
            public void onAction() {
                handleLockToggled();
            }
        });
    }
    
    /**
     * Start the application.
     */
    public void run() {
        // TODO: Load the presets
        // TODO: Send the presets to the UI
        mUI.run();
    }
    
    /**
     * Handles a preset selection.
     */
    private void handlePresetSelected() {
        int selectedPreset = mUI.getSelectedPreset();
        // TODO: What to do when the preset is selected
    }

    /**
     * Locks or unlocks the physical router controls.
     */
    private void handleLockToggled() {
        mRouter.setLockControls(mUI.getControlLockStatus());
    }
    
    /**
     * Entry-point for the application.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Parse command line arguments
        if (args.length != 1) {
            System.err.println("You must pass one these arguments:");
            System.err.println("\t[commport]      The name of the serial port to use.");
            System.err.println("\t--list-ports    List the available serial ports.");
            return;
        }

        if (args[0].equals("--list-ports")) {
            listCommPorts();
            return;
        }

        String comPort = args[0];
        
        SwitcherApp app = new SwitcherApp(comPort);
        app.run();
    }

    private static void listCommPorts() {
        Enumeration ports = CommPortIdentifier.getPortIdentifiers();
    
        if (ports.hasMoreElements()) {
            System.out.println("These ports are available on your system: ");
            while (ports.hasMoreElements()) {
                CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
                if (port.getPortType() == CommPortIdentifier.PORT_SERIAL) {   
                    System.out.println(port.getName());
                }
            } 

        } else {
            System.out.println("There are no serial ports on your system.");
        }
    }
}
