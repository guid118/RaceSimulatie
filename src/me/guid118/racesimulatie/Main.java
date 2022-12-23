package me.guid118.racesimulatie;

import me.guid118.racesimulatie.Files.Config;
import me.guid118.racesimulatie.Files.Output;
import me.guid118.racesimulatie.util.OrderedProperties;

import static me.guid118.racesimulatie.Files.Config.getvalue;
import static me.guid118.racesimulatie.Files.Output.Save;
import static me.guid118.racesimulatie.Files.Output.Write;
import static me.guid118.racesimulatie.Rondetijd.rondetijd;

public class Main {


    static double Softrondetijd;
    static double maxsoftgebruiks;
    static double maxsoftgebruik;
    static double maxsoftrondens;
    static double softdegradatie;

    static double Mediumrondetijd;
    static double maxmediumgebruiks;
    static double maxmediumgebruik;
    static double maxmediumrondens;
    static double mediumdegradatie;

    static double Hardrondetijd;
    static double maxhardgebruiks;
    static double maxhardgebruik;
    static double maxhardrondens;
    static double harddegradatie;

    static double startbrandstof = 3;
    static double brandstofperronde;

    static double multiplier;

    static int pitstopronde;
    static int pitstopronde1;
    static int pitstopronde2;
    static double pitstoptijd;
    static int pitstops;
    static int totronde;

    static double totalrt;

    public static int simulated;
    public static OrderedProperties prop = new OrderedProperties();

    static int sbiteration;
    static int mbiteration;
    static int ebiteration;
    static int eebiteration;

    public static void main(String[] args) {

        Config.CreateFile();
        Softrondetijd = getvalue("Banden.Soft.rondetijd");
        maxsoftgebruiks = getvalue("Banden.Soft.Maxgebruik");
        maxsoftrondens = getvalue("Banden.Soft.Maxronden");
        softdegradatie = getvalue("Banden.Soft.degradatie");

        Mediumrondetijd = getvalue("Banden.Medium.rondetijd");
        maxmediumgebruiks = getvalue("Banden.Medium.Maxgebruik");
        maxmediumrondens = getvalue("Banden.Medium.Maxronden");
        mediumdegradatie = getvalue("Banden.Medium.degradatie");

        Hardrondetijd = getvalue("Banden.Hard.rondetijd");
        maxhardgebruiks = getvalue("Banden.Hard.Maxgebruik");
        maxhardrondens = getvalue("Banden.Hard.Maxronden");
        harddegradatie = getvalue("Banden.Hard.degradatie");

        pitstoptijd = getvalue("other.pitstops.tijd");
        totronde = (int) getvalue("other.simulatie.ronden");

        pitstops = (int) getvalue("strategie.pitstops");
        pitstopronde = (int) getvalue("strategie.pitstopronde");
        if(getvalue("strategie.pitstops") == 2) {
            pitstopronde1 = (int) getvalue("strategie.pitstopronde1");
            ebiteration = (int) getvalue("strategie.eindband");
        } else if (getvalue("strategie.pitstops") == 3) {
            pitstopronde1 = (int) getvalue("strategie.pitstopronde1");
            pitstopronde2 = (int) getvalue("strategie.pitstopronde2");
            ebiteration = (int) getvalue("strategie.eindband");
            eebiteration = (int) getvalue("strategie.eindeindband");
        }
        sbiteration = (int) getvalue("strategie.startband");
        mbiteration = (int) getvalue("strategie.middenband");

        Output.CreateFile();
        Save("Softrondetijd:\tMediumrondetijd:\tHardrondetijd\tpitstoptijd\tmaxpitstops\ttotale ronden");
        Save(Softrondetijd + "\t" + Mediumrondetijd + "\t" + Hardrondetijd + "\t" + pitstoptijd + "\t" + pitstops + "\t" + totronde);
        Save("ronde \t bandentype \t rondentijd \t bandronde");


            strategie(pitstops);


        Write();
        System.exit(1);
    }

    private static void imp_strat(String reden) {
        if (reden.equalsIgnoreCase("pitstoprondes")) {
            System.out.println("onmogelijke strategie! pitstopronde is kleinder dan pitstopronde1");
            System.exit(0);
        }
        if (reden.equalsIgnoreCase("bandenkeuze")) {
            System.out.println("onmogelijke strategie! er moeten 2 of meer verschillende bandentypes gebruikt worden!");
            System.exit(0);
        }
        System.out.println("onbekende fout opgetreden:" + reden);
        System.exit(0);
    }

    private static String bandentype(int type){
        String bandentype = null;
        if(type == 3) {
            bandentype = "Hard";
        } else if(type == 4) {
            bandentype = "Medium";
        } else if(type == 5) {
            bandentype = "Soft";
        }
        return bandentype;
    }

    private static void ronde(int ronde, int sbiteration, int ebiteration, int pitstopronde) {
        double rondetijd;
        String bandentype;
        if (ronde <= pitstopronde) {
            rondetijd = rondetijd(ronde, sbiteration, ronde);
            if (ronde == pitstopronde) {rondetijd += pitstoptijd;}
            totalrt += rondetijd;
            bandentype = bandentype(sbiteration);
            Save(ronde + "\t" + bandentype + "\t" + rondetijd + "\t" + ronde + "\t" + totalrt);
        } else {
            rondetijd = rondetijd(ronde, ebiteration, ronde);
            totalrt += rondetijd;
            bandentype = bandentype(ebiteration);
            Save(ronde + "\t" + bandentype + "\t" + rondetijd + "\t" + (ronde-pitstopronde) + "\t" + totalrt);
        }
    }

    private static void ronde(int ronde, int sbiteration, int mbiteration, int ebiteration, int pitstopronde, int pitstopronde1) {
        double rondetijd;
        String bandentype;
        if(ronde <= pitstopronde) {
            rondetijd = rondetijd(ronde, sbiteration, ronde);
            if (ronde == pitstopronde) {rondetijd += pitstoptijd;}
            totalrt += rondetijd;
            bandentype = bandentype(sbiteration);
            Save(ronde + "\t" + bandentype + "\t" + rondetijd + "\t" + ronde + "\t" + totalrt);
        } else if (ronde <= pitstopronde1) {
            rondetijd = rondetijd(ronde, mbiteration, ronde - pitstopronde);
            if (ronde == pitstopronde1) {rondetijd += pitstoptijd;}
            totalrt += rondetijd;
            bandentype = bandentype(mbiteration);
            Save(ronde + "\t" + bandentype + "\t" + rondetijd + "\t" + (ronde-pitstopronde) + "\t" + totalrt);
        } else {
            rondetijd = rondetijd(ronde, ebiteration, ronde - pitstopronde1);
            totalrt += rondetijd;
            bandentype = bandentype(ebiteration);
            Save(ronde + "\t" + bandentype + "\t" + rondetijd + "\t" + (ronde-pitstopronde1) + "\t" + totalrt);
        }
    }

    private static void ronde(int ronde, int sbiteration, int mbiteration, int ebiteration, int eebiteration, int pitstopronde, int pitstopronde1, int pitstopronde2) {
        double rondetijd;
        String bandentype;
        if(ronde <= pitstopronde) {
            rondetijd = rondetijd(ronde, sbiteration, ronde);
            if (ronde == pitstopronde) {rondetijd += pitstoptijd;}
            totalrt += rondetijd;
            bandentype = bandentype(sbiteration);
            Save(ronde + "\t" + bandentype + "\t" + rondetijd + "\t" + ronde + "\t" + totalrt);
        } else if (ronde <= pitstopronde1) {
            rondetijd = rondetijd(ronde, mbiteration, ronde - pitstopronde);
            if (ronde == pitstopronde1) {rondetijd += pitstoptijd;}
            totalrt += rondetijd;
            bandentype = bandentype(mbiteration);
            Save(ronde + "\t" + bandentype + "\t" + rondetijd + "\t" + (ronde-pitstopronde) + "\t" + totalrt);
        } else if (ronde <= pitstopronde2){
            rondetijd = rondetijd(ronde, ebiteration, ronde - pitstopronde1);
            if (ronde == pitstopronde2) {rondetijd += pitstoptijd;}
            totalrt += rondetijd;
            bandentype = bandentype(ebiteration);
            Save(ronde + "\t" + bandentype + "\t" + rondetijd + "\t" + (ronde-pitstopronde1) + "\t" + totalrt);
        } else {
            rondetijd = rondetijd(ronde, eebiteration, ronde - pitstopronde2);
            totalrt += rondetijd;
            bandentype = bandentype(eebiteration);
            Save(ronde + "\t" + bandentype + "\t" + rondetijd + "\t" + (ronde-pitstopronde1) + "\t" + totalrt);
        }
    }

    private static void strategie(int pitstops) {
        totalrt = 0;
        if (pitstops == 1) {
            if(sbiteration == mbiteration) {imp_strat("bandenkeuze");}
            for (int ronde = 0; ronde <= totronde; ronde++) {
                ronde(ronde, sbiteration, mbiteration, pitstopronde);
            }

        } else if (pitstops == 2) {
            if (pitstopronde >= pitstopronde1) {imp_strat("pitstoprondes");}
            for (int ronde = 0; ronde <= totronde; ronde++) {
                ronde(ronde, sbiteration, mbiteration, ebiteration, pitstopronde, pitstopronde1);
            }

        } else if (pitstops == 3) {
            if (pitstopronde >= pitstopronde1 || pitstopronde1 >= pitstopronde2) {imp_strat("pitstoprondes");}
            for (int ronde = 0; ronde <= totronde; ronde++) {
                ronde(ronde, sbiteration, mbiteration, ebiteration, eebiteration, pitstopronde, pitstopronde1, pitstopronde2);
            }
        }
    }
}