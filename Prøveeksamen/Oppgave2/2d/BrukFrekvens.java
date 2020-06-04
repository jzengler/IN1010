import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class BrukFrekvens{

    public static void main(String[] args) {


        String filnavn;

        if (args.length == 1) {
            filnavn = args[0];
        }
        else{
            System.out.println("riktig bruk er 'java BrukFrekvens <filbane>'");
            return;
        }

        Frekvens frekvens = new Frekvens(lesFil(filnavn));
        frekvens.finnFlest();
        System.out.println(frekvens.hentFlest() + " forekom " + frekvens.hentAntall() + " ganger\n");





    }

    public static ArrayList<String> lesFil(String filnavn){

        ArrayList<String> tekster = new ArrayList<String>();

        Scanner scanner = null;

        try {
            scanner = new Scanner(new File(filnavn));
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }

        while (scanner.hasNextLine()){

            tekster.add(scanner.nextLine());

        }

        return tekster;

    }
}



class Frekvens{

    //INSTANSVARIABLER
    private ArrayList<String> tekster = new ArrayList<String>();
    private HashMap<String, Integer> forekomster = new HashMap<String, Integer>();

    private String flestTekst = "";
    private int flestAntall = 0;

    //KONSTRUKTOER
    Frekvens(ArrayList<String> tekster){
        this.tekster = tekster;
    }



    //METODER
    public void finnFlest(){

        for(String tekst : tekster){

            // intialverdi
            int init = 1;

            // sjekk om teksten finnes i hashmap og inkrementer intialverdi
            // hvis ikke legg til og bruk initialverdi
            if (forekomster.containsKey( tekst ) ){

                // hent verdi for noekkelen
                int j = forekomster.get(tekst);
                // inkrementer verdi for noekkelen
                forekomster.put(tekst, j + 1);
            }
            else{
                // ny forekomst med initialverdi 0
                forekomster.put(tekst, init);
            }
        }


        // opprett map til iterering av forekomster
        Map<String,Integer> map = new HashMap<String, Integer>();

        // loop gjennom forekomster
        for (Map.Entry<String,Integer> oppfoering : forekomster.entrySet()){

            // hent verdi for oppfoeringen
            int x = oppfoering.getValue();

            // hvis verdien er større enn den eksisterende legg til ny tekst og antallet forekomster
            if( x > flestAntall  ){
                flestTekst = oppfoering.getKey();
                flestAntall = x;
            }

        }
    }

    public String hentFlest(){
        return flestTekst;
    }

    public int hentAntall(){
        return flestAntall;
    }
}
