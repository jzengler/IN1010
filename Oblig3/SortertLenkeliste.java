class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T>{

  //legger til objekter sortert fra minst til størst
  @Override
  public void leggTil(T x){

    int n = super.stoerrelse();

    //Hvis lenken ikke inneholder noen noder
    if(n == 0 ){
      super.leggTil(x);
    }
    //hvis det finnes noder, sjekk alle noder helt til treff på større verdi enn x
    else{
      //fikk ikke denne til med while
      // unødvendig gjennomgang av hele lenken
      int indeks = 0;
      for ( int i = 0; i < n; i++){
        if( super.hent(i).compareTo(x) <= 0){
          indeks++;
        }
      }
      super.leggTil(indeks, x);
    }
    return;
  }

  //fjerner største objekt, dvs siste i lenken
  @Override
  public T fjern(){

    int i = super.stoerrelse() - 1;
    return super.fjern(i);

  }

  //kaster unntak ved forsøk på endring av verdi i listen
  @Override
  public void sett(int post, T x){
    throw new UnsupportedOperationException("Kan ikke sette inn objektet i sortert liste");
  }

  //kaster unntak ved forsøk på tillegg i listen
  @Override
  public void leggTil(int pos, T x){
    throw new UnsupportedOperationException("Kan ikke legge til objektet i sortert liste");
  }


}
