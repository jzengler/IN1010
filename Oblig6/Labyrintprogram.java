import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Labyrintprogram{

    public static void main(String[] args) {

        //modifisert kode kopiert fra Oblig5.java
        String filnavn = null;
        String utskrift = null;

        if (args.length > 0) {
            filnavn = args[0];

            if (args.length == 2){
                utskrift = args[1];
            }
            else{
                utskrift = "korteste";
            }

        }
        else {
            System.out.println("FEIL! Riktig bruk av programmet: "
                               +"java Labyrintprogram <labyrintfil> [detaljert]");
            return;
        }



        File fil = new File(filnavn);
        Labyrint l = null;
        try {
            l = Labyrint.lesFraFil(fil);
        } catch (FileNotFoundException e) {
            System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", filnavn);
            System.exit(1);
        }

        // les start-koordinater fra standard input
        Scanner inn = new Scanner(System.in);
        System.out.println("Skriv inn koordinater <kolonne> <rad> ('a' for aa avslutte)");
        String[] ord = inn.nextLine().split(" ");
        while (!ord[0].equals("a")) {

            try {
                int startKol = Integer.parseInt(ord[0]);
                int startRad = Integer.parseInt(ord[1]);

                // bruker ikke returverdi, egne metoder for utskrift
                l.finnUtveiFra(startKol, startRad);

                if( utskrift.equals("detaljert") ){
                    l.skrivUtVeierDetaljert();
                }
                else{
                    l.skrivUtKorteste();
                }

                System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig input!");
            }

            System.out.println("Skriv inn nye koordinater ('a' for aa avslutte)");
            ord = inn.nextLine().split(" ");
        }

    }

}
