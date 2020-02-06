public class Vanlig extends Legemiddel{



  //Instansvariabler
  public final String PREPARAT_KLASSE = "Klasse C preparat";


  //Konstruktør
  Vanlig(String navn, double pris, double virkestoff){
    //kall konstruktøren til superklassen Legemiddel
    super(navn, pris, virkestoff);
  }
}
