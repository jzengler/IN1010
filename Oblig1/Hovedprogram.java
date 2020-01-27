public class Hovedprogram{

  public static void main(String [] args){


    String navn, filnavn;

    // quick and dirty for aa kunne koe¸re fra CLI med argumenter
    // tar argumentene navn og filnavn
    if ( args.length < 2 ){

      System.out.println("Forventet to argumenter: navn filnavn");
      System.out.println("Bruker 'Abel' og 'dataklynge.txt'\n");
      filnavn = "dataklynge.txt";
      navn = "Abel";

    }
    else{

      filnavn = args[0];
      navn = args[1];
    }

    // opprett dataklyngen
    Dataklynge abel = new Dataklynge(navn, filnavn);


    // Kall paa utskriftsmetoder
    System.out.println();
    abel.noderMedNokMinne(32);
    abel.noderMedNokMinne(64);
    abel.noderMedNokMinne(128);
    System.out.println();
    abel.antProsessorer();
    abel.antRack();
  }
}
