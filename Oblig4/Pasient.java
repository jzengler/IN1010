class Pasient {

  //Klassevariabler
  //brukes til å sette pasientId
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

  @Override
  public String toString(){
    return ("PASIENT\n" +
            "ID: " + hentId() + ", Navn: " + hentNavn() +
            "\nFnr: " + hentFnummer() +
            "\nAntall resepter: " + hentResepter().stoerrelse() );
  }

  public String hentObjektMedResept(){

    String retur = this.toString() + "\n";

    int i = 1;
    for ( Resept r : resepter ) {
       retur += ("\nResept " + i + "\n" + r + "\n");
       i++;
    }

    return retur;
  }

  //Legg til resept i pasientens Stabel
  public void leggTilResept(Resept r){

    // sjekk om reseptens pasientID er den samme som pasienten den legges til
    // kan vurdere å kaste unntak her?
    if( r.hentPasientId() != hentId()){
      System.out.println("Kan ikke skrive ut resept " + r.hentId() + " på en annen pasient. Forventet pasientID " + hentId() + ", fant " + r.hentPasientId());
    }
    //hvis id er lik, legg til i stabel
    else{
      resepter.leggTil(r);
    }
  }

  //loop gjennom alle respeter i pasientens Stabel
  public Stabel<Resept> hentResepter(){

    // returner reseptene
    return resepter;
  }

  //returner pasientId
  public int hentId(){
    return id;
  }

  public String hentNavn(){
    return navn;
  }

  public String hentFnummer(){
    return fnummer;
  }

}
