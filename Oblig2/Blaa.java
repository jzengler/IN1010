class Blaa extends Resept{



  //Instansvariabler
  protected final String RESEPT_FARGE = "BLAA RESEPT";


  //Konstruktør
  Blaa(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
    super(legemiddel, utskrivendeLege, pasientId, reit);
  }


  //Metoder
  public String farge(){
    return RESEPT_FARGE;
  }

  public double prisAaBetale(){
    return hentLegemiddel().pris * 0.25;
  }
}
