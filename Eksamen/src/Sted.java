class Sted{
    // KLASSEVARIABLER



    // INSTANSVARIABLER
    String beskrivelse;
    private Skattkiste kiste;


    // KONSTRUKTOER
    Sted(String beskrivelse){
        this.beskrivelse = beskrivelse;
    }


    // METODER
    void leggTilSkattkiste(Skattkiste kiste){
        this.kiste = kiste;
    }

    Skattkiste hentSkattkiste(){
        return kiste;
    }

    Sted gaaVidere(Sted sted){
        return sted;
    }
}
