class Hvit extends Resept{



  //Instansvariabler
  protected final String RESEPT_FARGE = "HVIT";


  //Konstruktør
  Hvit(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
    super(legemiddel, utskrivendeLege, pasient, reit);

  }


  //Metoder
  public String farge(){
    return RESEPT_FARGE;
  }

  public double prisAaBetale(){
    return hentLegemiddel().pris;
  }
}
