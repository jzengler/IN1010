class Integrasjonstest{


  public static void main(String [] args){

    //Opprett legemidler
    Narkotisk nark = new Narkotisk("Metadon", 1000.00 , 150.00, 4);
    Vanedannende vane = new Vanedannende("Morfin", 1500.99, 20.99, 8);
    Vanlig norm1 = new Vanlig("Penicillin", 248.50 , 5.5);
    Vanlig norm2 = new Vanlig("NuvaRing", 420.0, 2.0);


    //Opprett lege og spesialt
    Lege lege = new Lege("Doctor Mike");
    Spesialist spes = new Spesialist("Dr. House", 123);

    //Opprett resepter
    Hvit hvit = new Hvit(norm1, lege, 1111, 1);
    Blaa blaa = new Blaa(nark, spes, 2222, 2);
    PResept pResept = new PResept(norm2, lege, 3333);
    MilResept milResept = new MilResept(vane, spes, 4444, 4);


    //Skriv ut hvit resept, norm1 legemiddel, lege
    System.out.println(hvit + "\n");
    //Skriv ut blaa resept, nark legemiddel, spesialist
    System.out.println(blaa + "\n");
    //Skriv ut p resept, norm2, legemiddel, lege
    System.out.println(pResept + "\n");
    //Skriv ut mil resept, vane legemiddel, spesialist
    System.out.println(milResept + "\n");
  }
}
