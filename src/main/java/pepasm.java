import java.util.*;

import static java.util.Map.entry;

public class pepasm {
    public static void main(String[] args) {
        Map<String, Integer> instructions = Map.ofEntries(
                entry("STBA", 15), entry("LDBA", 13), entry("STWA", 14),
                entry("LDWA", 12), entry("ANDA", 8), entry("CPBA", 11), entry("BRNE", 25)
        );
        // Called zeroInstructions because they start with a 0.
        // They have a separate map because they do not take an input.
        // For example, LDBA 0x000A, i, loads 0x000A, while ASRA stands alone.
        Map<String, String> zeroInstructions = Map.ofEntries(
                entry("ASLA", "0A"), entry("ASRA", "0C"), entry("STOP", "00")
        );

        String fileName = args[0];
        Integer[] branchLocations = new Integer[0];
    }
}
