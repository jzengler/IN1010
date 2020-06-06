import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

class Spillkontroll{

    public final static String STEDER_FIL = "steder.txt";
    public final static String GJENSTANDER_FIL = "gjenstander.txt";
    public final static int ANTALL_TREKK = 6;
    public final static int PLASS_I_SEKK = ANTALL_TREKK / 2;



    public static void main(String[] args){

        Terreng terreng = new Terreng(STEDER_FIL, GJENSTANDER_FIL);

        // les inn navn paa spiller
        Scanner skan = new Scanner(System.in);

        System.out.println("Velg et navn");
        String navn = skan.nextLine().toUpperCase();

        // menneske eller robot?
        System.out.println("Vil du spille selv? [J]");
        String menneske = skan.next().toUpperCase();

        Spiller spiller;

        if("JA".contains(menneske)){
            Terminal term = new Terminal( new Scanner(System.in) );
            spiller = new Spiller(navn, terreng.hentStart(), term);
        }
        else{
            Robot rob = new Robot();
            spiller = new Spiller(navn, terreng.hentStart(), rob);
        }

        // start spillet
        Spiller poeng = Spill.startSpill(terreng, spiller);
        System.out.println(poeng.toString());
    }
}
