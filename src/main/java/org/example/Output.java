package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Output {

    public int t;
    public int Rt;
    public ArrayList<Integer> RIx;

    public Output() {
        t = 0;
        Rt = 0;
        RIx = new ArrayList<>();
    }

    //scrive la riga sul file
    public static void writeFile(String filePath, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Aggiunge una risorsa acquistata nel turno
    public void addResources(int RIx){
        this.Rt +=1;
        this.RIx.add(RIx);
    }

    //Genera la riga del turno da scrivere su file
    public String generateContent(){
        StringBuilder result = new StringBuilder();
        result.append(this.Rt);
        for (Object item : this.RIx) {
            result.append(" ").append(item.toString()); 
        }
        return result.toString();
    }

    //Funzione da chiamare dopo avere scritto una riga sul file per pulire i dati
    public void restart(){
        this.Rt = 0;
        this.t += 1;
        this.RIx.clear();
    }
}
