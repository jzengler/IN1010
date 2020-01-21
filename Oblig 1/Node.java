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

  public int minne(){

    return minneStoerrelse;

  }

  public int prosessorer(){

    return prosessorAntall;

  }

}
