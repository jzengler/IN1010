public class PreparatA extends Legemiddel{



  //Instansvariabler
  private int narkotiskStyrke;


  //Konstruktør
  PreparatA(String navn, double pris, double virkestoff, int styrke){
    super.Legemiddel(navn, pris, virkestoff);
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
