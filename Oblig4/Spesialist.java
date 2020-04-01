class Spesialist extends Lege {



  //Instansvariabler
  // private int kontrollID; - Flyttet til lege. Gir mer mening


  //Konstruktoer
  Spesialist(String navn, int kontrollID){
    super(navn);
    this.kontrollID = kontrollID;
  }


  //metoder
  public String toString(){
    return super.toString() + "\nKontroll ID: " + hentKontrollID();
  }

  // hentKontrollID er flyttet til lege. Gir mer mening.
  // public int hentKontrollID(){
  //   return kontrollID;
  // }
}
