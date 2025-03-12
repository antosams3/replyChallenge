package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public int D;
    public int R;
    public int T;
    public List<Resource> resources;
    public List<Turn> turns;
    public List<Resource> actualResources;
    public int actualBuildings = 0;


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
                    this.D = Integer.parseInt(values[0]);
                    this.R = Integer.parseInt(values[1]);
                    this.T = Integer.parseInt(values[2]);
                    first = false;
                    continue;
                }
                if (i < this.R) {
                    Resource r = new Resource();
                    r.RI = values[0];
                    r.RA = Integer.parseInt(values[1]);
                    r.RP = Integer.parseInt(values[2]);
                    r.RW = Integer.parseInt(values[3]);
                    r.RM = Integer.parseInt(values[4]);
                    r.RL = Integer.parseInt(values[5]);
                    r.RU = Integer.parseInt(values[6]);
                    String specialEffectType = values[7];
                    if (specialEffectType.equals("X")) r.RT = SpecialEffectType.X;
                    else {
                        SpecialEffectType s = SpecialEffectType.valueOf(specialEffectType);
                        s.setRE(Integer.parseInt(values[8]));
                        r.RT = s;
                    }
                    this.resources.add(r);
                    i++;
                    continue;
                }
                if (j < this.T) {
                    Turn t = new Turn();
                    t.TMt = Integer.parseInt(values[0]);
                    t.TXt = Integer.parseInt(values[1]);
                    t.TRt = Integer.parseInt(values[2]);
                    this.turns.add(t);
                    j++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void calculateCosts(){

    }

    public Resource resetResource(String id){
        
    }
    public void decreaseActivationTurn(){
        for(int i=0; i<this.actualResources.size(); i++){
            //La risorsa non è utilizzabile per ora
            if(this.actualResources.get(i).RW == 0){
                //controllo se è scaduto il tempo di down
                if(this.actualResources.get(i).RM == 0){
                    //reset
                    this.actualResources.set (i, this.resetResource(this.actualResources.get(i).RI));

                }
                //decremento il tempo di down
                else{
                    this.actualResources.get(i).RM -= 1;
                }
            }
            
            else {
                this.actualResources.get(i).RW -=1;
            }
        }
    }


}
