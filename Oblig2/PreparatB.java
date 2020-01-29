public class PreparatB extends Legemiddel{



  //Instansvariabler
  private int vanedannendeStyrke;
  public final String PREPARAT_KLASSE = "Klasse B preparat";


  //Konstruktør
  PreparatB(String navn, double pris, double virkestoff, int styrke){
    //kall konstruktøren til superklassen Legemiddel
    super(navn, pris, virkestoff);
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
