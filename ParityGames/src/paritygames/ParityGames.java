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
//        File file = new File(args[0]);
        
        File file = new File("/Users/ruudandriessen/study/2imf35/2IMF35-ParityGameSolving/problem sets/elevator-games/elevator1_2.gm");
        ParityGame pg = pgp.readFilePG(file);
        
        Pair<List<Vertex>, List<Vertex>> results = null;
        try {
            results = SmallProgressMeasures.calculate(pg, new LiftStrategyOrdered(pg));
        } catch (IllegalTupleException ex) {
            Logger.getLogger(ParityGames.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (results.getKey().size() == 0) {
            System.out.println("Even: {}");
            System.out.println("Odd: S");
        } else if (results.getValue().size() == 0) {
            
            System.out.println("Even: S");
            System.out.println("Odd: {}");
        } else {
            System.out.println("Even: " + results.getKey());
            System.out.println("Odd: " + results.getValue());
        }
    }
    
}
