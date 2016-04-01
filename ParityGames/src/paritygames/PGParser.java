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

    public PGParser() {
        this.scanner = new Scanner(System.in);
        this.initPattern = Pattern.compile("parity ([0-9]+);");
        this.edgePattern = Pattern.compile("([0-9]+)\\s([0-9]+)\\s([0-9]+)\\s([0-9]+[,0-9]*)(?:\\s\"([0-9a-zA-Z, ()]*)\")*;");
    }

    public ParityGame readFilePG(File file) throws FileNotFoundException, IOException {
        ParityGame pg = null;
        HashMap<Integer, Vertex> states = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (String line; (line = br.readLine()) != null;) {
                Matcher m = initPattern.matcher(line);
                if (m.find()) {
                    pg = new ParityGame(m.group(1));
                    String edgeLine = br.readLine();
                    while (edgeLine != null) {
                        m = edgePattern.matcher(edgeLine);
                        parsePG(pg, states, m);
                        edgeLine = br.readLine();
                    }
                }
            }
        }

        if (pg == null) {
            return pg;
        }

        for (Vertex s : states.values()) {
            pg.addNode(s);
        }
        return pg;
    }

    private ParityGame parsePG(ParityGame pg, HashMap<Integer, Vertex> states, Matcher m) throws IOException {
        if (m.find()) {
            int id = Integer.parseInt(m.group(1));
            int prio = Integer.parseInt(m.group(2));
            int own = Integer.parseInt(m.group(3));
            String suc = m.group(4);
            String n = m.group(5);

            String[] strArray = suc.split(",");
            int[] successors = new int[strArray.length];
            for (int i = 0; i < strArray.length; i++) {
                successors[i] = Integer.parseInt(strArray[i]);
            }

            Vertex identifier;
            if (states.containsKey(id)) {
                identifier = states.get(id);
            } else {
                identifier = new Vertex(id, prio, own, successors, n);
            }
            states.put(id, identifier);
        }
        return pg;
    }
}
