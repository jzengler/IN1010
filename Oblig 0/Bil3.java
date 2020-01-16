class Bil3 {

  //deklarering av instansvariabel
  private String bilNummer;

  //konstruktør
  Bil3(String bilNummer){
    this.bilNummer = bilNummer;
  }

  // metode for å skrive ut bilnummer til terminal
  public void skrivUt() {
    System.out.println(this.bilNummer);
  }

  // metode for returnere bilnummer
  public String hentNummer() {
    return this.bilNummer;
  }
}
