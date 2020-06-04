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
        System.out.println("\n" + status);
    }

    // returner indeks for alternativ
    public int beOmKommando(String spoersmaal, String[] alternativer){

        // skriv ut spoersmaal og alternativene til terminal
        System.out.println("\n" + spoersmaal);

        for(int i = 0; i < alternativer.length; i++){
            System.out.println(i + ": " + alternativer[i]);
        }


        // dummy-verdi for aa starte while-loekken
        int valg = -1;

        // saa lenge "valg" ikke er en indeks
        while( valg < 0 || valg >= alternativer.length ){

            // sjekk om neste input er en int
            if( skanner.hasNextInt() ){
                valg = skanner.nextInt();
            }
            else{
                // les neste, ikke bruk
                skanner.next();
            }

            // skriv ut alternativet hvis "valg" er en indeks
            if( valg >= 0 && valg < alternativer.length){
                giStatus("Valgte: " + alternativer[valg]);
            }
            // skriv ut feilmelding og sett dummy saa loopen gjentar seg
            else{
                System.out.println("Ugyldig valg! Skriv inn ett tall fra listen med alternativer!");
                valg = -1;
            }
        }


        return valg;
    }

}
