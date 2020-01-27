public class TestPreparat{


  public static void main(String [] args){

    PreparatA prepA = new PreparatA("Metadon", 1000.00 , 150.00, 4);
    System.out.println(prepA);
    System.out.println();
    System.out.println(prepA.hentID());
    System.out.println(prepA.hentNavn());
    System.out.println(prepA.hentPris());
    System.out.println(prepA.hentVirkestoff());
    System.out.println(prepA.hentNarkotiskStyrke());
    System.out.println();

    PreparatB prepB = new PreparatB("Morfin", 1500.99, 20.99, 8);
    System.out.println(prepB);
    System.out.println();
    System.out.println(prepB.hentID());
    System.out.println(prepB.hentNavn());
    System.out.println(prepB.hentPris());
    System.out.println(prepB.hentVirkestoff());
    System.out.println(prepB.hentVanedannendeStyrke());
    System.out.println();

    PreparatC prepC = new PreparatC("Penicillin", 248.50 , 5.5);
    System.out.println(prepC);
    System.out.println();
    System.out.println(prepC.hentID());
    System.out.println(prepC.hentNavn());
    System.out.println(prepC.hentPris());
    System.out.println(prepC.hentVirkestoff());
    System.out.println();

  }


}
