import java.util.ArrayList;
abstract class Rute{

  //Instansvariabler
  private int x;
  private int y;

  private Labyrint l;
  private ArrayList<Rute> naboer = new ArrayList<Rute>();

  private boolean besokt = false;
  protected char tegn;

  //konstruktør
  Rute(int x, int y){
    this.x = x;
    this.y = y;
  }

  //metoder
  abstract char tilTegn();

  abstract void settTegn();



  // legger til en rute som nabo
  protected void leggTilNabo(Rute nabo){
      naboer.add(nabo);
  }



  // grisete måte å legge til labyrint-referansen etter at ruten er opprettet
  protected void tilordneLabyrint(Labyrint l){
      this.l = l;
  }



  // kaller gaa() på naborutene rekursivt
  protected void gaa(Rute forrige, String vei){

      // endrer besokt til true og rutens tegn for aa markere gaatt vei
      besok();

      // hvis ruten er en aapning er en utvei funnet
      if (this instanceof Aapning){

          // legg til korrdinatet for aapningen
          vei += hentKoord();

          // Skriv ut labyrinten (med gaatt vei) og veiens koordinater som streng
          l.labyrinter.leggTil( l.toString() );
          l.veier.leggTil( vei);

          // tilbakestill tegnene tilbake til første felles rute slik at kun en løsning vises
          gaaTilbake();

          return;
      }
      // siden ruten ikke avslutter veien legg til pil etter koordinatene
      else{
          vei += hentKoord() + "-->";
      }



      // sjekk alle naboer
      for(Rute n : naboer){

          // sjekk at det ikke er forrige rute eller en vegg
          // har kun hviteruter og aapninger som naboer. settes i labyrint.java
          if( n != forrige && n.harBesokt() == false){

              // gaa videre hvis ny hvit eller aapning
              //:musical_note: walk this way, talk this way :musical_note:
              n.gaa(this, vei);
          }

      }

      // endre tegn tilbake til tomt slik at blindveier ikke vises som en del av veien
      gaaTilbake();

  }


  // kaller gaa med seg selv som startrute
  protected void finnUtvei(){
      gaa(this, "");

  }


  // henter koordinater på formatet (x,y)
  protected String hentKoord(){
      return ("(" + x + "," + y + ")");
  }


  // setter besokt flagget til true
  protected void besok(){
      besokt = true;
      tegn = '.';
  }


  // setter besokt flagget til false og endrer utskriftssymbolet
  protected void gaaTilbake(){
      besokt = false;
      settTegn();
  }


  // returner besokt
  protected boolean harBesokt(){
      return besokt;
  }
}
