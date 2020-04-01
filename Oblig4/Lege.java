class Lege implements Godkjenningsfritak, Comparable<Lege>{


  //Instansvariabler
  private String navn;
  private Lenkeliste<Resept> utskrevedeResepter = new Lenkeliste<Resept>();
  protected int kontrollID = 0;

  //Konstruktoer
  Lege(String navn){
    this.navn = navn;
  }


  //Metoder
  @Override
  public String toString(){
    return ("LEGE\n" +
            "Navn: " + hentNavn() );
  }

  //Metode for å returnere objekt med resepter
  public String hentObjektMedResept(){

    String retur = this.toString();
    retur += "\nResepter: " + "\n";

    for ( Resept r : utskrevedeResepter ) {
       retur += ("\n" + r + "\n");
    }

    return retur;
  }



  public String hentNavn(){
    return navn;
  }

  public int compareTo(Lege l){

    String lNavn = l.hentNavn();

    return lNavn.compareTo(navn);

  }

  //Metode for å returnere alle de utskrevede reseptene til en lege
  public Lenkeliste<Resept> hentResepter() {
    return utskrevedeResepter;
  }


  public void skrivHvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){

      try{
      sjekkLisens(legemiddel, utskrivendeLege);
      }
      catch(UlovligUtskrift e){
        System.out.println(e);
        return;
      }

      //opprett ny resept
      Hvit res = new Hvit(legemiddel, utskrivendeLege, pasient, reit);
      //legg resepten i pasientens stabel
      pasient.leggTilResept(res);
      //legg resepten i legens utskrevne
      utskrevedeResepter.leggTil(res);
  }

  public void skrivBlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){

        try{
        sjekkLisens(legemiddel, utskrivendeLege);
        }
        catch(UlovligUtskrift e){
          System.out.println(e);
          return;
        }

      //opprett ny resept
      Blaa res = new Blaa(legemiddel, utskrivendeLege, pasient, reit);
      //legg resepten i pasientens stabel
      pasient.leggTilResept(res);
      //legg resepten i legens utskrevne
      utskrevedeResepter.leggTil(res);
  }

  public void skrivMilResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){

        try{
        sjekkLisens(legemiddel, utskrivendeLege);
        }
        catch(UlovligUtskrift e){
          System.out.println(e);
          return;
        }

      //opprett ny resept
      MilResept res = new MilResept(legemiddel, utskrivendeLege, pasient, reit);
      //legg resepten i pasientens stabel
      pasient.leggTilResept(res);
      //legg resepten i legens utskrevne
      utskrevedeResepter.leggTil(res);
  }

  public void skrivPResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient){

        try{
        sjekkLisens(legemiddel, utskrivendeLege);
        }
        catch(UlovligUtskrift e){
          System.out.println(e);
          return;
        }

      //opprett ny resept
      PResept res = new PResept(legemiddel, utskrivendeLege, pasient);
      //legg resepten i pasientens stabel
      pasient.leggTilResept(res);
      //legg resepten i legens utskrevne
      utskrevedeResepter.leggTil(res);
  }

  //Metode for å sjekke om en lege har gyldiglisens til å skrive ut narkotisk legemiddel
  public void sjekkLisens(Legemiddel lm, Lege l) throws UlovligUtskrift {

    if ( ( lm instanceof Narkotisk ) && ( l.hentKontrollID() == 0 ) ) {
        throw new UlovligUtskrift(l, lm);
    }

  }

  public int hentKontrollID(){
    return kontrollID;
  }

}
