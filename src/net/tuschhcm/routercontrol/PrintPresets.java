package net.tuschhcm.routercontrol;

import java.io.File;
import java.util.Map;

/**
 * Utility to print out the preset file in a nice readable form.
 */
public class PrintPresets {
    public static void main(String... args) throws Exception {
        Map<Integer, Preset> ps = Preset.loadPresetsFile(new File("presets.txt"));

        for (Map.Entry<Integer, Preset> entry : ps.entrySet()) {
            final Preset p = entry.getValue();

            System.out.printf("Preset %3d. %-20s",
                    entry.getKey(), p.getName());
            
            for (int i = 1; i <= p.getNumberOfOutputs(); i++) {
                System.out.printf("%3d", p.getInputForOutput(i));
            }
            System.out.println();
        }
    }
}
