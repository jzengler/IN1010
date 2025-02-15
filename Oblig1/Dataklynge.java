import java.util.ArrayList;
//Importer ArrayList for enklere haandtering av array endringer
 // Importer noedvendige klasser for filhåndtering
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;



public class Dataklynge{

  // Instansvariabler
  // arraylist til rack for enklere behandling av liste-elementer
  private ArrayList<Rack> rack = new ArrayList<Rack>();
  // navnet paa klyngen
  private String navn;
  private int maksNoderPerRack;



  // Konstruktoer
  Dataklynge(String navn, String filnavn){

    this.navn = navn;
    // midlertidig variabler for innhold fra fil
    int noder, minne, prosessorer;
    Scanner fil = null;

    // Les inn fil med klyngeoppsett
    try{

        fil = new Scanner( new File(filnavn) );
    }
    catch(FileNotFoundException e){
          System.out.println(e.getMessage());
          System.exit(1);
    }

    // les antall noder per rack fra fil, foerste linje i filen
    //konverter streng til heltall
    maksNoderPerRack = fil.nextInt();

    // opprett ett tomt rack, reduserer behov for feilhaandtering i leggTilNode()
    rack.add( new Rack(maksNoderPerRack) );

    // skriv ut klyngoppsett til terminal naar det leses fra fil
    System.out.println("#### Oppretter dataklynge fra fil ####");
    System.out.println("\nMaks noder per rack: " + maksNoderPerRack + "\n");

    // loekke saa lenge fil har en neste linje
    while( fil.hasNextLine() ){

      // leser ut antall noder, minne og prosessorer som skal opprettes
      // hver linje i tekstfil representerer like noder
      noder = fil.nextInt();
      minne = fil.nextInt();
      prosessorer = fil.nextInt();

      // opprett settet med noder
      for(int i = 0; i < noder; i++){
        leggTilNode( new Node(minne, prosessorer) );
      }

      // skriv ut hva som ble lagt til
      System.out.println("noder: " + noder + ", minne: " + minne + ", prosessorer: " + prosessorer);
    }

    // lukk fil
    fil.close();

    // avsluttende utskrift naar hele filen er lest og klyngen er opprettet
    System.out.println("\n#### Opprettet dataklynge " + this.navn + " ####\n");
  }



  // Metoder
  // legger til node i rack, oppretter nytt rack hvis fullt
  public void leggTilNode(Node node){

      // finn siste rack i listen
      Rack sisteRack = rack.get(rack.size() - 1);

      // sjekk om det er plass i racket, opprett rack hvis ikke
      if( !sisteRack.ledigPlass() ){

        sisteRack = new Rack(maksNoderPerRack);
        rack.add(sisteRack);
      }

      // legg til node
      sisteRack.leggTilNode(node);
  }

  // skriver ut det totale antallet prosessorer i dataklyngen
  public void antProsessorer(){

    // variabel til aa holde det totale antallet prosessorer
    int totAntallProsessorer = 0;

    // loop gjennom alle rack i dataklyngen
    for(Rack r : rack){

      totAntallProsessorer += r.prosessorerIRack();

    }

    System.out.println("Antall prosessorer: " + totAntallProsessorer);
  }

  // skriver ut antallet noder i dataklyngen med tilstrekkelig minne
  public void noderMedNokMinne(int paakrevdMinne){

    int antallNoder = 0;

    for ( Rack r : rack){

      antallNoder += r.noderMedNokMinne(paakrevdMinne);

    }

    System.out.println("Noder med minst " + paakrevdMinne + " GB: " + antallNoder);

  }

  // skriver ut antallet rack i dataklyngen
  public void antRack(){

    System.out.println("Antall rack: " + rack.size() );

  }

}
