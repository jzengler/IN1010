import java.util.Scanner;

class UT{


    public static void main(String[] args){
        Terminal t = new Terminal(new Scanner(System.in));

        String[] alt = {"test", "alt", "jada"};

        t.giStatus("skriv til terminal");
        t.beOmKommando("Hva velger du?", alt);



    }

}
