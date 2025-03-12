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
    public Output output = new Output(); 


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

    //calcola il costo di mantenimento
    public int calculateCosts(){
        int cost = 0;
        for (int i = 0; i < this.actualResources.size(); i++) {
            if(this.actualResources.get(i).RW >0){
                cost += this.actualResources.get(i).RP;
            }
        }
        return cost;
    }

    public Resource resetResource(String id){
        Resource resource = new Resource();
        for (int i = 0; i < this.R; i++) {
            if(this.resources.get(i).RI.equals(id)){
                resource = this.resources.get(i);
                break;
            }
        }
        return resource;
    }
    public void decreaseActivationTurn(){
    	List<Resource> expiredResources = new ArrayList<>();
        for(int i=0; i<this.actualResources.size(); i++){
            this.actualResources.get(i).RL -= 1;
                    
            if(this.actualResources.get(i).RL == 0){
            	expiredResources.add(this.actualResources.get(i));
            }
            else {
                
            	//La risorsa non è utilizzabile per ora
                if(this.actualResources.get(i).RW == 0){
                    this.actualResources.get(i).RM -= 1;
                    if(this.actualResources.get(i).RM == 0){
                        //reset
                        this.actualResources.set (i, this.resetResource(this.actualResources.get(i).RI));
                        
                    }
                }
                //La risorsa è ancora utilizzabile
                else {
                    this.actualResources.get(i).RW -=1;
                }
            }
        }
        
        this.actualResources.removeAll(expiredResources);
    }

    public int calculateProfit(){
        int Tx = this.turns.get(this.output.t).TXt;
        int Tr = this.turns.get(this.output.t).TRt;
        return Math.min(this.actualBuildings, Tx) * Tr;
    }


}
