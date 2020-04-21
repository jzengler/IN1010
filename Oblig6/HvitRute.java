class HvitRute extends Rute{

  //Instansvariabler
  // protected char tegn = ' ';

  //konstruktør
  HvitRute(int x, int y){
    super(x, y);
    settTegn();
  }

  //metoder
  // returner char for ruten
  public char tilTegn(){
    return tegn;
  }

 // setter rutens tegn
  protected void settTegn(){
      tegn = ' ';
  }

}
