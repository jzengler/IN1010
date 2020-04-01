abstract Rute{

  //Instansvariabler
  int y;
  int x;
  Labyrint l;
  int[] naboer;

  //konstruktør
  Rute(Labyrint l){
    this.l = l;
  }

  //metoder
  abstract char tilTegn();

}
