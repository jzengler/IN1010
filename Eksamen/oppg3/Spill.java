import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.*;
import javafx.stage.FileChooser;

class Spill{

    public final static String STEDER_FIL = "steder.txt";
    public final static String GJENSTANDER_FIL = "gjenstander.txt";
    public final static int ANTALL_TREKK = 2;
    public final static int PLASS_I_SEKK = ANTALL_TREKK / 2;

    Stage stage;


    public static void main(String[] args){

        Terreng terreng = new Terreng(STEDER_FIL, GJENSTANDER_FIL);

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

        // launch(her)?
        System.out.println("\nDen magiske reisen er over\n");
        System.out.println(spiller);
    }

    // class StoppBehandler implements EventHandler<ActionEvent>{
    //
    //     @Override
    //     public void handle(ActionEvent event){
    //         System.exit(0);
    //     }
    // }

}
