class Lege implements Comparable<Lege>{


  //Instansvariabler
  private String navn;
  private Lenkeliste<Resept> utskrevedeResepter = new Lenkeliste<Resept>();


  //Konstruktoer
  Lege(String navn){
    this.navn = navn;
  }


  //Metoder
  public String toString(){
    return navn;
  }

  public String hentNavn(){
    return navn;
  }

  public int compareTo(Lege l){

    String lNavn =  l.hentNavn();

    if(lNavn < navn ){
      return -1;
    }
    else if( lNavn == navn){
      return 0;
    }
    else{
      return 1;
    }

  }

  //burde sikkert gjøre noe lurt med iterator her
  public void hentResepter(){
  }


  public void skrivHvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
      Hvit res = new Hvit(legemiddel, utskrivendeLege, pasient, reit);
      utskrevedeResepter.leggTil(res);
  }
  public void skrivBlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
      Blaa res = new Blaa(legemiddel, utskrivendeLege, pasient, reit);
      utskrevedeResepter.leggTil(res);
  }
  public void skrivMilResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
      MilResept res = new MilResept(legemiddel, utskrivendeLege, pasient, reit);
      utskrevedeResepter.leggTil(res);
  }
  public void skrivPResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient){
      PResept res = new PResept(legemiddel, utskrivendeLege, pasient);
      utskrevedeResepter.leggTil(res);
  }

}
