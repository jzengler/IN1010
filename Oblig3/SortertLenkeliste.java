class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T>{

  //legger til objekter sortert fra minst til størst
  @Override
  public void leggTil(T x){

    // legg til først hvis ingen noder finnes fra før
    if(super.stoerrelse() == 0){
      super.leggTil(x);
    }
    else{
      // opprett iterasjonsvariabler
      Node p = start;
      int i = 0;
      //så lenge p eksisterer
      while(p != null){
        //sammenlign node med indeks i mot ny node
        if(super.hent(i).compareTo(x) <= 0){
          //inkrementer ny nodes indeks hvis mindre
          i++;
        }
        // gå til neste node i lenken
        p = p.neste;
      }
      // legg til ny node i indeks funnet ved sammenligning
      super.leggTil(i, x);
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
