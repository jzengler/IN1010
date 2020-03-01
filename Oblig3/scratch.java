class scratch{

  public static void main(String[] args) {

    Liste<String> liste = new SortertLenkeliste<String>();
    




  }



  public static void stackTrace(Stabel<Integer> liste){
  System.out.println("\nPrinting stack");
  for(int i = 0; i < liste.stoerrelse(); i++){
    System.out.println(liste.hent(i));
  }
  System.out.println("End of stack\n");
  }
}
