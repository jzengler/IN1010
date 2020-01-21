import java.util.ArrayList;

public class Rack{


  // Instansvariabler

  private ArrayList<Node> noder = new ArrayList<Node>();
  private int maksNoder;


  // Konstruktør

  Rack(int maksNoder){

    this.maksNoder = maksNoder;

    }


  // Metoder

  // returnerer true/false om noden ble lagt til iht plass i racket 
  public boolean leggTilNode(Node node){

    boolean nodeLagtTil;

    // legg til noden hvis plass i racket
    if (noder.size() < maksNoder){
      noder.add(node);

      // returner true etter noden er lagt til
      nodeLagtTil = true;
    }
    else{
      // hvis racket er fullt returner false
      nodeLagtTil = false;
    }

    return nodeLagtTil;

  }

  // returnerer det totale antallet prosessorer i racket
  public int prosessorerIRack(){

    int antallProsessorer = 0;

    for ( Node n : noder){

      antallProsessorer += n.prosessorer();

    }

    return antallProsessorer;

  }

  // returnerer antallet noder i racket som oppfyller påkrevd minne
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
