import java.util.Scanner;

class Terminal implements Brukergrensesnitt{
    // KLASSEVARIABLER



    // INSTANSVARIABLER
    Scanner skanner;


    // KONSTRUKTOER
    Terminal(Scanner skanner){
        this.skanner = skanner;

    }


    // METODER
    // skriv ut status-streng
    public void giStatus(String status){
        System.out.println(status);
    }

    // returner indeks for alternativ
    public int beOmKommando(String spoersmaal, String[] alternativer){

        // skriv ut alternativene til terminal
        for(int i = 0; i < alternativer.length; i++){
            System.out.println(i + ": " + alternativer[i]);
        }

        // dummy verdi for aa starte while-loekken
        int valg = -1;

        // saa lenge "valg" ikke er en int eller en indeks i alternativer be om ny input
        while( valg < 0 || valg >= alternativer.length){

            // sjekk om neste input er en int
            if(skanner.hasNextInt() ){
                valg = skanner.nextInt();
            }
            // skriv ut feilmelding, hent neste verdi og sett dummy saa loopen gjentar seg
            else{
                System.out.println("Ugyldig valg! Skriv inn ett tall:");
                skanner.next();
                valg = -1;
            }
        }

        // skriv ut alternativet og returner indeksen
        giStatus("Valgte: " + alternativer[valg]);
        return valg;
    }

}
