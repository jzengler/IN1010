class Person{

  // Deklarerer de private instansvariablene navn på personen og objektreferanse til personens bil
  private String navn;
  private Bil3 bil;

  // Konstruktør
  Person(String navn, Bil3 bil){

    //initialiser instansvariablene med argumentene
    this.navn = navn;
    this.bil = bil;

  }

  // metode for å skrive ut bilNummer knyttet til personen
  public void skrivUtBilNummer(){
    System.out.println(this.bil.hentNummer());
  }
}
