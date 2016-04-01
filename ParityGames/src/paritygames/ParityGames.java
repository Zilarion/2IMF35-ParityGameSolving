/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     */
    public static void main(String[] args) throws IOException {
        PGParser pgp = new PGParser();

            File file = new File("C:\\Users\\Hein\\Documents\\TUe\\Master Q3\\Algorithms for Model Checking (2IMF35)\\Assignments\\ParityGame.txt");
            ParityGame pg = pgp.readFilePG(file);
            System.out.println(pg.toString());
    }
    
}
