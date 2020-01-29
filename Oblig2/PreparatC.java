public class PreparatC extends Legemiddel{



  //Instansvariabler
  public final String PREPARAT_KLASSE = "Klasse C preparat";


  //Konstruktør
  PreparatC(String navn, double pris, double virkestoff){
    //kall konstruktøren til superklassen Legemiddel
    super(navn, pris, virkestoff);
  }
}
