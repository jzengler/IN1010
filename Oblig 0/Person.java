public class Person{

  private String navn;
  private Bil3 bil;

  Person(String navn, Bil3 bil){

    this.navn = navn;
    this.bil = bil;

  }

  public void skrivUtBilNummer(){
    System.out.println(this.bil.hentNummer());
  }
}
