public class TestPreparat{


  public static void main(String [] args){

    PreparatA prepA = new PreparatA("Metadon", 1000.00 , 150.00, 4);
    PreparatB prepB = new PreparatB("Morfin", 1500.99, 20.99, 8);
    PreparatC prepC = new PreparatC("Penicillin", 248.50 , 5.5);

    //Skriv ut objektene prepA, prepB, prepC
    System.out.println("Skriver ut preparatene med tilpasset toString-metode\n"
                    + "######################################################\n"
                    + "#  Preparat: <streng navn> <flyttall virkemiddel>mg  #\n"
                    + "#  ID: <heltall id> , Pris: <flyttall pris>kr        #\n"
                    + "#  [narkotisk/vanedannende styrke: <heltall>]        #\n"
                    + "######################################################\n");
    System.out.println(prepA + "\n");
    System.out.println(prepB + "\n");
    System.out.println(prepC + "\n");

    System.out.println("Skriver ut returverdiene til metodene i Legemiddel-klassen for alle tre preparat-objektene");
    testPreparat(prepA);
    testPreparat(prepB);
    testPreparat(prepC);

    System.out.println("Heltall fra hentNarkotiskStyrke i prepA-objektet");
    testPrepA(prepA);
    System.out.println("Heltall fra hentVanedannendeStyrke i prepB-objektet");
    testPrepB(prepB);


  }

  //Test-metoder
  static void testPreparat(Legemiddel prep){
    System.out.println();
    System.out.println(prep.hentID());
    System.out.println(prep.hentNavn());
    System.out.println(prep.hentPris());
    System.out.println(prep.hentVirkestoff());
    System.out.println();
  }

  static void testPrepA(PreparatA prepA){
    System.out.println(prepA.hentNarkotiskStyrke());
  }

  static void testPrepB(PreparatB prepB){
    System.out.println(prepB.hentVanedannendeStyrke());
  }

}
