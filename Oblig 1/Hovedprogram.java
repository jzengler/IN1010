public class Hovedprogram{

  public static void main(String [] args){


    String filnavn, navn;

    // bruk standard klynge hvis ikke spesifisert som argument
    if ( args.length == 0 ){
      navn = "Abel";
      filnavn = "regneklynge.txt";

    }
    else{
      navn = args[0];
      filnavn = args[1];
    }

    // opprett regneklyngen
    Regneklynge abel = new Regneklynge(navn, filnavn);


    // Kall på utskriftsmetoder
    System.out.println();
    abel.noderMedNokMinne(32);
    abel.noderMedNokMinne(64);
    abel.noderMedNokMinne(128);
    System.out.println();
    abel.antProsessorer();
    abel.antRack();
  }
}
