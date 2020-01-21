public class Node{

  // Instansvariabler
  private int minneStoerrelse;
  private int prosessorAntall;

  // Konstruktør
  Node(int minneStoerrelse, int prosessorAntall){

    this.minneStoerrelse = minneStoerrelse;
    this.prosessorAntall = prosessorAntall;

  }

  // Metoder

  // returnerer nodens minnestørrelse
  public int minne(){

    return minneStoerrelse;

  }

  // returnerer nodens prosessorantall
  public int prosessorer(){

    return prosessorAntall;

  }

}
