class Labyrint{

  //Instansvariabler
  // kolonner og rader
  private int kol;
  private int rad;
  private int[][] ruter;




  //Konstruktør
  private Labyrint(int kol, int rad, int[] ruter){
    this.kol = kol;
    this.rad = rad;
    ruter = new int[kol][rad];
  }

  //Metoder

  //returner labyrinten som streng
  public String toString(){
    String rutenett = "";

    //legg til alle ruter i en streng. ny linje mellom hver rad.
    for(int y = 0; y < kol; y++){
      for(int x = 0; x < rad; x++){
        rutenett += ruter[y][x] + " ";
      }
      // newline før neste rad
      rutenett += "\n";
    }

    // returner hele strengen
    return rutenett;

  }

  static public Labyrint lesFraFil(File fil)throws Exception{

    Scanner scanner = new Scanner(fil);


    //les antall rader og kolonner
    rad = scanner.nextInt();
    kol = scanner.nextInt();


    //les labyrint-symbolene
    while( scanner.hasNextLine() ){

      String linje = scanner.nextLine();

      // for(char l : linje.toCharArray() ){
      //
      //   if( l.compareTo("#") == 0 ){
      //     new SortRute()
      //   }

      }
    }

    //legg inn hvit/sort i array

    return this;
  }


}
