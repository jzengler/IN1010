import java.util.ArrayList;
import java.util.concurrent.*;


abstract class Rute{

  //Instansvariabler
  private int x;
  private int y;

  private Labyrint l;
  private ArrayList<Rute> naboer = new ArrayList<Rute>();

  private volatile boolean besokt = false;
  protected volatile char tegn;


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


      }
      // siden ruten ikke avslutter veien legg til pil etter koordinatene
      else{
          vei += hentKoord() + "-->";
      }

      // finn antall naboer
      int antTraader = naboer.size();
      // opprett array til å holde trådene
      Thread[] t= new Thread[antTraader];
      Rute n;

      // hopper over første nabo slik at hovedtråden kan kalle gaa på den rekursivt
      // fører til at første element alltid vil være tomt
      for(int i = 1; i < antTraader; i++){

          n = naboer.get(i);

          if( n != forrige && n.harBesokt() == false){

              // gaa videre hvis ny hvit eller aapning

              // Opprett objekt av typen monitor
              Monitor mon = new Monitor(this, n, vei);

              // lag og start ny monitor-tråd
              t[i] = new Thread(mon, this.hentKoord()+":"+i);
              t[i].start();
              System.out.println("startet traad " + t[i].getName());

              //:musical_note: walk this way, talk this way :musical_note:
              // n.gaa(this,vei);
          }

      }

      if(antTraader > 0){
          n = naboer.get(0);
          if( n != forrige && n.harBesokt() == false){

              n.gaa(this, vei);

          }
      }

      // Synkroniser alle tråder før vi gaar tilbake
      for(int i = 1; i < antTraader; i++){

          try{
              System.out.println("avslutter traad " + t[i].getName());
              t[i].join();
          }
          catch(Exception e){
              // System.out.println(t[i].getName() );
              // System.out.println("traad finnes ikke");
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
