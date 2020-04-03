import java.util.ArrayList;
abstract class Rute{

  //Instansvariabler
  int x;
  int y;

  Labyrint l;
  ArrayList<Rute> naboer = new ArrayList<Rute>();

  private boolean harBesokt = false;

  //konstruktør
  Rute(int x, int y){
    this.x = x;
    this.y = y;
  }


  //metoder
  abstract char tilTegn();

  // legger til en rute som nabo
  protected void leggTilNabo(Rute nabo){
      naboer.add(nabo);
  }

  // grisete måte å legge til labyrint referansen etter at ruten er opprettet
  protected void tilordneLabyrint(Labyrint l){
      this.l = l;
  }

  // kaller gaa() på naborutene rekursivt
  protected String gaa(Rute forrige, String vei){

      besok();

      for(Rute n : naboer){

          if(n != forrige && n.tilTegn() != '#'){

              vei += hentKoord() + "-->";

              if(n instanceof Aapning){
                  vei += n.hentKoord();
                  System.out.println(vei);
                  vei += "\n";
                  return vei;
              }
              else if(n.harBesokt() == true){
                  return "Dead end!";
              }

              // walk this way, talk this way
              n.gaa(this, vei);

          }

      }

      return vei;

  }


  protected void finnUtvei(){
      gaa(this, "");
  }

  protected String hentKoord(){
      return ("(" + x + "," + y + ")");
  }

  protected void besok(){
      harBesokt = true;
  }

  protected boolean harBesokt(){
      return harBesokt;
  }
}
