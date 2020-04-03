import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class TestFactory{

    public static void main(String[] args) {

        String filnavn = ".\\labyrinter\\4.in";

        File fil = new File(filnavn);
        Labyrint l = null;
        try {
            l = Labyrint.lesFraFil(fil);
                    System.out.println(l);
                    l.finnUtveiFra(7,4);

        } catch (FileNotFoundException e) {
            System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", filnavn);
            System.exit(1);
        }

    }

}
