class VeivalgSted extends Sted{
    // KLASSEVARIABLER



    // INSTANSVARIABLER
    VeivalgSted[] utganger = new VeivalgSted[3];
    String[] retninger = {"rett frem", "hoyre", "venstre"};


    // KONSTRUKTOER
    VeivalgSted(String beskrivelse){
        super(beskrivelse);
    }


    // METODER
    public String[] hentVeier(){
        return retninger;
    }


    public Sted gaaVidere(int indeks){

        return utganger[indeks];
    }

    public void settVeier(VeivalgSted rettFrem, VeivalgSted hoyre, VeivalgSted venstre){
        utganger[0] = rettFrem;
        utganger[1] = hoyre;
        utganger[2] = venstre;

    }

}
