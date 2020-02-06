class Hvit extends Resept{



  //Instansvariabler
  protected final String RESEPT_FARGE = "HVIT RESEPT";


  //Konstruktør
  Hvit(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
    super(legemiddel, utskrivendeLege, pasientId, reit);

  }


  //Metoder
  public String farge(){
    return RESEPT_FARGE;
  }

  public double prisAaBetale(){
    return hentLegemiddel().pris;
  }
}
