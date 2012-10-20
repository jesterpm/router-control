package net.tuschhcm.routercontrol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Class to represent a router preset.
 */
public class Preset {
    private static final String UNNAMED = "";

    /**
     * Name of the preset.
     */
    private String mName;

    /**
     * List of inputs for outputs.
     * List index corresponds with the output.
     */
    private final ArrayList<Integer> mInputs;

    /**
     * Load the presets from a given file.
     *
     * @param file The file to load.
     * @return A list of Presets
     * @throws ParseException if the presets file can't be parsed.
     * @throws IOException if the file can't be read.
     */
    public static Map<Integer, Preset> loadPresetsFile(File file)
        throws ParseException, IOException {
        
        final Map<Integer, Preset> map = new HashMap<Integer, Preset>();
        final BufferedReader in = new BufferedReader(new FileReader(file));

        try {
            int lineno = 0;
            String line;

            while ((line = in.readLine()) != null) {
                lineno++; // Inc. the line count immediately after the read.

                line = line.trim();
                if (line.equals("")) {
                    continue;
                }

                try {
                    map.put(lineno, Preset.fromString(line.trim()));

                } catch (IllegalArgumentException e) {
                    throw new ParseException("Malformed preset on line " + lineno
                            + ": " + e.getMessage(), lineno);
                }
            }

            return map;

        } finally {
            in.close();
        }
    }

    /**
     * Load a preset from a String.
     * @param line The string to parse.
     * @return The Preset defined by the string.
     * @throws IllegalArgumentException if the string is malformed.
     */
    public static Preset fromString(String line) {
        final Preset p = new Preset();

        boolean inInput = false;
        int start = 0;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (inInput && Character.isWhitespace(c)) {
                // End of current input
                Integer input = Integer.valueOf(line.substring(start, i));
                p.mInputs.add(input);
                inInput = false;
                start = i + 1;

            } else if (!inInput && c == '#') {
                // Start comment
                // Ignore empty comments.
                if ((i+1) < line.length()) {
                    p.mName = line.substring(i+1).trim();
                }
                break; // end loop

            } else if (Character.isDigit(c)) {
                inInput = true;

            } else {
                throw new IllegalArgumentException("Unexpected character '" + c + "' at position "
                        + (i+1) + ". (inInput = " + inInput +")");
            }
        }

        if (inInput) {
            // One last input at the end of the line.
            Integer input = Integer.valueOf(line.substring(start));
            p.mInputs.add(input);
        }

        return p;
    }

    /**
     * Constructor for Preset.
     */
    public Preset() {
        mName = UNNAMED;
        mInputs = new ArrayList<Integer>();
    }

    /**
     * Get the name of the preset.
     *
     * @return The preset name
     */
    public String getName() {
        return mName;
    }

    /**
     * Get an input for the given output.
     *
     * @param output
     * @return Input number, or 0 if no change.
     * @throws IllegalArgumentException if the output isn't defined.
     */
    public int getInputForOutput(final int output) {
        final int index = output - 1;
        if (index < 0 || index > mInputs.size()) {
            throw new IllegalArgumentException("Output out of range.");
        }

        return mInputs.get(index);
    }

    /**
     * @return The number of outputs defined in this preset.
     */
    public int getNumberOfOutputs() {
        return mInputs.size();
    }

    /**
     * Return a string representation of this preset.
     */
    public String toString() {
        String str = "";
        for (Integer in : mInputs) {
            str += in + " ";
        }
        
        if (!mName.equals(UNNAMED)) {
            str += "# " + mName;
        }

        return str;
    }
}
