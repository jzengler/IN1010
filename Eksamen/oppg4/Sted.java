class Sted{
    // KLASSEVARIABLER



    // INSTANSVARIABLER
    protected String beskrivelse;
    protected Skattkiste kiste;
    protected Sted nesteSted;


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

    Sted gaaVidere(){
        return nesteSted;
    }

    @Override
    public String toString(){
        return beskrivelse;
    }
}
