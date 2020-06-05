import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Spill{

    public final static String STEDER_FIL = "steder.txt";
    public final static String GJENSTANDER_FIL = "gjenstander.txt";
    public final static int ANTALL_TREKK = 2;
    public final static int PLASS_I_SEKK = ANTALL_TREKK / 2;
    public static Gjenstand[] gjenstander;



    public static void main(String[] args){

        gjenstander = lesGjenstanderFil(GJENSTANDER_FIL);
        Terreng terreng = new Terreng(STEDER_FIL);

        // les inn navn paa spiller
        Scanner skan = new Scanner(System.in);

        System.out.println("Velg et navn");
        String navn = skan.nextLine();

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
        System.out.println("\nDen magiske reisen begynner...\n");

        for(int i = 0; i < ANTALL_TREKK; i++){

            spiller.nyttTrekk();

        }

        System.out.println("\nDen magiske reisen er over\n");
        System.out.println(spiller);
    }

    // les listen med objekter til en statisk variabel
    // Skattkiste henter tilfeldige elementer fra listen
    public static Gjenstand[] lesGjenstanderFil(String filnavn){

        Scanner skanner = null;

        Gjenstand[] temp = new Gjenstand[500];
        Gjenstand[] retur;

        try {
            skanner = new Scanner(new File(filnavn));
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }

        int i = 0;
        while (skanner.hasNextLine()){

            if( skanner.hasNext() ){
                String type = skanner.next();

                if( skanner.hasNextInt()){
                    int verdi = skanner.nextInt();

                    temp[i++] = new Gjenstand(type, verdi);
                }
            }
            // hopper over tomme linjer
            else{
                break;
            }
        }

        retur = new Gjenstand[i];

        for(int j = 0; j < retur.length; j++){
            retur[j] = temp[j];
        }



        return retur;

    }

}
