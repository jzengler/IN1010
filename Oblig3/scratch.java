class scratch{

  public static void main(String[] args) {

    System.out.println("debug/tester");

    Stabel<String> liste = new Stabel<String>();

    liste.leggTil("Element 0");
    liste.leggTil("Element 1");
    liste.leggTil("Element 2");
    liste.leggTil("Element 3");
    liste.leggTil("Element 4");
    liste.sett(0, "nyVerdi 0");
    liste.sett(2, "nyVerdi 2");

    stackTrace(liste);
    System.out.println(liste.fjern(4));
    liste.leggTil("NyttElement");
    stackTrace(liste);




  }



  public static void stackTrace(Stabel<String> liste){
  System.out.println("\nPrinting stack");
  for(int i = 0; i < liste.stoerrelse(); i++){
    System.out.println(liste.hent(i));
  }
  System.out.println("End of stack\n");
  }
}
