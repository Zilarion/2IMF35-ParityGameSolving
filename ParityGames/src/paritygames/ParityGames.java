package paritygames;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author Hein
 */
public class ParityGames {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        PGParser pgp = new PGParser();

        String fileLoc = null, algorithm = null;
        boolean fullOutput = false;
        boolean mute = false;
        for (int i = 0; i < args.length; i++) {
            String input = args[i];
            switch (input) {
                case "-f":
                    fileLoc = args[i + 1];
                    break;
                case "-l":
                    algorithm = args[i + 1];
                    break;
                case "-d":
                    fullOutput = true;
                    break;
                case "-m":
                    mute = true;
                    break;
                case "-h":
                case "--help":
                    printHelp();
                    return;
                default:
                    break;
            }
        }

        // Check if we have a valid algorithm and file
        if (fileLoc == null || algorithm == null || (algorithm == "random" || algorithm == "input")) {
            printHelp();
            return;
        }
        File file = new File(fileLoc);

        // Read parity game from file
        ParityGame pg = pgp.readFilePG(file);
        LiftStrategy strategy = getStrategy(algorithm, pg);

        // Calculate
        Pair<List<Vertex>, List<Vertex>> results = null;
        try {
            results = SmallProgressMeasures.calculate(pg, strategy);
        } catch (IllegalTupleException ex) {
            Logger.getLogger(ParityGames.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (fullOutput && !mute) {
            // Print full results
            System.out.println("Even: " + results.getKey());
            System.out.println("Odd: " + results.getValue());
        } else if (!mute) {
            // Print results
            if (results.getKey().get(0).getID() == 0) {
                System.out.println("Even won!");
            } else if (results.getValue().get(0).getID() == 0) {
                System.out.println("Odd won!");
            } else {
                System.out.println("Hein won!");
            }
        }
    }

    public static void printHelp() {
        System.out.println("Usage ParityGames.jar [options]");
        System.out.println("-h|--help   print help");
        System.out.println("-f          input file location");
        System.out.println("-d          detailed output (full winning partitions)");
        System.out.println("-m          no output (mute)");
        System.out.println("-l          lifting method used: <order, random, ..>");
    }

    public static LiftStrategy getStrategy(String strategy, ParityGame pg) {
        switch (strategy) {
            case "random":
                return new LiftStrategyRandomlyOrdered(pg);
            case "input":
                return new LiftStrategyOrdered(pg);
            default:
                return null;
        }
    }
}
