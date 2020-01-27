class BilBruk3{

  public static void main(String [] args){

    // oppretter ett nytt objekt av klassen Bil3
    Bil3 bil = new Bil3("YE23456");

    // oppretter ett nytt objekt av klassen Person, referanse til objektet bil sendes med
    Person eier = new Person("Ola Dunk", bil);

    // skriver ut bilnummer på bilen knyttet til personen ved bruk av metode i klassen Person
    eier.skrivUtBilNummer();

  }
}
