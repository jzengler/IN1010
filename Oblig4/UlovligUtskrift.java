public class UlovligUtskrift extends Exception{
  UlovligUtskrift(Lege l, Legemiddel lm){
    super( "Legen " + l.hentNavn() + " har ikke lov til \u00E5 skrive ut " + lm.hentNavn() );
  }
}
