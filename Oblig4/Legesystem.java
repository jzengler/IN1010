import java.util.*;
import java.io.*;

public class Legesystem{

  // Opprett lister som lagrer objektene i legesystemet
  static Lenkeliste<Pasient> pasienter = new Lenkeliste<Pasient>();
  static Lenkeliste<Legemiddel> legemidler = new Lenkeliste<Legemiddel>();
  static SortertLenkeliste<Lege> leger = new SortertLenkeliste<Lege>();






  // MAIN
  public static void main( String[] args )throws Exception{

    clearScreen();
    // hent mengder av ukontrollert lenkelisten
    File inndata = new File("myeInndata.txt");
    lesFil(inndata);


    //venter til bruker trykker enter
    System.out.println("\nTrykk [enter] for \u00E5 fortsette");
    try{System.in.read();}catch(Exception e){}
    clearScreen();

    meny();

  }





  //metoder
  // leser fil linje for linje og forsøker å legge til elementer i legesystemet
  private static void lesFil(File fil)throws Exception{

    // les fil og begynn data import
    Scanner scanner = null;

    //kast feil hvis en fil ikke ble funnet
    try{
      scanner = new Scanner(fil, "UTF-8");
      System.out.println("\nStarter innlesing fra fil...\n");
    }
    catch(FileNotFoundException e){
      System.out.println("Fant ikke fil, starter legesystem uten data");
      return;
    }

    String linje = scanner.nextLine();
    // // loop gjennom alle linjer i filen
    // // innlesing av data skjer i denne løkken
    // // ytre løkke for å skille mellom pasienter, leger, legemidler og resepter
    while(scanner.hasNextLine()){

      //sjekk header for datatype
      String dataType = kontrollerDataType(linje);

      //skriver til terminal hvilken data som legges til
      System.out.println("Legger til " + dataType + " i legesystemet");


      //sjekker hvilken type vi behandler
      // Header: "# Pasienter (navn, fnr)"
      if( dataType.compareTo("pasienter") == 0) {

        // looper til vi finner ny header indikert med #
        while(scanner.hasNextLine()){
          linje = scanner.nextLine();

          //avslutter hvis vi linjen inneholder en ny header
          if(linje.charAt(0) == '#'){
            break;
          }

        validerPasientData(linje);
        }
      }
      else if( dataType.compareTo("legemidler") == 0 ){

        // looper til vi finner ny header indikert med #
        while(scanner.hasNextLine()){
          linje = scanner.nextLine();


          //avslutter hvis vi linjen inneholder en ny header
          if(linje.charAt(0) == '#'){
            break;
          }

        validerLegemiddelData(linje);
        }
      }
      else if( dataType.compareTo("leger") == 0 ){

        // looper til vi finner ny header indikert med #
        while(scanner.hasNextLine()){
          linje = scanner.nextLine();


          //avslutter hvis vi linjen inneholder en ny header
          if(linje.charAt(0) == '#'){
            break;
          }

        validerLegeData(linje);
        }
      }
      else if( dataType.compareTo("resepter") == 0 ){

        // looper til vi finner ny header indikert med #
        while(scanner.hasNextLine()){
          linje = scanner.nextLine();


          //avslutter hvis vi linjen inneholder en ny header
          if(linje.charAt(0) == '#'){
            break;
          }

        validerReseptData(linje);
        }
      }

      }
      System.out.println("\nFerdig \u00E5 lese fra fil...\n");
      return;
  }




  //brukes av lesFil()
  private static String kontrollerDataType(String linje){

    String[] data = linje.split(" ");


      String typeData = data[1];
      switch (typeData){

        case "Pasienter":   typeData = "pasienter";
                            break;
        case "Leger":       typeData = "leger";
                            break;
        case "Legemidler":  typeData = "legemidler";
                            break;
        case "Resepter":    typeData = "resepter";
                            break;
        default:            typeData = "ugyldig";


          try{
            throw new UgyldigData(data[1] + " er ikke en gyldig datatype");
          }
          catch (Exception e){
            System.out.println(e);
          }

      }
      return typeData;

  }

  //brukes av lesFil()
  private static boolean validerPasientData(String linje){

    // Header: "# Pasienter (navn, fnr)"

    //split CSV
    String[] data = linje.split(",");

    String navn = null;
    String fnr;

    //valider data
    try{

      //sjekk at pasientdata kun inneholder to verdier
      if(data.length != 2){throw new UgyldigData("Pasientdata inneholder feil: " + linje);}

      //les felter som strenger
      navn = String.valueOf(data[0]);
      navn = navn.substring(0, 1).toUpperCase() + navn.substring(1);
      fnr = String.valueOf(data[1]);

      //sjekk at navn ikke er blankt, minst to tegn
      if(navn.length() < 2){throw new UgyldigData("Ugyldig pasientnavn: " + navn);}

      //sjekk at fnr består av 11 tegn
      //sjekker ikke at det kun består av siffer
      if(fnr.length() != 11){throw new UgyldigData("Ugyldig fødselsnummer: " + fnr);}

    }
    //kast feilmelding for ugyldig data
    catch(Exception e){

      System.out.println(e);
      return false;

    }




    //kontroller om pasient allerede eksisterer
    try{
      //sjekk at fnr ikke allerede er lagt inn
      for(Pasient p : pasienter){
        if( p.hentFnummer().equals(fnr) ) {throw new UgyldigData("Pasient, " + fnr + " allerede opprettet");}
      }

      //legg til pasient
      pasienter.leggTil( new Pasient(navn, fnr) );

    }

    catch(Exception e) {

      System.out.println(e);
      return false;

    }

    return true;
  }

  //brukes av lesFil()
  private static boolean validerLegeData(String linje){

    // # Leger (navn,kontrollid / 0 hvis vanlig lege)
    String navn;
    int kontrollId;

    //split CSV
    String[] data = linje.split(",");

    //sjekker at antallet elementer stemmer med legemiddel
    try{
      if(data.length != 2){throw new UgyldigData("Ugyldig legedata: " + linje);}
      navn = String.valueOf( data[0] );
      navn = navn.substring(0, 1).toUpperCase() + navn.substring(1);

      try{
        kontrollId = Integer.parseInt( data[1] );
      }catch(NumberFormatException e){
        throw new UgyldigData("Forventet tall som kontrollId, fikk " + data[1]);
      }

      if(navn.length() < 2){throw new UgyldigData("Ugyldig legenavn: " + navn);}

    }
    catch(Exception e){
      System.out.println(e);
      return false;
    }


    try{

      //sjekk om lege allerede finnes i listen
      for (Lege l : leger){
        if(l.hentNavn().compareTo(navn) == 0){throw new UgyldigData("Lege, " + navn + ", allerede opprettet");}
        if(l.hentKontrollID() == kontrollId && kontrollId != 0){throw new UgyldigData("KontrollID, " + kontrollId +", allerede i bruk");}
      }

      //legger til riktig kategori lege
      if(kontrollId != 0){
        leger.leggTil(new Spesialist( navn, kontrollId ) );
      }
      else{
        leger.leggTil(new Lege( navn ) );
      }

    }
    catch(Exception e){
      System.out.println(e);
      return false;
    }

    return true;
  }

  //brukes av lesFil()
  private static boolean validerLegemiddelData(String linje){
    // # Legemidler (navn,type,pris,virkestoff,[styrke])


    String navn;
    String type;
    Double pris;
    Double virkestoff;
    int styrke = 0;

    String[] data = linje.split(",");

    int count = data.length;

    try{
      //|| count != 4
      //

      //narkotisk og vanedannende skal ha 5 felter
      //vanlig skal har 4
      if( count > 5 || count < 4 ){throw new UgyldigData("Legemiddeldata inneholder feil: " + linje);}

      //legg i navngitte variabler for lesbarhet og færre oppslag i array senere
      navn = String.valueOf( data[0] );
      type = String.valueOf( data[1] ).toLowerCase();

      try{
        pris = Double.parseDouble( data[2] );
        virkestoff = Double.parseDouble ( data[3] );
      }
      catch(NumberFormatException e){
        throw new UgyldigData("Forventet tallverdi, fant " + e.getMessage().replaceAll("For input string: ", "") + ": " + linje);
      }

      //sjekk at vi finner legemiddeltypen på forventet plass
      if( type.compareTo("narkotisk") != 0 && type.compareTo("vanedannende") != 0 && type.compareTo("vanlig") != 0){
        throw new UgyldigData("Ugyldig legemiddeltype: " + type);
      }

      //gjelder narkotisk og vanedannende
      if(count == 5){
        styrke = Integer.parseInt(data[4]);
      }

    }
    catch(Exception e){
      System.out.println(e);
      return false;
    }




    //legg til riktig type legemiddel
    if(count == 5){

      //legger til riktig kategori legemiddel, parser strenger til riktig type
      if(type.compareTo("narkotisk") == 0){
        legemidler.leggTil( new Narkotisk( navn, pris, virkestoff, styrke ) );
      }
      else if(type.compareTo("vanedannende") == 0){
        legemidler.leggTil( new Vanedannende( navn, pris, virkestoff, styrke ) );
      }
      else{return false;}
    }
    else if( count == 4){
      if(type.compareTo("vanlig") == 0){
        legemidler.leggTil( new Vanlig( navn, pris, virkestoff ) );
      }
    }
    else{return false;}

    return true;
  }

  //brukes av lesFil()
  private static boolean validerReseptData(String linje){
    // # Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])

    int legemiddelId;
    String legeNavn;
    int pasientId;
    String reseptType;
    int reit = 0;


    //split CSV
    String[] data = linje.split(",");


    int count = data.length;

    //sjekk om data er gyldig
    try{

      //5 felter untatt for PResept
      if( count > 5 || count < 4){throw new UgyldigData("Reseptdata inneholder feil: " + linje);}

      //mellomlagrer for leserbarhet senere
      legemiddelId = Integer.parseInt( data[0] );
      legeNavn = String.valueOf( data[1] );
      pasientId = Integer.parseInt( data[2] );
      reseptType = String.valueOf(data[3]).toLowerCase();

      // for alle typer untatt p resept
      if(count == 5){
        reit = Integer.parseInt( data[4] );
      }

      if(reseptType.compareTo("blaa") != 0 && reseptType.compareTo("hvit") != 0 && reseptType.compareTo("militaer") != 0 && reseptType.compareTo("p") != 0 ){
        throw new UgyldigData("Ugyldig resepttype: " + reseptType);
      }

    }
    catch(Exception e){

      //skriv ut feilmelding returner
      System.out.println(e);
      return false;
    }

    //finn legemiddelobjekt fra legemiddelnummer
    Legemiddel legemiddel = null;
    for (Legemiddel l : legemidler ){
      if(l.hentID() == legemiddelId ){

        //lagre peker til legemiddelobjekt
        legemiddel = l;

        //ikke sjekk resten hvis legemiddel finnes
        break;
      }
    }

    //finn legeobjekt fra legenavn
    Lege lege = null;
    for (Lege l : leger){
      if( l.hentNavn().compareTo( legeNavn ) == 0 ){

        //lagre peker til legeobjekt
        lege = l;

        //ikke sjekk resten hvis legen finnes
        break;
      }
    }

    //finn pasientobjekt fra pasientId
    Pasient pasient = null;
    for (Pasient p : pasienter){
      if( p.hentId() == pasientId ){

        //lagre peker til pasientobjekt
        pasient = p;

        //ikke sjekk resten hvis pasienten finnes
        break;
      }
    }

    try{
      if(legemiddel == null){throw new UgyldigData("Legemiddel, " + legemiddelId + ", finnes ikke");}
      if(lege == null){throw new UgyldigData("Lege, " + legeNavn + ", finnes ikke");}
      if(pasient == null){throw new UgyldigData("Pasient, " + pasientId + ", finnes ikke");}
    }
    catch(Exception e){
      System.out.println(e);
      return false;
    }


    //legg til ny presept hvis antall elementer og type stemmer
    if(count == 4 && reseptType.compareTo("p") == 0){

      //opprett resept ved bruk av skrivResept iht oppgaven.
      //bør metoden returnere resept for lagring i egen liste med alle resepter?
      lege.skrivPResept(legemiddel, lege, pasient);

    }
    else{
      //opprett riktig type resept
      if(reseptType.compareTo("blaa") == 0){
        lege.skrivBlaaResept(legemiddel, lege, pasient, reit);
      }
      else if(reseptType.compareTo("hvit") == 0){
        lege.skrivHvitResept(legemiddel, lege, pasient, reit);
      }
      else if(reseptType.compareTo("militaer") == 0){
        lege.skrivMilResept(legemiddel, lege, pasient, reit);
      }
      else{return false;}
    }

    return true;
  }





  // Skriver ut en meny til bruker - E2
  private static void meny(){



    int valg = 1;
    while(valg != 0){
      System.out.println("LEGESYSTEM\n");
      System.out.println("1: Skriv ut alle pasienter, leger, legemidler og resepter");
      System.out.println("2: Legg til element");
      System.out.println("3: Bruk resept");
      System.out.println("4: Skriv ut statistikk");
      System.out.println("5: Skriv til fil");
      System.out.println("\n0: Avslutt program");
      Scanner s = new Scanner(System.in);

      try{
        valg = s.nextInt();
      }
      catch(Exception e){
        //stygg måte å tvinge default switch case
        valg = 999;
      }

      switch(valg){
        case 0: return;

        case 1: clearScreen();
                skrivUtAlt();
                break;
        case 2: clearScreen();
                leggTilElement();
                break;
        case 3: clearScreen();
                brukResepet();
                break;
        case 4: clearScreen();
                skrivUtStatistikk();
                break;
        case 5: clearScreen();
                skrivTilFil();
                break;
        default:
                clearScreen();
      }
    }
  }

  //brukes av meny()
  // Skriver ut alle elementer i systemet til skjerm - E3
  private static void skrivUtAlt(){


    //skriv ut alle pasienter i listen
    for( Pasient p : pasienter){
      System.out.println(p.hentObjektMedResept() + "\n");
    }

    //skriv ut alle legemidler i lenkelisten
    for( Legemiddel l : legemidler){
      System.out.println(l + "\n");
    }

    //skriv ut alle leger i lenkelisten
    for( Lege l : leger){
      System.out.println(l + "\n");
    }
  }

  //brukes av meny()
  // Legg til element - E4
  private static void leggTilElement(){

    // Undermeny for å legge til lege, pasient, resept eller legemiddel
    int valg = 1;
    while(valg != 0){
      System.out.println("\nVelg hva du \u00F8nsker \u00E5 legge til:\n");
      System.out.println("1: Lege");
      System.out.println("2: Pasient");
      System.out.println("3: Legemiddel");
      System.out.println("4: Resept");
      System.out.println("\n0: Returner til hovedmeny");

      Scanner s = new Scanner(System.in);
      valg = s.nextInt();

      switch(valg){
        case 0:
                return;
        case 1:

                try{
                  String lege;

                  System.out.println("Skriv inn navn p\u00E5 lege:");
                  s = new Scanner(System.in);
                  lege = s.nextLine();

                  System.out.println("Skriv inn kontrollID (0 hvis vanlig lege): ");
                  s = new Scanner(System.in);

                  lege = String.join(",",lege,s.nextLine());

                  //kontroll av gyldighet gjøres av metoden
                  if( validerLegeData(lege) ){
                    System.out.println("Lege ble opprettet!");
                  }
                }catch(Exception e){
                  break;
                }

                break;
        case 2:

                String pasient;

                System.out.println("Skriv inn navn p\u00E5 pasient:");
                s = new Scanner(System.in);

                try{ pasient = s.nextLine();
                }catch(Exception e){
                  break;
                }

                System.out.println("Skriv inn f\u00F8dselsnummer (11 siffer): ");
                s = new Scanner(System.in);

                try{ pasient = String.join(",",pasient,s.nextLine());
                }catch(Exception e){
                  break;
                }

              //kontroll av gyldighet gjøres av metoden

              if( validerPasientData(pasient) ){
                System.out.println("Pasient ble opprettet!");
              }

              break;
        case 3:
                // # Legemidler (navn,type,pris,virkestoff,[styrke])
                String legemiddel;
                String type = null;

                // String[] utskrift = ();
                System.out.println("Skriv inn navn p\u00E5 legemiddel: ");
                s = new Scanner(System.in);

                try{ legemiddel = s.nextLine();
                }catch(Exception e){
                  break;
                }

                System.out.println("Skriv inn type legemiddel (vanlig, narkotisk, vanedannende): ");
                s = new Scanner(System.in);

                try{
                  type = s.nextLine();
                  legemiddel = String.join(",",legemiddel, type);
                }catch(Exception e){
                  break;
                }

                System.out.println("Skriv inn pris p\u00E5 legemiddel i kr (123.99): ");
                s = new Scanner(System.in);

                try{ legemiddel = String.join(",",legemiddel,s.nextLine());
                }catch(Exception e){
                  break;
                }

                System.out.println("Skriv inn mengde virkestoff  i mg (123.99): ");
                s = new Scanner(System.in);

                try{ legemiddel = String.join(",",legemiddel,s.nextLine());
                }catch(Exception e){
                  break;
                }

                if (type.compareTo("vanlig") != 0){
                  System.out.println("Skriv inn narkotisk/vanedannende styrke (1 - 10): ");
                  s = new Scanner(System.in);

                  try{ legemiddel = String.join(",",legemiddel,s.nextLine());
                  }catch(Exception e){
                    break;
                  }
                }
                if( validerLegemiddelData(legemiddel)){
                  System.out.println("Legemiddel lagt til!");
                }

                break;
        case 4:
                // # Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])
                String resept;
                String reseptType = null;

                // ta imot legemiddelnummer
                System.out.println("Skriv inn legemiddelnummer: ");
                s = new Scanner(System.in);
                resept = s.nextLine();


                // ta imot legenavn
                System.out.println("Skriv inn navn p\u00E5 lege: ");
                s = new Scanner(System.in);
                resept = String.join(",",resept, s.nextLine());


                // ta imot pasientID
                System.out.println("Skriv inn pasientID: ");
                s = new Scanner(System.in);
                resept = String.join(",",resept,s.nextLine());


                // velg resept type
                System.out.println("Velg resept type: ");
                System.out.println("0: Bl\u00E5");
                System.out.println("1: Hvit");
                System.out.println("2: Milit\u00E6r");
                System.out.println("3: Prevensjon");
                s = new Scanner(System.in);
                valg = s.nextInt();

                if(valg == 0 ){reseptType = "blaa";}
                else if(valg == 1 ){reseptType = "hvit";}
                else if(valg == 2 ){reseptType = "militaer";}
                else if(valg == 3 ){reseptType = "p";}
                else{break;}

                resept = String.join(",",resept,reseptType);


                // reit hvis ikke p resept
                if (valg != 3){
                  System.out.println("Skriv inn antall reit: ");
                  s = new Scanner(System.in);

                  resept = String.join(",",resept,s.nextLine());
                }

                if( validerReseptData(resept)){
                  System.out.println("Resept lagt til!");
                }

                break;
        default:
      }
    }
  }

  //brukes av meny()
  // Bruk resepet - E5
  private static void brukResepet(){

    // Undermeny for å bruke resepter

    // Initialiserer metodevariabler
    int pas = 0;
    int res = 0;

    //skriv ut alle pasienter i listen
    for( Pasient p : pasienter ){
      System.out.println(p.hentId() + ": " + p.hentNavn() +" (fnr " + p.hentFnummer() + ")");
    }

    System.out.println("\nHvilken pasient vil du se resepter for?\n");
    Scanner s = new Scanner(System.in);
    try{ pas = s.nextInt(); }catch(Exception e){System.out.println(e.getMessage());}

    // Løper igjennom alle pasienter
    for( Pasient p : pasienter ){

      // Stopper ved valgt pasient
      if ( p.hentId() == pas ) {
        int nResepter = p.hentResepter().stoerrelse();
        int teller = 0;
        int[] lmKnytning = new int [nResepter];


        System.out.println("\nValgt pasient: " + p.hentNavn() +" (fnr " + p.hentFnummer() + ")");
        System.out.println("\nHvilken resept vil du bruke?\n");

        // Løper igjennom alle resepter til pasientene i den hensikt å skrive ut disse så bruker kan velge ønsket resept
        for ( Resept r : p.hentResepter() ) {
          System.out.println(teller + ": " + r.hentLegemiddel().hentNavn() + " ("+ r.hentReit() + " reit)\n" );
          lmKnytning[teller] = r.hentId();
          teller++;
        }

        // Går tilbake til hovedmeny hvis pasienten ikke har noen registrerte resepter
        if ( nResepter == 0 ) {
          System.out.println(p.hentNavn() + "har ikke mottatt noen resepter i dette legemiddelsystemet\n");
          System.out.println("G\u00E5r tilbake til hovedmenyen\n");
          return;
        }

        // Leser inn ønsket resept
        try{ res = s.nextInt();
            // Lagrer den aktuelle legemiddel ID-en som ønsket resept
            res = lmKnytning[res];
        }
        catch(Exception e){
          return;
        }




        // Løper igjennom alle resepter til pasientene.
        for ( Resept r : p.hentResepter() ) {

          // Stopper på valgt resept i den hensikt å bruke resepten
          if ( r.hentId() == res ) {

            if ( r.bruk() ) {
              // Skriver ut positiv status fra bruken av resepten.
              System.out.println("Brukte resept p\u00E5 " + r.hentLegemiddel().hentNavn() + ". Antall gjenv\u00E6rende reit: " + " ("+ r.hentReit() + " reit)\n" );


            }

            else {
              // Skriver ut negativ status fra bruken av resepten.
              System.out.println("Kunne ikke bruke resepten. 0 reit gjenst\u00E5r");

            }
            //venter til bruker trykker enter
            System.out.println("Trykk [enter] for \u00E5 fortsette");
            try{
              System.in.read();
            }
            catch(Exception e){
              return;
            }

            clearScreen();
          }
        }
      }
    }
  }

  //brukes av meny()
  // Skriv ut statistikk - E6
  private static void skrivUtStatistikk(){

    // Undermeny for statistikktype
    int valg = 1;
    while(valg != 0){
      System.out.println("\nVelg \u00F8nsket statistikk\n");
      System.out.println("1: Totalt antall utskrevne resepter p\u00E5 vanedannende legemidler");
      System.out.println("2: Totalt antall utskrevne resepter p\u00E5 narkotiske legemidler");
      System.out.println("3: Leger med utskrift av narkotiske legemidler");
      System.out.println("4: Pasienter som har mottatt narkotiske legemidler");
      System.out.println("\n0: Returner til hovedmeny");
      Scanner s = new Scanner(System.in);
      try{ valg = s.nextInt();}catch(Exception e){clearScreen();return;}

      switch(valg){
        case 1:
                int nResepterVanedannendeLegemidler = 0;
                // Looper igjennom alle leger
                for ( Lege l : leger) {

                // Looper igjennom alle reseptene til hver lege
                  for ( Resept r : l.hentResepter() ) {

                    // Stopper hvis resepten omhandler et vanedannende legemiddel
                    if ( r.hentLegemiddel() instanceof Vanedannende ) {
                      System.out.println("\n" + r);

                      // Teller opp antall resepter som gjelder vanedannende legemidler (per lege)
                      nResepterVanedannendeLegemidler++;
                    }

                  }
                }
                System.out.println( "\nAntall resepter utskrevet p\u00E5 vanedannende legemidler: " + nResepterVanedannendeLegemidler );
                break;
        case 2:
                int nResepterNarkotiskeLegemidler = 0;
                // Looper igjennom alle leger
                for ( Lege l : leger) {

                // Looper igjennom alle reseptene til hver lege
                  for ( Resept r : l.hentResepter() ) {

                    // Stopper hvis resepten omhandler et narkotisk legemiddel
                    if ( r.hentLegemiddel() instanceof Narkotisk ) {
                      System.out.println("\n" + r);

                      // Teller opp antall resepter som gjelder narkotiske legemidler (per lege)
                      nResepterNarkotiskeLegemidler++;
                    }

                  }
                }
                System.out.println( "\nAntall resepter utskrevet p\u00E5 narkotiske legemidler: " + nResepterNarkotiskeLegemidler );
                break;
        case 3:
                // Looper igjennom alle leger
                for ( Lege l : leger) {

                  int nNarkotiskeResepter = 0;

                // Looper igjennom alle reseptene til hver lege
                  for ( Resept r : l.hentResepter() ) {

                    // Stopper hvis resepten omhandler et narkotisk legemiddel
                    if ( r.hentLegemiddel() instanceof Narkotisk ) {
                      // Teller opp antall narkotiske resepter
                      nNarkotiskeResepter++;
                    }
                  }
                  if ( nNarkotiskeResepter != 0 ) {
                    System.out.println( l.hentNavn() + " har skrevet ut narkotiske resepter. Antall resepter: " + nNarkotiskeResepter );
                  }
                }
                break;
        case 4:

                // Looper igjennom alle pasienter
                for ( Pasient p : pasienter) {

                // Initialiserer metodevariabler
                  int nGyldigeNarkotiskeResepter = 0;
                  int sumReit = 0;

                // Looper igjennom alle resepter, per pasient
                  for ( Resept r : p.hentResepter() ) {

                    // Stopper hvis legemiddelet er narkotisk
                    if ( r.hentLegemiddel() instanceof Narkotisk ) {

                      // Sjekker om resepten er gyldig
                      if ( r.hentReit() != 0 ) {

                        //Teller opp gyldig resept
                        nGyldigeNarkotiskeResepter++;

                        sumReit += r.hentReit();
                      }
                    }
                  }

                  // Hvis pasienten hadde noen resepter på narkotiske legemidler blir dette skrevet ut
                  if ( nGyldigeNarkotiskeResepter != 0 ) {
                    System.out.println( p.hentNavn() + " har mottatt narkotiske resepter. Antall gyldige resepter: " + nGyldigeNarkotiskeResepter + ". Sum reit: " + sumReit );
                  }

                }
                break;
        default:
                clearScreen();
                return;
      }
    }
  }




  //brukes av meny()
  // Skriver ut alle elementer i systemet til fil - E8
  private static void skrivTilFil(){

    try{

      String filnavn = "legesystem.txt";
      // opprett filskriver
      //UTF-8 for å håndtere æ,ø,å
      PrintWriter fil = new PrintWriter(filnavn, "UTF-8");

      //skriv pasientdata
      fil.println("# Pasienter (navn,fnr)");
      for(Pasient p : pasienter){
        fil.println( String.join(",", p.hentNavn(), p.hentFnummer() ) );
      }

      //skriv legemiddeldata
      fil.println("# Legemidler (navn,type,pris,virkestoff,[styrke])");
      for(Legemiddel lm : legemidler){

        if( lm instanceof Narkotisk){
          Narkotisk n = (Narkotisk) lm;
          fil.println( String.join(",", n.hentNavn(), "narkotisk", String.valueOf( n.hentPris() ), String.valueOf( n.hentVirkestoff() ), String.valueOf( n.hentNarkotiskStyrke() ) ) );
        }
        else if(lm instanceof Vanedannende){
          Vanedannende v = (Vanedannende) lm;
          fil.println( String.join(",", v.hentNavn(), "vanedannende", String.valueOf( v.hentPris() ), String.valueOf( v.hentVirkestoff() ), String.valueOf( v.hentVanedannendeStyrke() ) ) );
        }
        else{
          fil.println( String.join(",", lm.hentNavn(), "vanlig", String.valueOf( lm.hentPris() ), String.valueOf( lm.hentVirkestoff() ) ) );
        }
      }

      //skriv legedata
      fil.println("# Leger (navn,kontrollid / 0 hvis vanlig lege)");
      for( Lege l : leger){
        fil.println( String.join(",", l.hentNavn(), String.valueOf( l.hentKontrollID() ) ) );
      }

      //skriv reseptdata
      fil.println("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
      for( Lege l : leger ){
        Lenkeliste<Resept> resepter = l.hentResepter();

        for (Resept r : resepter){
          if(r instanceof PResept){
            fil.println( String.join(",", String.valueOf( r.hentId() ), r.hentLege().hentNavn(), String.valueOf( r.hentPasientId() ), "p") );
          }
          else if(r instanceof MilResept){
            fil.println( String.join(",", String.valueOf( r.hentId() ), r.hentLege().hentNavn(), String.valueOf( r.hentPasientId() ), "militaer", String.valueOf( r.hentReit() ) ) );
          }
          else if(r instanceof Blaa){
            fil.println( String.join(",", String.valueOf( r.hentId() ), r.hentLege().hentNavn(), String.valueOf( r.hentPasientId() ), "blaa", String.valueOf( r.hentReit() ) ) );
          }
          else{
            fil.println( String.join(",", String.valueOf( r.hentId() ), r.hentLege().hentNavn(), String.valueOf( r.hentPasientId() ), "hvit", String.valueOf( r.hentReit() ) ) );
          }
        }
      }


      fil.close();
      System.out.println("Data ble skrevet til '" + filnavn + "'");

      //venter til bruker trykker enter
      System.out.println("Trykk [enter] for \u00E5 fortsette");
      try{System.in.read();}catch(Exception e){}


    }
    catch(Exception e){
      System.out.println(e);
    }

    clearScreen();
  }





  private static void clearScreen(){



    try{
      if( System.getProperty("os.name").contains("Windows") ){
        new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
      }
      else{
        Runtime.getRuntime().exec("clear");
      }
    }
    catch(Exception e){
      System.out.println(e);
    }
  }

//end of class
}
