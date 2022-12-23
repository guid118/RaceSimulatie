package me.guid118.racesimulatie.Files;


import java.io.*;

import static me.guid118.racesimulatie.Main.simulated;


public class Output {

    static File file;

    public static void CreateFile() {
        try {
            file = new File("Output_0.txt");
            File fileold = new File("Output_1.txt");
            if (file.createNewFile()) {
                System.out.println("File output will be: " + file.getName());
            } else {
                for (int i = 1; fileold.exists() && i <= 50; i++) {
                    fileold = new File("Output_" + i + ".txt");

                }
                file = fileold;
                file.createNewFile();
                System.out.println("File output will be: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    static String[] lines = new String[10000000];
    static int linenr = 0;
    public static void Save(String arg) {
        lines[linenr] = arg;
        linenr++;
    }


    public static void Write() {

        //Declaring FileWriter Object.
        FileWriter fileWriter = null;
        //Stroying file name in a string.
        String pathname = file.getName();
        //Declaring String Array to hold multiple data
        try {
            //Creating File Object.
            File file = new File(pathname);
            //Initializing filewriter object.
            fileWriter = new FileWriter(file);

            int linecounter = 0;

            for (int i = 0; i <= lines.length - 1; i++) {
                if (lines[i] != null) {
                    //Writing content to a file.
                    fileWriter.write(lines[i] + "\n");
                    //Incrementing line counter.
                    linecounter++;
                } else {
                    linecounter++;
                    continue;
                }


            }


        } catch (IOException iOException) {
            //Catching Exceptions.
            System.out.println("Error : " + iOException.getMessage());
        } finally {
            System.out.println(simulated + " Strategies simulated");
            System.out.println(simulated-linenr + " of which were too slow or had a tire failure");
            System.out.println("resulting in " + linenr + " valid strategies that can be found in: " + file.getName());

            if (fileWriter != null) {
                try {
                    //Closing file writer object.
                    fileWriter.close();
                } catch (IOException iOException) {
                    System.out.println("Error : " + iOException.getMessage());
                }
            }

        }

    }

}