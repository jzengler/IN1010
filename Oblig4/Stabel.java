class Stabel<T> extends Lenkeliste<T>{


  //metoder

  //push
  public void leggPaa(T x){
    //legger til node på slutten av stacken
    leggTil(x);
    return;
  }

  //pop
  public T taAv(){
    //hent størrelse på stacken og angi indeks
    int n = stoerrelse() - 1;
    // fjern siste element og returner innhold
    return fjern(n);
  }
}
