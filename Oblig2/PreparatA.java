public class PreparatA extends Legemiddel{



  //Instansvariabler
  private int narkotiskStyrke;
  public final String PREPARAT_KLASSE = "Klasse A preparat";


  //Konstruktør
  PreparatA(String navn, double pris, double virkestoff, int styrke){
    //kall konstruktøren til superklassen Legemiddel
    super(navn, pris, virkestoff);
    narkotiskStyrke = styrke;
  }



  //Metoder
  @Override
  public String toString(){

    return (super.toString() + "\nNarkotisk styrke: " + narkotiskStyrke);
  }

  public int hentNarkotiskStyrke(){
    return narkotiskStyrke;
  }
}
