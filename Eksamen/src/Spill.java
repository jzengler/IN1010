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
        Terreng steder = new Terreng(STEDER_FIL);
        Spiller pers = null;
        Spiller npc = null;


        Scanner skan = new Scanner(System.in);

        System.out.println("Velg et navn");
        String navn = skan.nextLine();

        Terminal term = new Terminal( new Scanner(System.in) );
        pers = new Spiller(navn, steder.hentStart(), term);


        System.out.println("Vil du spille mot en robot? [J,N]");
        String comp = skan.next().toUpperCase();

        if("JA".contains(comp)){

            Robot rob = new Robot();
            npc = new Spiller("Rob", steder.hentStart(), rob);

        }

        System.out.println("\nDen magiske reisen begynner...\n");

        for(int i = 0; i < ANTALL_TREKK; i++){

            pers.nyttTrekk();

            if(npc != null){
                npc.nyttTrekk();
            }

        }

        System.out.println("\nDen magiske reisen er over\n");

        if(npc != null){
            if( pers.hentFormue() > npc.hentFormue() ){
                System.out.println(pers.hentNavn() + " vant!!!");
            }
            else{
                System.out.println(npc.hentNavn() + " vant...");
            }

            System.out.println(npc);
        }

        System.out.println(pers);





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
