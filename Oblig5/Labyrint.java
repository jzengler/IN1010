class Labyrint{

  //Instansvariabler
  // Y,X = kol, rad
  private int kol;
  private int rad;
  private String[][] ruter;




  //Konstruktør
  Labyrint(int kol, int rad){
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

  public Labyrint lesFraFil(){

    // les fra fil
    // kolonne rad
    // labyrinten

    //legg inn hvit/sort i array

    return this;
  }


}
