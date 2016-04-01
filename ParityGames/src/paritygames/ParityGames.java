package paritygames;

import java.io.File;
import java.io.IOException;

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

            File file = new File("C:\\Users\\Hein\\Documents\\TUe\\Master Q3\\Algorithms for Model Checking (2IMF35)\\Assignments\\ParityGame.txt");
            ParityGame pg = pgp.readFilePG(file);
            System.out.println(pg.toString());
    }
    
}
