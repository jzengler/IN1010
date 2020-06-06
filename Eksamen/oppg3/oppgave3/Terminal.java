import java.util.Scanner;
import java.util.Random;

class Terminal implements Brukergrensesnitt{
    // KLASSEVARIABLER



    // INSTANSVARIABLER
    Scanner skanner;
    Random r = new Random();


    // KONSTRUKTOER
    Terminal(Scanner skanner){
        this.skanner = skanner;
    }


    // METODER
    // skriv ut status-streng
    public void giStatus(String status){
        System.out.println(status);
        // vent paa at bruker trykker enter for aa fortsette
        // skanner.nextLine();
    }

    // returner indeks for alternativ
    // jeg syntes det ga mening at spilleren kan velge gjenstand fra sekken, men blir tildelt tilfeldig fra kisten.
    // Da ble spillet litt mer interaktivt.
    // er ikke helt fornoyd med at metoden returnerer -1 for aa fange opp naar spiller svarer nei eller Skattkiste er tom
    public int beOmKommando(String spoersmaal, String[] alternativer){

        // dummy-verdier for aa kjore looper
        String taUt = "";
        int valg = -1;

        // skriv ut spoersmaal
        System.out.println(spoersmaal);
        // skriv ut svar-hint
        System.out.println("Ja [J], nei [N]?");

        // valider svaret fra bruker
        // ber om ny input hvis det ikke matcher
        while(taUt.equals("")){

            // hent neste ord
            taUt = skanner.next().toUpperCase();

            // bruker vil ikke selge/ta noe
            if("NEI".contains(taUt)){
                return -1;
            }
            // maa fange opp ja saa ikke loopen fortsetter
            // ugyldig input, loop igjen
            else if(!"JA".contains(taUt)){
                System.out.println("Forstod ikke svaret... Skriv en gang til");
                taUt = "";
            }

        }



        //  sjekk om sekken/kisten tom, returner hvis den er det
        int antall = alternativer.length;

        if(antall == 0){
            System.out.println("Huff da! Der var det helt tom!");
            return -1;
        }



        // tillat spiller aa velge gjenstand hvis det er fra sekken
        if(spoersmaal.contains("sekk")){

            // skriv ut alle alternativene
            for(int i = 0; i < antall; i++){
                System.out.println(i + ": " + alternativer[i].toUpperCase());
            }

            // loop til bruker velger en gyldig indeks
            while( valg == -1 ){

                System.out.println("Velg gjenstand [heltall]");

                // sjekk om neste input er en int
                if( skanner.hasNextInt() ){
                    valg = skanner.nextInt();
                }
                else{
                    // les neste saa ikke programmet venter paa input
                    // streng brukes ikke
                    skanner.next();
                }


                // out of bounds - skriv ut feilmelding og sett dummy saa loopen gjentar seg
                if( valg < 0 || valg >= antall){
                    System.out.println("Ugyldig valg! Skriv inn ett tall fra listen med alternativer!");
                    valg = -1;
                }

            }
        }
        // velg noe tilfeldig hvis det er fra kisten
        // innholdet i kisten vises heller ikke til spiller
        else{
            valg = r.nextInt(antall);
        }

        // ikke en pen loesning, returner -1 som er out of bounds hvis bruker velger nei
        return valg;
    }

}
