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
        resultat = poeng.toString();

        // lag traad som venter 5 sek foer den avslutter GUI
        Thread avslutt = new Thread( new StoppGUI() );
        avslutt.start();

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
