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

    public static String[] lesFil(String filnavn){

        String[] innData = new String[1000];
        int linjer = 0;

        Scanner scanner = null;

        try {
            scanner = new Scanner(new File(filnavn));
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }

        while (scanner.hasNextLine()){

            innData[linjer++] = scanner.nextLine();
        }

        String[] tekster = new String[linjer];

        for (int i = 0; i < tekster.length; i++ ){
            tekster[i] = innData[i];
        }

        return tekster;

    }
}



class Frekvens{

    class Node{

        public Node neste = null;
        String tekst;

        Node(String t){
            tekst = t;
        }

    }


    //INSTANSVARIABLER
    private Node start = null;
    private int antallNoder = 0;

    private String flestTekst = "";
    private int flestAntall = 0;


    //KONSTRUKTOER
    Frekvens(String[] tekster){

        // start med tom lenkeliste
        Node denne = start;

        // loop gjennom datasettet og sett inn noder i lenkelisten
        for(int i = 0; i < tekster.length; i++){

            Node nyNode = new Node(tekster[i]);
            antallNoder++;

            // hvis lenkelisten er tom
            if( start == null ){
                start = nyNode;
                denne = start;
            }
            else{
                denne.neste = nyNode;
                denne = denne.neste;
            }
        }
    }



    //METODER
    public void finnFlest(){

        Node denne = start;
        String forrigeTekst = denne.tekst;
        int denneAntall = 0;

        while(denne != null){

            if( forrigeTekst.equals(denne.tekst) ){
                denneAntall++;

            }
            else{
                denneAntall = 1;
            }


            if(denneAntall > flestAntall){
                flestAntall = denneAntall;
                flestTekst = denne.tekst;
            }

            forrigeTekst = denne.tekst;
            denne = denne.neste;
        }




    }

    public String hentFlest(){
        return flestTekst;
    }

    public int hentAntall(){
        return flestAntall;
    }
}
