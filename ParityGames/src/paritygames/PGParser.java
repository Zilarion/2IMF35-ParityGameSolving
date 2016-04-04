/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paritygames;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import paritygames.Vertex.Owner;

/**
 *
 * @author Hein
 */
public class PGParser {

    Scanner scanner;
    Pattern initPattern;
    Pattern numberPattern;
    Pattern edgePattern;
    Pattern labelPattern;
    private HashMap<Integer, int[]> successors = new HashMap<>();

    public PGParser() {
        this.scanner = new Scanner(System.in);
        this.initPattern = Pattern.compile("parity ([0-9]+);");
        this.edgePattern = Pattern.compile("([0-9]+)\\s([0-9]+)\\s([0-9]+)\\s([0-9]+[,0-9]*)(?:\\s\"([\\S\\s]*)\")*;");
    }

    public ParityGame readFilePG(File file) throws FileNotFoundException, IOException {
        ParityGame pg = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (String line; (line = br.readLine()) != null;) {
                Matcher m = initPattern.matcher(line);
                if (m.find()) {
                    pg = new ParityGame();
                    String edgeLine = br.readLine();
                    while (edgeLine != null) {
                        m = edgePattern.matcher(edgeLine);
                        parsePG(pg, m);
                        edgeLine = br.readLine();
                    }
                }
            }
            for (int id : successors.keySet()) {
                int[] succ = successors.get(id);
                for (int sucId : succ) {
                    Vertex suc = pg.getVertex(sucId);
                    pg.getVertex(id).addSuccessor(suc);
                }
            }
        }
        return pg;
    }
    
    private ParityGame parsePG(ParityGame pg, Matcher m) throws IOException {
        if (m.find()) {
            int id = Integer.parseInt(m.group(1));
            int prio = Integer.parseInt(m.group(2));
            Owner own;
            if (m.group(3).equals("0")) {
                own = Owner.EVEN;
            } else {
                own = Owner.ODD;
            }
            String suc = m.group(4);
            String n = m.group(5);

            //Create array of successors as integers for the new vertex
            String[] strArray = suc.split(",");
            int[] sucArray = new int[strArray.length];
            for (int i = 0; i < strArray.length; i++) {
                sucArray[i] = Integer.parseInt(strArray[i]);
            }

            //Create the new vertex
            Vertex vertex = new Vertex(id, prio, own, n);
            pg.addVertex(vertex);
            successors.put(id, sucArray);
        }

        return pg;
    }
}
