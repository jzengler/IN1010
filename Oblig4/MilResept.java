class MilResept extends Hvit{

  //Klassevariabler
  private final static int PRIS = 0;

  //Instansvariabler

  //Konstruktør
  MilResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
    super(legemiddel, utskrivendeLege, pasient, reit);
  }


  //Metoder
  public String farge(){
    return RESEPT_FARGE + " - MIL" ;
  }

  public double prisAaBetale(){
    return PRIS;
  }
}
