import java.util.Iterator;

class Lenkeliste<T> implements Liste<T>{

  //indre klasse for støtteobjekt
  class Node{

    Node neste = null;
    T data;

    Node(T x){
      data = x;
    }
  }

  //indre klasse for iterator
  class LenkelisteIterator implements Iterator<T>{
    //Hvordan skal dette implementeres egentlig?


    //Instansvariabler
    protected Node p = start;

    //sjekk om element eksisterer
    public boolean hasNext(){
      if( p != null ){
        return true;
      }
      else{
        return false;
      }
    }

    //returner neste element
    public T next(){

      Node temp = p;

      //flytter peker til neste node slik at hasNext() kan sjekke
      p = p.neste;

      //returner data fra elementet vi startet med i metoden
      return temp.data;
    }


    public void remove(){}

  }



  //Instansvariabler
  protected Node start = null;
  private int antallNoder = 0;

  //Konstruktør



  //returnerer antallet noder i lenken
  public int stoerrelse(){
    return antallNoder;
  }

  //legger til node i gitt posisjon
  public void leggTil(int pos, T x){
    if (pos < 0 || pos > antallNoder)
    throw new UgyldigListeIndeks(pos);

    //Oppretter ny node
    Node nyNode = new Node(x);

    //oppretter p og setter til start som utgangspunkt for løkken.
    Node p = start;


    if(pos == 0){
      nyNode.neste = start;
      start = nyNode;
    }
    else{
      // gå videre i lenken så lenge "p" peker på en node
      for(int n = 0; n < pos-1; n++){
        p = p.neste;
      }
      // legg til peker til objektet etter ønsket posisjon
      nyNode.neste = p.neste;

      // lagre den nye noden i neste-peker i angitt posisjonen
      p.neste = nyNode;

    }
    //inkrementer antallNoder
    antallNoder++;
    return;
  }

  //legger til node på slutten av lenken
  public void leggTil(T x){

    //Oppretter ny node
    Node nyNode = new Node(x);

    //legg til som første node i lenken hvis ikke node eksisterer i start
    if(start == null){
      start = nyNode;
    }
    //legg node til i slutten av lenken
    else{

      //oppretter p og setter til start som utgangspunkt for løkken.
      Node p = start;

      // gå videre i lenken så lenge "neste" peker på en node
      while(p.neste != null){
        p = p.neste;
      }

      // lagre den nye noden i første tomme neste-peker, dvs enden av lenken
      p.neste = nyNode;
    }

    //inkrementer antallNoder
    antallNoder++;
    return;
  }

  //overskriver data i node i pos
  public void sett(int pos, T x){
    if (pos < 0 || pos>=antallNoder || start == null)
    throw new UgyldigListeIndeks(pos);

    Node p = start;

    for(int i = 0; i < pos; i++){
      p = p.neste;
    }

    p.data = x;
    return;
  }

  //returnerer data fra node i pos
  public T hent(int pos){
    if (pos < 0 || pos >= antallNoder || start == null)
    throw new UgyldigListeIndeks(pos);

    Node p = start;

    for(int i = 0; i < pos; i++){
      p = p.neste;
    }

    return p.data;
  }

  //fjerner node i pos fra lenken og returnerer data
  public T fjern(int pos){
    if (pos < 0 || pos >= antallNoder || start == null)
    throw new UgyldigListeIndeks(pos);

    Node p = start;
    Node r = null;

    // hvis første node skal fjernes
    if(pos == 0){
      start = p.neste;
    }
    else{
      //stop på noden før den som skal fjernes
      for(int i = 0; i < pos-1; i++){
        p = p.neste;
      }

      // mellomlagre noden som skal fjernes
      r = p.neste;
      // hvis noden ikke er den siste i lenken
      // noden før må peke til neste etter noden som skal fjernes
      if(r.neste != null){
        p.neste = r.neste;
      }
      else{
        p.neste = null;
      }

      // noden som er fjernet legges i p
      p = r;

    }

    //dekrementer antallNoder
    antallNoder--;
    //returner data til den fjernede noden
    return p.data;
  }

  //fjerner node fra starten av lenken og returnerer data
  public T fjern(){
    if (start == null)
    throw new UgyldigListeIndeks(antallNoder);


    //mellomlagrer første node
    Node p = start;

    // overskriver start med andre node i lenken slik at første tas ut
    start = p.neste;

    //dekrementer antall noder
    antallNoder--;

    // returnerer den første noden
    return p.data;
  }

  //iterator objekt
  public Iterator<T> iterator(){
     LenkelisteIterator iterator = new LenkelisteIterator();
     return iterator;
   }


}
