class Lenkeliste<T> implements Liste<T>{

  class Node{

    Node neste = null;
    T data;

    Node(T x){
       data = x;
    }
  }


//Instansvariabler
private Node start = null;
public static int antallNoder = 0;

//Konstruktør



//metoder

//done
public int stoerrelse(){
  Node p = start;
  int n = 0;

  while( p != null){
      n++;
      p = p.neste;
  }

  return n;
}

//done
public void leggTil(int pos, T x){
  if (pos < 0 || pos >= antallNoder)
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

//done
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

//done
public void sett(int pos, T x){
  if (pos < 0 || pos>=antallNoder)
    throw new UgyldigListeIndeks(pos);

  Node p = start;

  for(int i = 0; i < pos; i++){
    p = p.neste;
  }

  p.data = x;
  return;
}

//done
public T hent(int pos){
  if (pos < 0 || pos >= antallNoder)
    throw new UgyldigListeIndeks(pos);

  Node p = start;

  for(int i = 0; i < pos; i++){
    p = p.neste;
  }

  return p.data;
}

//done
public T fjern(int pos){
  if (pos < 0 || pos >= antallNoder)
    throw new UgyldigListeIndeks(pos);

  Node p = start;
  Node r = null;

  if(pos == 0){
    start = p.neste;
  }
  else{
    for(int i = 0; i < pos-2; i++){
      p = p.neste;
    }

    r = p.neste;
    if(r.neste != null){
      p.neste = r.neste;
      p = p.neste;
    }
    else{
      p.neste = null;
    }
  }

  //dekrementer antallNoder
  antallNoder--;
  return p.data;
}

//done
public T fjern(){
  if (antallNoder == 0){
    throw new UgyldigListeIndeks(antallNoder);
  }


  //mellomlagrer første node
  Node p = start;

  // overskriver start med andre node i lenken slik at første tas ut
  start = p.neste;

  //dekrementer antall noder
  antallNoder--;

  // returnerer den første noden
  return p.data;
}
}
