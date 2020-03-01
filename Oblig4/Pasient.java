class Pasient{


  //Klassevariabler
  private static int antall = 0;



  //Instansvariabler
  private String navn = null;
  private String fnummer = null;
  private int id = 0;
  private Stabel<Resept> resepter = new Stabel<Resept>();



  //Konstruktør
  Pasient(String navn, String fnummer){
    this.navn = navn;
    this.fnummer = fnummer;
    id = antall++;

  }


  //Metoder
  public void leggTilResept(Resept r){
    resepter.leggTil(r);
  }

  public Array hentResepter(){
    int n = resepter.stoerrelse();
    //Array liste = new Array(n);


    //for(int i = 0; i < n; i++){
    //liste[i] = resepter.hent(i);
    }

    return liste;
  }

  public int hentId(){
    return id;
  }

}
