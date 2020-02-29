class scratch{

  public static void main(String[] args) {

    System.out.println("debug/tester");

    Lenkeliste<String> liste = new Lenkeliste<String>();

    try{
      liste.hent(0);
    }
    catch(UgyldigListeIndeks e){
      System.out.println("fant den");
    }




  }



  public static void stackTrace(Stabel<Integer> liste){
  System.out.println("\nPrinting stack");
  for(int i = 0; i < liste.stoerrelse(); i++){
    System.out.println(liste.hent(i));
  }
  System.out.println("End of stack\n");
  }
}
