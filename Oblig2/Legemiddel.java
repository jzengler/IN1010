abstract class Legemiddel{



  //Klassevariabler
  protected static int antall = 0;



  //Instansvariabler
  protected int id = 0;
  protected String navn;
  protected double pris;
  protected double virkestoff;



  //Konstruktør
  protected Legemiddel(String navn, double pris, double virkestoff){
    //inkrementer antall etter id er tilordnet verdien
    id = antall ++;
    this.navn = navn;
    this.pris = pris;
    this.virkestoff = virkestoff;
  }



  //Metoder
  public String toString(){
    return ("LEGEMIDDEL\n" +
            "Preparat: " + hentNavn() + " " + hentVirkestoff() + "mg\n"
            + "ID: " + hentID() + " , Pris: " + hentPris() + "kr");
  }

  protected int hentID(){
    return id;
  }

  protected String hentNavn(){
    return navn;
  }

  protected double hentPris(){
    return pris;
  }

  protected double hentVirkestoff(){
    return virkestoff;
  }

  protected void settNyPris(double nyPris){

    pris = nyPris;

    return;
  }
}
