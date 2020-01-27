public class PreparatB extends Legemiddel{



  //Instansvariabler
  private int vanedannendeStyrke;



  //Konstruktør
  PreparatB(String navn, double pris, double virkestoff, int styrke){
    super.Legemiddel(navn, pris, virkestoff);
    vanedannendeStyrke = styrke;
  }



  //Metoder
  @Override
  public String toString(){

    return (super.toString() + "\nVanedannende styrke: " + vanedannendeStyrke);
  }

  public int hentVanedannendeStyrke(){
    return vanedannendeStyrke;
  }
}
