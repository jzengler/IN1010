import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class UT{

    public static String stederFil = "steder.txt";
    public static String gjenstanderFil = "gjenstander.txt";
    public static int antallTrekk = 10;
    public static Gjenstand[] g;



    public static void main(String[] args){

        g = lesGjenstanderFil(gjenstanderFil);

        String stederFil = "steder.txt";
        Terreng s = new Terreng(stederFil);


        // String gjenstanderFil = "gjenstander.txt";
        // Gjenstand[] items = lesGjenstanderFil(gjenstanderFil);
        //
        // Skattkiste kiste = new Skattkiste(items);
        // System.out.println("innhold\n" + kiste + "\n");
        // System.out.println( "tar ut: " + kiste.taUtGjenstand() );
        // System.out.println("innhold\n" + kiste + "\n");
        // System.out.println("legger ned: " + items[0] + " fikk denne: " + kiste.leggNedGjenstand(items[0]) );
        // System.out.println("innhold\n" + kiste  + "\n");
        // //
        // // Terminal t = new Terminal(new Scanner(System.in));
        // // Robot r = new Robot();
        //
        // // String[] alt = {"test", "alt", "jada"};
        //
        // // r.giStatus("ROBOT info");
        // // r.beOmKommando("Hva velger du?", alt);
        // //
        // // t.giStatus("HUMAN tekst");
        // // t.beOmKommando("Hva velger du?", alt);



    }

    // flytt dit det passer senere
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
