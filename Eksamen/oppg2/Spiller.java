class Spiller{
    // KLASSEVARIABLER



    // INSTANSVARIABLER
    private Sted her;
    private Brukergrensesnitt grensesnitt;
    private String navn;
    private Skattkiste ryggsekk;
    private int plassISekk = Spill.PLASS_I_SEKK;
    private int formue = 0;

    // KONSTRUKTOER
    Spiller(String navn, Sted her, Brukergrensesnitt grensesnitt){
        this.navn = navn;
        this.her = her;
        this.grensesnitt = grensesnitt;

        // Opprett ryggsekk med noen tilfeldige antall ting
        ryggsekk = new Skattkiste( Spill.gjenstander );
        // juster antall ledige plasser i sekken
        plassISekk = plassISekk - ryggsekk.antallGjenstander();
    }

    // METODER
    public void nyttTrekk(){


        // oppdater spillet med sted og funn av kiste
        grensesnitt.giStatus(hentNavn() + her.toString() );
        grensesnitt.giStatus(hentNavn() + "Du fant en kiste!");

        // hent kisten fra stedet
        Skattkiste kiste = her.hentSkattkiste();



        // selge noe? trenger du baNaNer?
        String[] selg = innhold(ryggsekk);

        // hent indeks for gjenstanden som skal legges ned, returnerer -1 hvis tom eller nei
        int indeks = grensesnitt.beOmKommando("Vil du selge noe fra ryggsekken?", selg);

        // sett inn tom linje for bedre lesbarhet i terminal
        System.out.println("");

        // gyldig indeks
        if(indeks >= 0){

            // ta ut av sekk, legg ned i kiste
            int verdi = kiste.leggNedGjenstand( ryggsekk.taUtGjenstand(indeks) );
            // oek formuen fra salget
            formue += verdi;

            // tingen som ble solgt
            String solgt = kiste.innhold.get( kiste.antallGjenstander() - 1 ).toString().toUpperCase();

            // hva ble solgt og hva fikk spilleren for det
            grensesnitt.giStatus(hentNavn() + "Solgte en " + solgt);
            grensesnitt.giStatus(hentNavn() + "Den var verdt " + verdi + " baNaNer!");

            // en plass mer i sekken
            plassISekk++;
        }


        // ta noe?
        // opprett string array for innholdet
        String[] ta = innhold(kiste);

        // send til brukergrensesnitt
        indeks = grensesnitt.beOmKommando("Vil du aapne kisten?", ta);

        // sett inn tom linje for bedre lesbarhet i terminal
        System.out.println("");

        // spiller svarte nei
        if(indeks == -1){
            grensesnitt.giStatus(hentNavn() + "Det fristet ikke denne gangen...");
        }
        // plass i sekken?
        else if(ryggsekk.antallGjenstander() >= 0 && ryggsekk.antallGjenstander() < plassISekk){
            // ta ut av kisten, legg ned i sekken
            ryggsekk.leggNedGjenstand( kiste.taUtGjenstand(indeks) );

            // streng av innholdet fra kisten, for lesbarhet
            String ting = ryggsekk.innhold.get( ryggsekk.antallGjenstander() - 1 ).toString().toUpperCase();

            // skriv ut info om tingen
            grensesnitt.giStatus(hentNavn() + "Se hva som spratt ut av kisten!\n" + ting);

            // en plass mindre i sekken
            plassISekk--;
        }
        else{
            grensesnitt.giStatus(hentNavn() + "Oi, det var vist ikke plass i sekken... Du maa nok selge noe");
        }


        grensesnitt.giStatus(hentNavn() + "Trasker videre...");
        her = her.gaaVidere();

    }

    public int hentFormue(){
        return formue;
    }

    public String hentNavn(){
        return "[" + navn + "]";



    }

    public String toString(){
        return hentNavn() + " har " + hentFormue() + " baNaNer i banken!";
    }

    //  bygg streng array for kiste-innhold
    private String[] innhold(Skattkiste kiste){

        String[] alternativer = new String[ kiste.antallGjenstander() ];

        for(int i = 0; i < alternativer.length; i++){
            alternativer[i] = kiste.innhold.get(i).toString();
        }

        return alternativer;
    }
}
