package me.guid118.racesimulatie.Files;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static me.guid118.racesimulatie.Main.prop;

public class Config {

    static File file;
    static String configFilePath = "config.properties";

    public static void CreateFile() {

        try {


            file = new File(configFilePath);

            if (!file.exists()) {
                file.createNewFile();
            }
            FileInputStream propsInput = new FileInputStream(configFilePath);
            prop.load(propsInput);

            setdefaults();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setdefaults() {
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(configFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        prop.putIfAbsent("Race", "zie hieronder instellingen");

        prop.putIfAbsent("Soft", "");
        prop.putIfAbsent("Banden.Soft.rondetijd", "107.4");
        prop.putIfAbsent("Banden.Soft.Maxgebruik", "14");
        prop.putIfAbsent("Banden.Soft.Maxronden", "25");
        prop.putIfAbsent("Banden.Soft.degradatie", "0.16");

        prop.putIfAbsent("Medium", "");
        prop.putIfAbsent("Banden.Medium.rondetijd", "107.6");
        prop.putIfAbsent("Banden.Medium.Maxgebruik", "27");
        prop.putIfAbsent("Banden.Medium.Maxronden", "33");
        prop.putIfAbsent("Banden.Medium.degradatie", "0.1");

        prop.putIfAbsent("Hard", "");
        prop.putIfAbsent("Banden.Hard.rondetijd", "108.2");
        prop.putIfAbsent("Banden.Hard.Maxgebruik", "38");
        prop.putIfAbsent("Banden.Hard.Maxronden", "45");
        prop.putIfAbsent("Banden.Hard.degradatie", "0.05");

        prop.putIfAbsent("other", "");
        prop.putIfAbsent("other.pitstops.tijd", "23");
        prop.putIfAbsent("other.simulatie.ronden", "72");

        prop.putIfAbsent("strategie", "");
        prop.putIfAbsent("strategie.pitstops", "2");
        prop.putIfAbsent("strategie.pitstopronde", "25");
        prop.putIfAbsent("strategie.pitstopronde1", "55");
        prop.putIfAbsent("strategie.pitstopronde2", "");
        prop.putIfAbsent("Soft", "3");
        prop.putIfAbsent("Medium", "4");
        prop.putIfAbsent("Hard", "5");
        prop.putIfAbsent("strategie.startband", "4");
        prop.putIfAbsent("strategie.middenband", "4");
        prop.putIfAbsent("strategie.eindband", "3");
        prop.putIfAbsent("strategie.eindeindband", "");
        try {
            prop.store(os,"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static double getvalue(String path) {
        return Double.parseDouble(prop.getProperty(path));
    }






}
