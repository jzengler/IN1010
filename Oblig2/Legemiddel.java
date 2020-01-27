abstract class Legemiddel{



  //Instansvariabler
  protected static int id = -1;
  protected String navn;
  protected double pris;
  protected double virkestoff;



  //Konstruktør
  protected void Legemiddel(String navn, double pris, double virkestoff){
    id ++;
    this.navn = navn;
    this.pris = pris;
    this.virkestoff = virkestoff;
  }



  //Metoder
  public String toString(){
    return ("Preparat: " + navn + " " + virkestoff + "mg\n"
            + "ID: " + id + " , Pris: " + pris + "kr");
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

  protected void settNyPris(){
    return;
  }
}
