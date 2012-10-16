package net.tuschhcm.routercontrol;

import java.util.HashMap;
import java.util.Map;

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
		
		// TODO: Load the presets
		
		// TODO: Setup UI hooks
		mUI.setPresetSelectionAction(new Action() {
			public void onAction() {
				handlePresetSelected();
			}
		});
		
		// TODO: Send the presets to the UI
	}
	
	/**
	 * Start the application.
	 */
	public void run() {
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
	 * Entry-point for the application.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		// TODO: Parse command line arguments
		String comPort = "COM1";
		
		SwitcherApp app = new SwitcherApp(comPort);
		app.run();
	}
}
