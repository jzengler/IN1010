import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;

class Terreng{
    // KLASSEVARIABLER


    // INSTANSVARIABLER
    Sted[] terreng;

    // KONSTRUKTOER
    Terreng(String stederFil){

        terreng = lesStederFil(stederFil);

    }


    // METODER

    public Sted hentStart(){

        // returner et tilfeldig startsted
        Random r = new Random();
        return terreng[ ( r.nextInt( terreng.length ) ) ];

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

        // saa lenge det er flere linjer i filen
        while (skanner.hasNextLine()){

            // hent neste linje
            String beskrivelse = skanner.nextLine();

            // legg til hvis strengen ikke er tom
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
            retur[j].leggTilSkattkiste(new Skattkiste( Spill.gjenstander ));

            // System.out.println(retur[j]);
            // System.out.println(retur[j].hentSkattkiste());

            // hoppes over foerste runde
            if( j > 0){
                // legg til alle nestesteder unttatt for siste element
                    retur[j-1].nesteSted = retur[j];
            }
        }

        // sett siste til aa peke paa foerste
        retur[retur.length - 1].nesteSted = retur[0];

        return retur;

    }
}
