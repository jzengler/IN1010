// Hvis hovedtråden går videre før nye tråder blir opprettet blir ikke søket parallelisert.
// Hver "gren" i treet blir rekursivt undersøkt før trådene til naboer opprettes
// Kan føre til at en løsning blir oversett



import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;



public abstract class Rute{

  //Instansvariabler
  private int x;
  private int y;

  protected Labyrint l;
  private ArrayList<Rute> naboer = new ArrayList<Rute>();

  protected char tegn;
  private Lock laas = new ReentrantLock();

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

      // hvis ruten er en aapning er en utvei funnet
      if (this instanceof Aapning){

          // legg til korrdinatet for aapningen
          vei += hentKoord();

          // Skriv ut labyrinten (med gaatt vei) og veiens koordinater som streng
          laas.lock();

          try{
              l.veier.leggTil( vei);
          }
          finally{
              laas.unlock();
          }
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
      // fører til at første element ( t[0] ) alltid vil være tomt
      for(int i = 1; i < antTraader; i++){

          n = naboer.get(i);

          if( n != forrige){

              // gaa videre hvis ny hvit eller aapning

              // Opprett objekt av typen monitor
              Monitor mon = new Monitor(this, n, vei);

              // lag og start ny monitor-tråd
              t[i] = new Thread(mon, this.hentKoord()+":"+i);
              t[i].start();
              // System.out.println("startet traad " + t[i].getName());

              //:musical_note: walk this way, talk this way :musical_note:
              // n.gaa(this,vei);
          }

      }

      // Synkroniser alle tråder før vi gaar tilbake
      for(Thread q : t){

          try{
              // System.out.println("avslutter traad " + t[i].getName());

              q.join();
          }
          catch(Exception e){
              // System.out.println(t[i].getName() );
          }

      }

          n = naboer.get(0);
          if( n != forrige){

              // System.out.println("Går til neste rute med hovedtråden");
              n.gaa(this, vei);

          }


  }


  // kaller gaa med seg selv som startrute
  protected void finnUtvei(){

      gaa(this, "");
  }


  // henter koordinater på formatet (x,y)
  protected String hentKoord(){
      return ("(" + x + "," + y + ")");
  }
}
