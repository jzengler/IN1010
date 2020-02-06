public class Narkotisk extends Legemiddel{



  //Instansvariabler
  private int narkotiskStyrke;


  //Konstruktør
  Narkotisk(String navn, double pris, double virkestoff, int styrke){
    //kall konstruktøren til superklassen Legemiddel
    super(navn, pris, virkestoff);
    narkotiskStyrke = styrke;
  }



  //Metoder
  @Override
  public String toString(){

    return (super.toString() + "\nNarkotisk styrke: " + hentNarkotiskStyrke());
  }

  public int hentNarkotiskStyrke(){
    return narkotiskStyrke;
  }
}
