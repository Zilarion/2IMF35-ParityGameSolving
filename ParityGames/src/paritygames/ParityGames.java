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
        File file = new File(args[0]);
        
//        File file = new File("/Users/ruudandriessen/study/2imf35/2IMF35-ParityGameSolving/problem sets/elevator-games/elevator2_7.gm");
        ParityGame pg = pgp.readFilePG(file);
        
        Pair<List<Vertex>, List<Vertex>> results = null;
        try {
            results = SmallProgressMeasures.calculate(pg, new LiftStrategyRandomlyOrdered(pg));
        } catch (IllegalTupleException ex) {
            Logger.getLogger(ParityGames.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (results.getKey().get(0).getID() == 0) {
            System.out.println("Even won!");
        } else if (results.getValue().get(0).getID() == 0) {
            System.out.println("Odd won!");
        } else {
            System.out.println("Hein won!");
        }
    }
    
}
