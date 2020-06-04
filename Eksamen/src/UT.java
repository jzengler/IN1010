import java.util.Scanner;

class UT{


    public static void main(String[] args){
        Terminal t = new Terminal(new Scanner(System.in));

        Robot r = new Robot();


        String[] alt = {"test", "alt", "jada"};

        r.giStatus("ROBOT info");
        r.beOmKommando("Hva velger du?", alt);


        t.giStatus("HUMAN tekst");
        t.beOmKommando("Hva velger du?", alt);





    }

}
