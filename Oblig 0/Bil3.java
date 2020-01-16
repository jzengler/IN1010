public class Bil3 {

  private String bilNummer;

  Bil3(String bilNummer){
    this.bilNummer = bilNummer;
  }

  public void skrivUt() {
    System.out.println(this.bilNummer);
  }

  public String hentNummer() {
    return this.bilNummer;
  }
}
