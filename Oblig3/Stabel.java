class Stabel<T> extends Lenkeliste<T>{


  //metoder

  //push
  public void leggPaa(T x){
    leggTil(x);
    return;
  }

  //pop
  public T taAv(){
    int n = stoerrelse() - 1;
    return fjern(n);
  }
}
