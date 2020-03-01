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

  public void hentResepter(){
    int n = resepter.stoerrelse();

    for(int i = 0; i < n; i++){
    resepter.hent();
    }
  }

}
