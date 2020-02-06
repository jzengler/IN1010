public class TestResepter{


  public static void main(String [] args){

  //Oppretter legemiddel objekter
  Narkotisk metadon = new Narkotisk("Metadon", 1000.00 , 150.00, 4);
  Vanedannende morfin = new Vanedannende("Morfin", 1500.99, 20.99, 8);
  Vanlig penicillin = new Vanlig("Penicillin", 248.50 , 5.5);
  Vanlig nuvaRing = new Vanlig("NuvaRing", 420.0, 2.0);

  //oppretter et objekt lege som placeholder til resept
  Lege lege = new Lege();

  //oppretter hvit og blaa resept
  Hvit hvit = new Hvit(penicillin, lege, 1111, 1);
  Blaa blaa = new Blaa(metadon, lege, 2222, 2);

  //skriver ut reseptene, benytter tilpasset toString()
  System.out.println(hvit + "\n");
  System.out.println(blaa + "\n");

  //oppretter underkategoriene av hvit resept - p og mil
  PResept pResept = new PResept(nuvaRing, lege, 3333);
  MilResept milResept = new MilResept(morfin, lege, 4444, 4);

  // skriver ut ved bruk av toString()
  System.out.println(pResept + "\n");
  System.out.println(milResept + "\n");

  //tester at bruk av resepten returner false naar reit er 0
  System.out.println("Hent reit fra objektet \"hvit\" , bruk resept, hent reit og bruk igjen");
  System.out.println("Forventer resultatet: 1, true, 0, false");
  System.out.println(hvit.hentReit());
  System.out.println(hvit.bruk());
  System.out.println(hvit.hentReit());
  System.out.println(hvit.bruk());


  }
}
