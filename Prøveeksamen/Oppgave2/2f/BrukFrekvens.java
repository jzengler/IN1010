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

public class BrukFrekvens extends Application{

    Stage stage;

    String[] datasett;
    Frekvens frekvens;

    Text txtVanligst = new Text("Vanligst:");
    Text txtAntall = new Text("Antall:");
    Text txtListe = new Text("");

    class VelgFilBehandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event){
            FileChooser fileChooser = new FileChooser();
            // sett start-sti og begrens filtyper
            fileChooser.setInitialDirectory(new File("."));

            // tillater kun valg av filer av typen .txt
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text files", "*.txt")
            );

            // aapne filutforskeren
            File selectedFile = fileChooser.showOpenDialog(new Stage());

            // les labyrint fra fil
            datasett = lesFil(selectedFile);

            // last inn datasett, komprimer, finn flest forekomster og oppdater tekstbokser i GUI
            frekvens = new Frekvens(datasett);
            frekvens.komprimer();
            frekvens.finnFlest();
            txtVanligst.setText( "Vanligst: " + frekvens.hentFlest() );
            txtAntall.setText( "Antall: " + frekvens.hentAntall() );

            txtListe.setText(frekvens.hentListe());

        }
    }


    class StoppBehandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event){
            System.exit(0);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }



    public void start(Stage stage){

        stage.setTitle("Frekvens");

        Button btnVelgFil = new Button();
        btnVelgFil.setText("Velg fil");
        btnVelgFil.setMinWidth(80);
        btnVelgFil.setLayoutY(10);
        btnVelgFil.setLayoutX(10);
        VelgFilBehandler fil = new VelgFilBehandler();
        btnVelgFil.setOnAction(fil);


        Button btnStopp = new Button();
        btnStopp.setText("Avslutt");
        btnStopp.setMinWidth(80);
        btnStopp.setLayoutY(360);
        btnStopp.setLayoutX(10);
        StoppBehandler stopp = new StoppBehandler();
        btnStopp.setOnAction(stopp);


        // tekstfelt for vanligste
        txtVanligst.setY(60);
        txtVanligst.setX(10);

        // tekstfelt for antall
        txtAntall.setY(90);
        txtAntall.setX(10);

        txtListe.setY(60);
        txtListe.setX(200);

        Pane pane = new Pane();
        pane.getChildren().addAll(btnVelgFil, btnStopp, txtVanligst, txtAntall, txtListe);

        stage.setScene(new Scene(pane, 400, 400));
        stage.show();

    }


    public String[] lesFil(File fil){

        String[] innData = new String[1000];
        int linjer = 0;

        Scanner scanner = null;

        try {
            scanner = new Scanner(fil);
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }

        while (scanner.hasNextLine()){

            innData[linjer++] = scanner.nextLine();
        }

        String[] tekster = new String[linjer];

        for (int i = 0; i < tekster.length; i++ ){
            tekster[i] = innData[i];
        }

        return tekster;

    }
}



class Frekvens{

    class Node{

        public Node neste = null;
        String tekst;
        int antall = 1;

        Node(String t){
            tekst = t;
        }

    }


    //INSTANSVARIABLER
    private Node start = null;
    private int antallNoder = 0;

    private String flestTekst = "";
    private int flestAntall = 0;


    //KONSTRUKTOER
    Frekvens(String[] tekster){

        // start med tom lenkeliste
        Node denne = start;

        // loop gjennom datasettet og sett inn noder i lenkelisten
        for(int i = 0; i < tekster.length; i++){

            Node nyNode = new Node(tekster[i]);
            antallNoder++;

            // hvis lenkelisten er tom
            if( start == null ){
                start = nyNode;
                denne = start;
            }
            else{
                denne.neste = nyNode;
                denne = denne.neste;
            }
        }
    }



    //METODER
    public void finnFlest(){

        Node denne = start;
        flestAntall = 0;

        while(denne != null){

            if(denne.antall > flestAntall){
                flestAntall = denne.antall;
                flestTekst = denne.tekst;
            }

            denne = denne.neste;
        }




    }

    public String hentFlest(){
        return flestTekst;
    }

    public int hentAntall(){
        return flestAntall;
    }


    public void komprimer(){

        // ny lenke denneKomp
        Node denneKomp = start;
        Node denne = start.neste;

        // saa lenge noden etter komp-noden ikke er null
        while(denne != null){

            // er neste node i den opprinnelige lenken den samme som i den komprimerte?
            if(denneKomp.tekst.equals(denne.tekst)){

                // inkrementer antall i komprimert-node
                denneKomp.antall++;

            }
            else{
                // link ulik node i den komprimerte lenken
                denneKomp.neste = denne;
                // flytt til neste i komprimert lenke
                denneKomp = denneKomp.neste;
            }

            // flytt til neste i den opprinnelige lenken
            denne = denne.neste;



        }

        // unlink resterende noder fra den opprinnelige listen
        denneKomp.neste = null;

    }

    public String hentListe(){

        String liste = "";
        Node denne = start;

        while (denne != null){
            liste += denne.tekst + " : " + denne.antall + "\n";

            denne = denne.neste;

        }

        return liste;
    }
}
