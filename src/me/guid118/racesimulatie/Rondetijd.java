package me.guid118.racesimulatie;



import static me.guid118.racesimulatie.Main.*;

public class Rondetijd {
    static private double laptime;


    public static double rondetijd(int ronde, int bandentype, int bandronde) {

        if (bandentype == 5) {
            laptime = Softrondetijd;
        } else if (bandentype == 4) {
            laptime = Mediumrondetijd;
        } else if (bandentype == 3) {
            laptime = Hardrondetijd;
        } else {
            System.out.println("onbekend bandentype, Hard wordt gebruikt");
            laptime = Hardrondetijd;
        }
        laptime = laptime + Brandstof.Brandstoftijd(ronde) + Bandenslijtage.slijtage(ronde, bandronde, bandentype);
        return laptime;
    }


}
