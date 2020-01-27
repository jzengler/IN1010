import java.util.ArrayList;



public class Rack{

  // Instansvariabler
  private ArrayList<Node> noder = new ArrayList<Node>();
  private int maksNoder;



  // Konstruktoer
  Rack(int maksNoder){

    this.maksNoder = maksNoder;

    }



  // Metoder
  // legger til node i rack
  public void leggTilNode(Node node){

      noder.add(node);
  }

  // returnerer true/false om det er plass i racket
  public boolean ledigPlass(){

    if( noder.size() < maksNoder){
      return true;
    }
    else{
      return false;
    }
  }

  // returnerer det totale antallet prosessorer i racket
  public int prosessorerIRack(){

    int antallProsessorer = 0;

    for ( Node n : noder){

      antallProsessorer += n.prosessorer();

    }

    return antallProsessorer;

  }

  // returnerer antallet noder i racket som oppfyller paakrevd minne
  public int noderMedNokMinne(int paakrevdMinne){

    int antallNoder = 0;

    for (Node n : noder){

      if(n.minne() >= paakrevdMinne){
        antallNoder ++;
      }

    }

    return antallNoder;

  }

}
