package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public float D;
    public int R;
    public int T;
    public List<Resource> resources;
    public List<Turn> turns;

    public Game(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            this.resources = new ArrayList<>();
            this.turns = new ArrayList<>();
            boolean first = true;
            int i = 0;
            int j = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(" ");
                if (first) {
                    this.D = Float.parseFloat(values[0]);
                    this.R = Integer.parseInt(values[1]);
                    this.T = Integer.parseInt(values[2]);
                    first = false;
                    continue;
                }
                if (i < this.R) {


                    i++;
                    continue;
                }
                if (j < this.T) {
                    Turn t = new Turn();
                    t.TMt = Integer.parseInt(values[0]);
                    t.TXt = Integer.parseInt(values[1]);
                    t.TRt = Float.parseFloat(values[2]);
                    this.turns.add(t);
                    j++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
