package net.tuschhcm.routercontrol;

/**
 * Class to represent a router preset.
 */
public class Preset {

	/**
	 * Load a preset from the given file.
	 * 
	 * @param filename The file to load.
	 * @return the Preset object
	 */
	public static Preset loadPresetFile(String filename) {
		// TODO: Implement
		return null;
	}
	
	/**
	 * Get the name of the preset.
	 * 
	 * @return The preset name
	 */
	public String getName() {
		// TODO: Implement
		return null;
	}
	
	/**
	 * Get an input for the given output.
	 * 
	 * @param output
	 * @return Input number, or 0 if no change.
	 */
	public int getInputForOutput(final int output) {
		// TODO: Implement
		return 0;
	}
}
