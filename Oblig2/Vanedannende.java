public class Vanedannende extends Legemiddel{



  //Instansvariabler
  private int vanedannendeStyrke;


  //Konstruktør
  Vanedannende(String navn, double pris, double virkestoff, int styrke){
    //kall konstruktøren til superklassen Legemiddel
    super(navn, pris, virkestoff);
    vanedannendeStyrke = styrke;
  }



  //Metoder
  @Override
  public String toString(){

    return (super.toString() + "\nVanedannende styrke: " + hentVanedannendeStyrke());
  }

  public int hentVanedannendeStyrke(){
    return vanedannendeStyrke;
  }
}
