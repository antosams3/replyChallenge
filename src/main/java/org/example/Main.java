package org.example;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(args[0]);


    }

    public static void writeFile(String filePath, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}