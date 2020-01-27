public class Node{

  // Instansvariabler
  private int minneStoerrelse;
  private int prosessorAntall;



  // Konstruktoer
  Node(int minneStoerrelse, int prosessorAntall){

    this.minneStoerrelse = minneStoerrelse;
    this.prosessorAntall = prosessorAntall;

  }



  // Metoder
  // returnerer nodens minnestoerrelse
  public int minne(){

    return minneStoerrelse;

  }

  // returnerer nodens prosessorantall
  public int prosessorer(){

    return prosessorAntall;

  }

}
