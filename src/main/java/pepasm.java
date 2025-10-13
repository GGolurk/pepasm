import java.util.*;

import static java.util.Map.entry;

public class pepasm {
    public static void main(String[] args) {
        Map<String, Integer> instructions = Map.ofEntries(
                entry("STBA", 15), entry("LDBA", 13), entry("STWA", 14),
                entry("LDWA", 12), entry("ANDA", 8), entry("ASLA", 110),
                entry("ASRA", 112), entry("STOP", 0), entry("CPBA", 11), entry("BRNE", 25)
        );

        String fileName = args[0];
    } //waaaaaaaaaaaaaa
}
