package me.guid118.racesimulatie;


import static me.guid118.racesimulatie.Main.*;

public class Bandenslijtage {

    public static double slijtage(int ronde, int bandronde, int bandentype) {
        double a = 0.005 * (totronde / 2) + 0.75;
        multiplier = 0.005 * (ronde-bandronde) + a;

        double slijtage = 0;

        maxsoftgebruik = maxsoftgebruiks*multiplier;
        maxmediumgebruik = maxmediumgebruiks*multiplier;
        maxhardgebruik = maxhardgebruiks*multiplier;

        if (bandentype == 5) {
            if (bandronde <= maxsoftgebruik) {
                slijtage = (softdegradatie * bandronde) * multiplier;
            } else {
                slijtage = (softdegradatie * 2 * bandronde - 2.24) * multiplier;
            }
        } else if (bandentype == 4) {
            if (bandronde <= maxmediumgebruik) {
                slijtage = (mediumdegradatie * bandronde) * multiplier;
            } else {
                slijtage = (mediumdegradatie * 2 * bandronde - 2.97) * multiplier;
            }
        } else if (bandentype == 3) {
            if (bandronde <= maxhardgebruik) {
                slijtage = (harddegradatie * 2 * bandronde) * multiplier;
            } else {
                slijtage = (harddegradatie * 2 * bandronde - 1.9) * multiplier;
            }
        }
        return slijtage;
    }


}
