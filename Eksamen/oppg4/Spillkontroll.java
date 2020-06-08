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

        // Robot r = new Robot();
        // VeivalgTerreng vvt = new VeivalgTerreng(STEDER_FIL, GJENSTANDER_FIL);
        // VeivalgSpiller vvs = new VeivalgSpiller("test", vvt.hentStart(), r);
        // Spiller poeng = Spill.startSpill(vvt, vvs);

        // for aa les inn spiller valg
        Scanner skan = new Scanner(System.in);

        // spiller navn
        System.out.println("Velg et navn");
        String navn = skan.nextLine().toUpperCase();

        // menneske eller robot?
        System.out.println("Vil du spille selv? [J]");
        String menneske = skan.next().toUpperCase();

        // terreng valg
        System.out.println("Vil du spille med enkelt terreng? [J]");
        String enkelt = skan.next().toUpperCase();


        // instansier robot eller terminal

        Brukergrensesnitt grensesnitt;

        if("JA".contains(menneske)){
            grensesnitt = new Terminal( new Scanner(System.in) );
        }
        else{
            grensesnitt = new Robot();
        }

        Terreng terreng;
        Spiller spiller;

        if("JA".contains(enkelt)){
            terreng = new Terreng(STEDER_FIL, GJENSTANDER_FIL);
            spiller = new Spiller(navn, terreng.hentStart(), grensesnitt);
        }
        else{
            terreng = new VeivalgTerreng(STEDER_FIL, GJENSTANDER_FIL);
            spiller = new VeivalgSpiller(navn, terreng.hentStart(), grensesnitt);
        }

        // START SPILLET
        Spiller poeng = Spill.startSpill(terreng, spiller);
        resultat = poeng.toString();

        // // lag traad som venter 5 sek foer den avslutter GUI
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
