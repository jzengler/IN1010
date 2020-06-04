import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;

class Terreng{
    // KLASSEVARIABLER


    // INSTANSVARIABLER
    LinkedList<Sted> terreng = new LinkedList<Sted>();


    // KONSTRUKTOER
    Terreng(String stederFil){

        Sted[] steder = lesStederFil(stederFil);

        for(int i = 0; i < steder.length; i++){
            terreng.add( steder[i] );
        }



    }


    // METODER

    public Sted hentStart(){

        // returner et tilfeldig startsted
        Random r = new Random();
        return terreng.get( r.nextInt( terreng.size() ) );

    }


    // les stedsbeskrivelser fra fil
    public static Sted[] lesStederFil(String filnavn){

        Scanner skanner = null;

        Sted[] temp = new Sted[500];
        Sted[] retur;

        try {
            skanner = new Scanner(new File(filnavn));
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }

        int i = 0;
        while (skanner.hasNextLine()){

            String beskrivelse = skanner.nextLine();

            if(beskrivelse != null){

                temp[i++] = new Sted(beskrivelse);
            }
            // hopper over tomme linjer
            else{
                break;
            }
        }

        // opprett array med rett stoerelse
        retur = new Sted[i];

        // legg til alle stedene i retur-arrayet
        for(int j = 0; j < retur.length; j++){
            retur[j] = temp[j];
            retur[j].leggTilSkattkiste(new Skattkiste( UT.g ));

            // System.out.println(retur[j]);
            // System.out.println(retur[j].hentSkattkiste());

            // hoppes over foerste runde
            if( j != 0){

                // legg til alle nestesteder unttatt for siste element
                if(j < retur.length){
                    retur[j-1].nesteSted = retur[j];
                }
                // siste plass peker tilbake til start
                else{
                    retur[j-1].nesteSted = retur[0];
                }

            }


        }
        return retur;

    }
}
