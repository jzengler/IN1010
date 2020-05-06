import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.scene.paint.*;
import javafx.scene.text.*;



public class LabyrintLoser extends Application {



    // Variabler til GUI

    Labyrint l;

    // stage variabler
    Stage teater;
    int stageHeight = 200;
    int stageWidth = 450;

    // kulisser variabel
    Pane kulisser;

    // diverse knapper
    Button btnAapne;
    Button btnNeste;
    Button btnForrige;
    Button btnNullstill;

    // tekstfelt
    Text txtLosninger;
    Text txtGjeldende;

    // tellere ifm visning av utveier
    int nesteIndeks;
    int antVeier;

    // rutenett
    GridPane grid;
    GridCelle rutenett[][];
    int celleSize = 12;



    // klasse som representerer hver rute i labyrinten
    class GridCelle extends Button{

        // Konstruktør
        GridCelle(int celleSize){

            // setter minste og ønskede størrelse på "knappen"
            setMinSize(celleSize, celleSize);
            setPrefSize(celleSize,celleSize);
            // forsøkte å sette minst mulig stiler på knappen. Ikke veldig vellykket...
            setEffect(null);
            setStyle("-fx-border-radius: 0; -fx-background-radius: 0;");

            // velg behandler for handling ved trykk på rute
            FinnLosningBehandler finn = new FinnLosningBehandler();
            setOnAction(finn);
        }

    }



    // Behandler-klasser
    // finner utvei fra valgt rute ved å kalle på metoder i Labyrint.java
    class FinnLosningBehandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {

            // labyrinten tømmes for veier ved å sette den på nytt
            settLabyrintGrid();

            // deaktiver knappene for neste og forrige vei
            btnNeste.setDisable(false);
            btnForrige.setDisable(false);

            // finn hvilken celle som ble trykket på
            GridCelle celle = (GridCelle)event.getSource();

            // finn koordinatene til cellen i rutenettet og finn utvei derfra
            l.finnUtveiFra( grid.getColumnIndex(celle), grid.getRowIndex(celle));

            // mellomlagre for å spare noen flops
            antVeier = l.veier.stoerrelse();

            // fyll tekstfeltet for antallet løsninger
            txtLosninger.setText("Fant " + antVeier + " l\u00F8sning(er)" );

            // holder styr på hvilken vei som vises
            nesteIndeks = 0;

            // vis første utveien i listen til labyrinten
            if(antVeier > 0){
                settUtveiGrid(l.veier.hent(nesteIndeks));
                txtGjeldende.setText("Viser l\u00F8sning " + (nesteIndeks+1) + " av " + antVeier);
            }
            else{
                txtGjeldende.setText("Ingen utveier");
            }
        }
    }



    // Åpner filbehandler så bruker kan velge labyrintfil
    class AapneFilBehandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();

            // sett start-sti og begrens filtyper
            fileChooser.setInitialDirectory(new File("."));

            // tillater kun valg av filer av typen .in
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Maze Files", "*.in")
            );

            // åpne filutforskeren
            File selectedFile = fileChooser.showOpenDialog(new Stage());

            // les labyrint fra fil
            try {
                l = Labyrint.lesFraFil(selectedFile);
            } catch (FileNotFoundException e) {
                System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", selectedFile);
                System.exit(1);
            }

            // vis labyyrinten og sett start-tekst
            settLabyrintGrid();
            txtLosninger.setText("Velg en rute for \u00E5 finne veier");
            txtGjeldende.setText("");
        }
    }



    // Viser neste utvei
    class NesteLosningBehandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {

            // inkrementer hver gang Neste-knappen brukes
            nesteIndeks++;

            // sjekk at det faktisk er flere veier
            if(l.veier.stoerrelse() > nesteIndeks){

                // tømmer labyrinten
                settLabyrintGrid();

                // aktiver forrige-knappen siden vi nå har gått videre
                btnForrige.setDisable(false);

                // vis utvei i labyrint
                settUtveiGrid(l.veier.hent(nesteIndeks));
                txtGjeldende.setText("Viser l\u00F8sning " + (nesteIndeks+1) + " av " + antVeier);
            }
            else{
                // ingen flere veier, dekrementer for å "nulle" indeksen
                nesteIndeks--;

                // deaktiver Neste-knappen
                btnNeste.setDisable(true);
            }
        }
    }



    // Viser forrige utvei
    class ForrigeLosningBehandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {

            // dekrementer for å holde styr på hvilken vei som vises
            nesteIndeks--;

            if(l.veier.stoerrelse() > nesteIndeks && nesteIndeks >= 0){

                // tøm labyrinten
                settLabyrintGrid();

                // sørg for at nest knappen er aktiv
                btnNeste.setDisable(false);

                // vis gjeldende utvei
                settUtveiGrid(l.veier.hent(nesteIndeks));
                txtGjeldende.setText("Viser l\u00F8sning " + (nesteIndeks+1) + " av " + antVeier);
            }
            else{
                // "null" indeksen og deaktiver forrige-knappen når det ikke er flere veier
                nesteIndeks++;
                btnForrige.setDisable(true);
            }
        }
    }



    // tømmer rutenettet for utveier - beholder løsningene for valgt rute
    class NullstillBehandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {

            // nullstiller labyrinten
            settLabyrintGrid();
            txtGjeldende.setText("");
        }
    }




    // Metoder

    // oppretter valgt labyrint
    private void settLabyrintGrid(){

        // opprett ny grid til cellene
        grid = new GridPane();

        // sett bredden, men ikke mindre en teateret
        int sceneWidth = celleSize * l.kolonner * 2;
        if(sceneWidth < stageWidth){
            sceneWidth = stageWidth;
        }

        // sett høyden, men ikke mindre enn teateret
        int sceneHeight = celleSize * l.rader;
        if(sceneHeight < stageHeight){
            sceneHeight = stageHeight;
        }

        // 2D-array til labyrint-cellene
        rutenett = new GridCelle[l.kolonner][l.rader];

        // loop gjennom hele arrayet og legg til celler
        for(int x = 0; x < l.kolonner; x++){
            for(int y = 0; y < l.rader; y++){

                // ny "knapp" aka GridCelle
                GridCelle c = new GridCelle(celleSize);

                // sjekk om det er en vegg
                if(l.ruter[x][y].tilTegn() == '#'){
                    c.setStyle("-fx-background-color: black");
                }

                // lagre cellen i rutenettet
                rutenett[x][y] = c;
                // legg til cellen i griden
                grid.add(c,x,y);

            }
        }


        // juster plassering av knapper og tekstbokser ift labyrintens størrelse
        kulisser = new Pane();
        btnAapne.setLayoutY(sceneHeight + 10);
        btnNeste.setLayoutY(sceneHeight + 10);
        btnForrige.setLayoutY(sceneHeight + 10);
        btnNullstill.setLayoutY(sceneHeight + 10);
        grid.setLayoutX(20);
        txtLosninger.setX(sceneWidth / 2 + 30);
        txtGjeldende.setX(sceneWidth / 2 + 30);

        // legg til alle elementer i kulissen
        kulisser.getChildren().addAll(btnAapne, btnNeste, btnForrige, btnNullstill);
        kulisser.getChildren().addAll(txtLosninger, txtGjeldende);
        kulisser.getChildren().add(grid);

        // start showet
        teater.setScene(new Scene(kulisser, sceneWidth, sceneHeight + 40));
        teater.show();
    }

    // markerer utvei i grønt
    private void settUtveiGrid(String vei){

        if(vei.length() > 0){

            //  hent kovertert vei til bool array
            boolean utvei[][];
            utvei = losningStringTilTabell(vei, l.kolonner, l.rader);

            // sett bakgrunnsfargen til rutene som er en del av veien
            for(int x = 0; x < l.kolonner; x++){
                for(int y = 0; y < l.rader; y++){

                    if(utvei[y][x] == true){
                        rutenett[x][y].setStyle("-fx-background-color: green");
                    }

                }
            }
        }
    }

    // Hentet fra semesterside IN1010
    // konverterer (string) vei til (boolean) array
    static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
        boolean[][] losning = new boolean[hoyde][bredde];
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
        java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
        while (m.find()) {
            int x = Integer.parseInt(m.group(1));
            int y = Integer.parseInt(m.group(2));
            losning[y][x] = true;
        }
        return losning;
    }


    public static void main(String[] args) {
        launch(args);
    }

    // javafx start metoden
    @Override
    public void start(Stage stage) {
        // måtte gjøre dette for å få metodene til fungere
        teater = stage;

        //  velg plassering for GUI
        teater.setX(50);
        teater.setY(50);
        teater.setTitle("Labyrint-l\u00F8ser");


        // Knapp for å åpne filvelger
        btnAapne = new Button();
        btnAapne.setText("\u00C5pne labyrint-fil");
        btnAapne.setMinWidth(100);
        btnAapne.setLayoutY(stageHeight - 30);
        btnAapne.setLayoutX(10);
        AapneFilBehandler aapne = new AapneFilBehandler();
        btnAapne.setOnAction(aapne);

        // knapp for å velge neste utvei
        btnNeste = new Button();
        btnNeste.setText("Neste l\u00F8sning");
        btnNeste.setMinWidth(100);
        btnNeste.setLayoutY(stageHeight - 30);
        btnNeste.setLayoutX(120);
        btnNeste.setDisable(true);
        NesteLosningBehandler neste = new NesteLosningBehandler();
        btnNeste.setOnAction(neste);

        // knapp for å velge forrige utvei
        btnForrige = new Button();
        btnForrige.setText("Forrige l\u00F8sning");
        btnForrige.setMinWidth(100);
        btnForrige.setLayoutY(stageHeight - 30);
        btnForrige.setLayoutX(230);
        btnForrige.setDisable(true);
        ForrigeLosningBehandler forrige = new ForrigeLosningBehandler();
        btnForrige.setOnAction(forrige);

        // knapp for å tømme labyrinten for utveier
        btnNullstill = new Button();
        btnNullstill.setText("Nullstill");
        btnNullstill.setMinWidth(100);
        btnNullstill.setLayoutY(stageHeight - 30);
        btnNullstill.setLayoutX(stageWidth - 110);
        NullstillBehandler nullstill = new NullstillBehandler();
        btnNullstill.setOnAction(nullstill);

        // tekstfelt for totalt antall løsninger
        txtLosninger = new Text("");
        txtLosninger.setY(30);

        // tekstfelt for gjeldende løsning
        txtGjeldende = new Text("");
        txtGjeldende.setY(50);

        // startkulissen. innholder kun knapp for å åpne labyrintfil
        // kjipern, men sånn er livet
        kulisser = new Pane();
        kulisser.getChildren().add(btnAapne);
        kulisser.getChildren().add(txtLosninger);

        teater.setScene(new Scene(kulisser, stageWidth, stageHeight));
        teater.show();
    }

}
