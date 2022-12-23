package me.guid118.racesimulatie;


import static me.guid118.racesimulatie.Main.*;

public class Brandstof {


    public static double Brandstoftijd(int ronde) {
        double tijd;
        int rondentegaan;

        brandstofperronde = startbrandstof / totronde;
        rondentegaan = totronde - ronde;
        tijd = rondentegaan * brandstofperronde;
        return tijd;
    }


}
