import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import static java.util.Map.entry;

public class pepasm {
    public static void main(String[] args) {
        Map<String, Integer> instructions = Map.ofEntries(
                entry("STBA", 15), entry("LDBA", 13), entry("STWA", 14),
                entry("LDWA", 12), entry("ANDA", 8), entry("CPBA", 11),
                entry("BRNE", 25), entry("ADDA", 6), entry("SUBA", 7)
        ); //value associated with value in pep
        // Called zeroInstructions because they start with a 0.
        // They have a separate map because they do not take an input.
        // For example, LDBA 0x000A, i, loads 0x000A, while ASRA stands alone.
        Map<String, String> zeroInstructions = Map.ofEntries(
                entry("ASLA", "0A"), entry("ASRA", "0C"), entry("STOP", "00")
        );

        Map<String, Integer> instructionSpecifiers = Map.ofEntries(
                entry("i", 0), entry("d", 1)
        );

        String fileName = args[0];
        Map<String, Integer> branchLocations = Map.of();

        BufferedReader br;
        try {
            FileReader fr = new FileReader(fileName);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String line;
        while (true) {
            try {
                // reading the file
                line = br.readLine();

                // Ends loop if EOF
                if(line == null || line.contains(".END")){
                    System.out.print(" zz");
                    break;
                }
                //removes all whitespace before and after the string
                line = line.trim();
                if (line.isEmpty()) continue;
                //splitting into chunks, uppercase for uniformity
                String[] splitLine = line.split(" ");
                String instruction = splitLine[0].toUpperCase();

                if (zeroInstructions.containsKey(instruction)) {
                    System.out.print(zeroInstructions.get(instruction));
                }

                else if (instructions.containsKey(instruction)) {
                    int code = instructions.get(instruction);
                    String address;
                    if (splitLine.length > 1) {
                        address = splitLine[1];
                    } else {
                        address = "0x0000";
                    }
                    //getting rid of commas and prefixes
                    address = address.replace(",", "").replace("0x", "");
                    //convert to hex
                    String hexCode = String.format("%01X", code);
                    //get instruction specifier & add to instruction
                    int specifier = instructionSpecifiers.get(splitLine[2]);
                    hexCode += String.valueOf(specifier);
                    System.out.print(hexCode + " ");
                    //making sure that we have a four digit hex number
                    while(address.length() < 4){
                        address = "0" + address;
                    }
                    //separating address into 2-digit chunks & printing them
                    for (int i = 0; i < address.length(); i += 2) {
                        String byteChunk = address.substring(i, i + 2);
                        System.out.print(byteChunk + " ");
                    }
                }
                else {
                    System.out.println("Invalid/Unknown Instruction: " + instruction);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } // End catch
        } // End while
    } // End main
} // End class