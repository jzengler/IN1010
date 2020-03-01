public class Spesialist extends Lege implements Godkjenningsfritak{



  //Instansvariabler
  private int kontrollID;


  //Konstruktoer
  Spesialist(String navn, int kontrollID){
    super(navn);
    this.kontrollID = kontrollID;
  }


  //metoder
  public String toString(){
    return super.toString() + ", kontroll ID: " + hentKontrollID();
  }

  public int hentKontrollID(){
    return kontrollID;
  }
}
