// Det stod i oppgavetekste at kun en spiller skal skrive ut av gangen
// Jeg tolket det slik at alle utskrifter en spiller utfoerer i ett trekk skal vaere uavbrutt
// Det gir oversiktlig kjoering men foerte ogsaa til at jeg valgte aa blokke traader for System.layout
// Ettersom det er mange utskrifter stroedd ut i hele metoden nyttTrekk ble det i praksis blokking for hele metoden
// Det hindrer hvertfall race condition der gjenstander blir tatt ut/lagt ned i kiste av andre traader mellom der spilleren utfoerer salg/ta handling


class Spiller implements Comparable<Spiller>{
    // KLASSEVARIABLER



    // INSTANSVARIABLER
    protected Sted her;
    protected Brukergrensesnitt grensesnitt;
    protected String navn;
    protected Skattkiste ryggsekk;
    protected int plassISekk = Spillkontroll.PLASS_I_SEKK;
    protected int formue = 0;

    // KONSTRUKTOER
    Spiller(String navn, Sted her, Brukergrensesnitt grensesnitt){
        this.navn = navn;
        this.her = her;
        this.grensesnitt = grensesnitt;

        // Opprett ryggsekk med noen tilfeldige antall ting
        ryggsekk = new Skattkiste( Terreng.gjenstander );
        // juster antall ledige plasser i sekken
        plassISekk = plassISekk - ryggsekk.antallGjenstander();
    }

    // METODER
    public void nyttTrekk(){

        // blokker de andre traadene fra aa bruke System.out
        // tilnaermet 0 parallelitet da hele metoden er dekket fordi det er mye utskrift
        // i tillegg er metodene i klassen kiste syncronized
        synchronized(System.out){
        // oppdater spillet med sted og funn av kiste
        grensesnitt.giStatus("\n" + hentNavn() + her.toString());
        grensesnitt.giStatus(hentNavn() + "Du fant en kiste!");

        // hent kisten fra stedet
        Skattkiste kiste = her.hentSkattkiste();

        // her kunne det vaert bedre aa ta fra kisten foerst for aa unngaa at spiller kan faa tilbake samme gjenstand

        // selge noe? trenger du baNaNer?
        String[] selg = innhold(ryggsekk);

        // hent indeks for gjenstanden som skal legges ned, returnerer -1 hvis tom eller nei
        int indeks = grensesnitt.beOmKommando("\n" + hentNavn() + "Vil du selge noe fra ryggsekken?", selg);


        // gyldig indeks
        if(indeks >= 0){

            Gjenstand solgt = ryggsekk.taUtGjenstand(indeks);

            // ta ut av sekk, legg ned i kiste
            // svaket her er at spiller legger noe i kisten foerst, det er derfor mulig aa faa samme gjenstand tilbake
            int verdi = kiste.leggNedGjenstand( solgt );

            // oek formuen fra salget
            formue += verdi;

            // tingen som ble solgt'
            // String solgt = kiste.innhold.get( kiste.antallGjenstander() - 1 ).toString().toUpperCase();

            // hva ble solgt og hva fikk spilleren for det
            grensesnitt.giStatus(hentNavn() + "Fikk " + verdi + " baNaNer for ##" + solgt.toString().toUpperCase() + "##");

            // en plass mer i sekken
            plassISekk++;
        }


        // ta noe?

        // opprett string array for innholdet
        String[] ta = innhold(kiste);

        // send til brukergrensesnitt
        indeks = grensesnitt.beOmKommando("\n" + hentNavn() + "Vil du aapne kisten?", ta);


        // spiller svarte nei
        if(indeks == -1){
            grensesnitt.giStatus(hentNavn() + "Det fristet ikke denne gangen...");
        }
        // plass i sekken?
        else if(ryggsekk.antallGjenstander() >= 0 && ryggsekk.antallGjenstander() < plassISekk){

            Gjenstand fikk = kiste.taUtGjenstand(indeks);

            // ta ut av kisten, legg ned i sekken
            ryggsekk.leggNedGjenstand( fikk );

            // streng av innholdet fra kisten, for lesbarhet
            String ting = fikk.toString().toUpperCase();

            // skriv ut info om tingen
            grensesnitt.giStatus(hentNavn() + "Du fikk ##" + ting + "## av den magiske kisten!");

            // en plass mindre i sekken
            plassISekk--;
        }
        else{
            grensesnitt.giStatus(hentNavn() + "Oi, det var vist ikke plass i sekken... Du maa nok selge noe");
        }

    }
        her = her.gaaVidere();
    }

    public int hentFormue(){
        return formue;
    }

    public String hentNavn(){
        return "[" + navn + "]";



    }

    public String toString(){
        return hentNavn() + " " + hentFormue() + " baNaNer";
    }

    //  bygg streng array for kiste-innhold
    private String[] innhold(Skattkiste kiste){

        String[] alternativer = new String[ kiste.antallGjenstander() ];

        for(int i = 0; i < alternativer.length; i++){
            alternativer[i] = kiste.innhold.get(i).toString();
        }

        return alternativer;
    }

    // sammenlign formue med spiller
    // synkende rekkefølge
    @Override
    public int compareTo(Spiller s){

        return s.hentFormue() - formue;

    }
}
