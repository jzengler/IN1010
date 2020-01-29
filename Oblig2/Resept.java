abstract class Resept{



  //Klassevariabler
  private static int antall = 0;



  //Instansvariabler
  private int id;
  private Legemiddel legemiddel;
  private Lege utskrivendeLege;
  private int pasientId;
  private int reit;


  //Konstruktør
  protected Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
    id = antall ++;
    this.legemiddel = legemiddel;
    this.utskrivendeLege = utskrivendeLege;
    this.pasientId = pasientId;
    this.reit = reit;
  }


  //Metoder
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
    return pasientId;
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
