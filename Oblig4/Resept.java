abstract class Resept{



  //Klassevariabler
  private static int antall = 0;



  //Instansvariabler
  private int id;
  private Legemiddel legemiddel;
  private Lege utskrivendeLege;
  private Pasient pasient;
  private int reit;


  //Konstruktør
  protected Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
    id = antall ++;
    this.legemiddel = legemiddel;
    this.utskrivendeLege = utskrivendeLege;
    this.pasient = pasient;
    this.reit = reit;
  }


  //Metoder
  public String toString(){
    return (farge() +
            "\nID: "+ hentId() + " , Pris aa betale: " + prisAaBetale() + "kr" +
            "\nLege: " + hentLege() +
            "\nPasient: " + hentPasientId() +
            "\nreit: " + hentReit() +
            "\n" + hentLegemiddel());
  }

  protected int hentId(){
    return id;
  }
  protected Legemiddel hentLegemiddel(){
    return legemiddel;
  }
  protected Lege hentLege(){
    return utskrivendeLege;
  }
  protected int hentPasientId(){
    return pasient.hentId();
  }
  protected int hentReit(){
    return reit;
  }

  public boolean bruk(){

    if(reit > 0){
      reit --;
      return true;
    }
    else{
      return false;
    }
  }

  abstract public String farge();

  abstract public double prisAaBetale();
}
