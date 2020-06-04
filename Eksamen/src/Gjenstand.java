class Gjenstand{
    // KLASSEVARIABLER



    // INSTANSVARIABLER
    private String type;
    private int verdi;



    // KONSTRUKTOER
    Gjenstand(String type, int verdi){
        this.type = type;
        this.verdi = verdi;
    }


    // METODER
    public int hentVerdi(){
        return verdi;
    }


    public String toString(){
        return type + " - " + verdi;
    }
}
