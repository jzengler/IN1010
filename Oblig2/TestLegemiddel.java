public class TestLegemiddel{


  public static void main(String [] args){

    Narkotisk narkotisk = new Narkotisk("Metadon", 1000.00 , 150.00, 4);
    Vanedannende vanedannende = new Vanedannende("Morfin", 1500.99, 20.99, 8);
    Vanlig vanlig = new Vanlig("Penicillin", 248.50 , 5.5);

    //Skriv ut objektene narkotisk, vanedannende, vanlig
    System.out.println("Skriver ut preparatene med tilpasset toString-metode\n"
                    + "######################################################\n"
                    + "#  LEGEMIDDEL                                        #\n"
                    + "#  ID: <heltall id> , Pris: <flyttall pris>kr        #\n"
                    + "#  Preparat: <streng navn> <flyttall virkemiddel>mg  #\n"
                    + "#  [narkotisk/vanedannende styrke: <heltall>]        #\n"
                    + "######################################################\n");

    System.out.println(narkotisk + "\n");
    System.out.println(vanedannende + "\n");
    System.out.println(vanlig + "\n");

    System.out.println("Skriver ut returverdiene til metodene i Legemiddel-klassen for alle tre preparat-objektene");
    testLegemiddel(narkotisk);
    testLegemiddel(vanedannende);
    testLegemiddel(vanlig);

    System.out.println("Heltall fra hentNarkotiskStyrke i narkotisk-objektet");
    testNarkotisk(narkotisk);
    System.out.println("Heltall fra hentVanedannendeStyrke i vanedannende-objektet");
    testVanedannende(vanedannende);


  }

  //Test-metoder
  static void testLegemiddel(Legemiddel preparat){
    System.out.println();
    System.out.println(preparat.hentID());
    System.out.println(preparat.hentNavn());
    System.out.println(preparat.hentPris());
    System.out.println(preparat.hentVirkestoff());
    System.out.println();
  }

  static void testNarkotisk(Narkotisk narkotisk){
    System.out.println(narkotisk.hentNarkotiskStyrke());
  }

  static void testVanedannende(Vanedannende vanedannende){
    System.out.println(vanedannende.hentVanedannendeStyrke());
  }

}
