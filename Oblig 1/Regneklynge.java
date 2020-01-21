// Importer ArrayList for enklere håndtering av array endringer
import java.util.ArrayList;
 // Importer nødvendige klasser for filhåndtering
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;



public class Regneklynge{

  // Instansvariabler

  // arraylist til rack for enklere behandling av liste-elementer
  private ArrayList<Rack> rack = new ArrayList<Rack>();
  // navnet på klyngen
  private String navn;
  private int maksNoderPerRack;



  // konstruktør

  Regneklynge(String navn, String filnavn){


    int noder, minne, prosessorer;
    this.navn = navn;



    File klyngeOppsett = new File(filnavn);


    try{
    Scanner skanner = new Scanner(klyngeOppsett);

    // les antall noder per rack fra fil, konverter til int
    maksNoderPerRack = Integer.parseInt( skanner.next() );

    // opprett ett tomt rack, reduserer behov for feilhåndtering i leggTilNode
    rack.add( new Rack(maksNoderPerRack) );

    // skriv ut klyngoppsett når det leses
    System.out.println("#### Oppretter regneklynge fra fil ####");
    System.out.println("\nMaks noder per rack: " + maksNoderPerRack + "\n");

    // løkke så lenge skanner har en neste streng
    while( skanner.hasNext() ){

      // leser ut antall noder, minne og prosessorer som skal opprettes
      // hver linje representerer like noder
      noder = Integer.parseInt( skanner.next() );
      minne = Integer.parseInt( skanner.next() );
      prosessorer = Integer.parseInt( skanner.next() );

      // opprett settet med noder
      for(int i = 0; i < noder; i++){
        leggTilNode( new Node(minne, prosessorer) );
      }

      // skriv ut hva som ble lagt til
      System.out.println("noder: " + noder + ", minne: " + minne + ", prosessorer: " + prosessorer);
    }

    }
    catch(FileNotFoundException e){
      System.out.println(e.getMessage());
    }

    System.out.println("\n#### Opprettet regneklynge " + navn + " ####\n");


  }

  // legger til node i rack, oppretter nytt rack hvis fullt
  public void leggTilNode(Node node){

      // finn siste rack og forsøk å legg til node
      Rack sisteRack = rack.get(rack.size() - 1);
      // returnerer om noden ble lagt til
      boolean nodeLagtTil = sisteRack.leggTilNode(node);

      // hvis noden ikke blir lagt (nodeLagtTil == false), lag nytt rack
      if(nodeLagtTil == false){

        // opprett nytt rack og legg til i listen over rack
        // benytt maks antall noder fra regneklyngen
        Rack nyttRack = new Rack(maksNoderPerRack);
        rack.add( nyttRack );
        nyttRack.leggTilNode(node);

      }


  }

  // skriver ut det totale antallet prosessorer i regneklyngen
  public void antProsessorer(){

    // variabel til å holde det totale antallet prosessorer
    int totAntallProsessorer = 0;

    // loop gjennom alle rack i regneklyngen
    for(Rack r : rack){

      totAntallProsessorer += r.prosessorerIRack();

    }

    System.out.println("Antall prosessorer: " + totAntallProsessorer);
  }

  // skriver ut antallet noder i regneklyngen med tilstrekkelig minne
  public void noderMedNokMinne(int paakrevdMinne){

    int antallNoder = 0;

    for ( Rack r : rack){

      antallNoder += r.noderMedNokMinne(paakrevdMinne);

    }

    System.out.println("Noder med minst " + paakrevdMinne + " GB: " + antallNoder);

  }

  // skriver ut antallet rack i regneklyngen
  public void antRack(){

    System.out.println("Antall rack: " + rack.size() );

  }

}
