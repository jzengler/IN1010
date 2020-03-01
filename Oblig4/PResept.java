class PResept extends Hvit{


  //Instansvariabler
  private final static int RABATT = 108;

  //Konstruktør
  PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient){
    super(legemiddel, utskrivendeLege, pasient, 3);
  }



  //Metoder
  public String farge(){
    return RESEPT_FARGE + " - P";
  }

  public double prisAaBetale(){
    double pris = hentLegemiddel().pris;

    if (pris > RABATT){
      return pris - RABATT;
    }
    else{
      return 0;
    }
  }
}
