import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import javafx.application.Platform;

public class Spillkontroll extends Application{

    public final static String STEDER_FIL = "steder.txt";
    public final static String GJENSTANDER_FIL = "gjenstander.txt";
    public final static int ANTALL_TREKK = 6;
    public final static int PLASS_I_SEKK = ANTALL_TREKK / 2;

    static String resultat = "";

    // Stage stage;
    Stage stage;

    public static void main(String[] args){

        // for aa les inn spiller valg
        Scanner skan = new Scanner(System.in);


        // menneske eller robot?
        System.out.println("Vil du spille selv? [J]");
        String menneske = skan.next().toUpperCase();

        System.out.println("Hvor mange roboter skal spille? [heltall]");
        int antallRoboter = skan.nextInt();

        // terreng valg
        System.out.println("Vil du spille med enkelt terreng? [J]");
        String enkelt = skan.next().toUpperCase();


        //  Oppsett enkelt eller flere veivalg
        Terreng terreng;

        if("JA".contains(enkelt)){
            terreng = new Terreng(STEDER_FIL, GJENSTANDER_FIL);
        }
        else{
            terreng = new VeivalgTerreng(STEDER_FIL, GJENSTANDER_FIL);
        }


        // instansier robot og Terminal
        // opprett terminal-spiller
        Spiller[] spillere;
        String navn = "";
        Brukergrensesnitt terminal = null;

        if("JA".contains(menneske)){

            spillere = new Spiller[antallRoboter + 1];

            // spiller navn
            System.out.println("Skriv inn et navn");
            navn = skan.next().toUpperCase();

            terminal = new Terminal( new Scanner(System.in) );

        }
        else{
            spillere = new Spiller[antallRoboter];
        }



        // oppretter robot-grensesnitt selv om det ikke maa brukes
        Brukergrensesnitt robot = new Robot();

        // instansier spiller eller veivalgspiller
        // enkelt terreng
        if("JA".contains(enkelt)){

            if("JA".contains(menneske)){
                spillere[antallRoboter] = new Spiller(navn, terreng.hentStart(), terminal);
            }

            for(int i = 0; i < antallRoboter; i++){
                spillere[i] = new Spiller("R0b0" + i, terreng.hentStart(), robot);

            }
        }
        // veivalg terreng
        else{

            if("JA".contains(menneske)){
                spillere[antallRoboter] = new VeivalgSpiller(navn, terreng.hentStart(), terminal);
            }

            for(int i = 0; i < antallRoboter; i++){
                spillere[i] = new VeivalgSpiller("R0b0" + i, terreng.hentStart(), robot);
            }

        }



        // START SPILLET
        Spiller[] poeng = Spill.startSpill(terreng, spillere);


        // SPILL FERDIG
        // sorter spillerene etter hoyeste score
        Arrays.sort(poeng);

        // lagre sortert liste i static streng saa GUI kan hente
        for(int i = 0; i < poeng.length; i++){
            resultat = resultat + "\n" + (i+1) + " : " + poeng[i].toString();
        }

        // lag traad som venter 5 sek foer den avslutter GUI
        // Thread avslutt = new Thread( new StoppGUI() );
        // avslutt.start();

        launch(args);
    }

    // miniklasse for aa avslutte javafx etter 5 sekunder
    static class StoppGUI implements Runnable{
        public void run(){

            try{
                Thread.sleep(5000);
            }
            catch (InterruptedException error){
                System.out.println(error);
            }

            Platform.exit();
        }
    }

    // hentet fra oblig 7
    class StoppBehandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event){
            System.exit(0);
        }
    }

    // hentet fra oblig 7, lett modifisert
    public void start(Stage stage){

        stage.setTitle("Den magiske reisen");

        Button btnStopp = new Button();
        btnStopp.setText("Avslutt");
        btnStopp.setMinWidth(80);
        btnStopp.setLayoutY(260);
        btnStopp.setLayoutX(20);
        StoppBehandler stopp = new StoppBehandler();
        btnStopp.setOnAction(stopp);

        // tekstfelt for antall
        Text txtTittel = new Text("Resultat");
        txtTittel.setY(20);
        txtTittel.setX(20);

        Text txtListe = new Text(resultat);
        txtListe.setY(50);
        txtListe.setX(20);

        Pane pane = new Pane();
        pane.getChildren().addAll(btnStopp, txtTittel, txtListe);

        stage.setScene(new Scene(pane, 300, 300));
        stage.show();

    }

}
