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

    Labyrint l;

    Stage teater;
    int stageHeight = 200;
    int stageWidth = 450;

    Pane kulisser;

    Button btnAapne;
    Button btnNeste;
    Button btnForrige;
    Button btnNullstill;

    Text txtLosninger;
    Text txtGjeldende;

    int nesteIndeks;
    int antVeier;

    GridPane grid;

    GridCelle rutenett[][];

    class GridCelle extends Button{

        GridCelle(int celleSize){
            setMinSize(celleSize, celleSize);
            setPrefSize(celleSize,celleSize);
            setEffect(null);
            setStyle("-fx-border-radius: 0; -fx-background-radius: 0;");
            FinnLosningBehandler finn = new FinnLosningBehandler();
            setOnAction(finn);
        }

    }

    public class FinnLosningBehandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {

            settLabyrintGrid();
            btnNeste.setDisable(false);
            btnForrige.setDisable(false);

            GridCelle celle = (GridCelle)event.getSource();

            l.finnUtveiFra( grid.getColumnIndex(celle), grid.getRowIndex(celle));

            nesteIndeks = 0;

            antVeier = l.veier.stoerrelse();

            txtLosninger.setText("Fant " + antVeier + " l\u00F8sning(er)" );

            if(antVeier > 0){
                settUtveiGrid(l.veier.hent(nesteIndeks));
                txtGjeldende.setText("Viser l\u00F8sning " + (nesteIndeks+1) + " av " + antVeier);
            }
            else{
                txtGjeldende.setText("Ingen utveier");
            }
        }
    }

    public class AapneFilBehandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();

            // sett start-sti og begrens filtyper
            fileChooser.setInitialDirectory(new File("."));

            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Maze Files", "*.in")
            );

            File selectedFile = fileChooser.showOpenDialog(new Stage());
            System.out.println(selectedFile);

            try {
                // Labyrint l = null;
                l = Labyrint.lesFraFil(selectedFile);
            } catch (FileNotFoundException e) {
                System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", selectedFile);
                System.exit(1);
            }


            settLabyrintGrid();
            txtLosninger.setText("Velg en rute for \u00E5 finne veier");
            txtGjeldende.setText("");
        }
    }

    public class NesteLosningBehandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {

            nesteIndeks++;


            if(l.veier.stoerrelse() > nesteIndeks){

                settLabyrintGrid();
                btnForrige.setDisable(false);
                settUtveiGrid(l.veier.hent(nesteIndeks));
                txtGjeldende.setText("Viser l\u00F8sning " + (nesteIndeks+1) + " av " + antVeier);
            }
            else{
                nesteIndeks--;
                btnNeste.setDisable(true);
            }
        }
    }

    public class ForrigeLosningBehandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {

            nesteIndeks--;

            if(l.veier.stoerrelse() > nesteIndeks && nesteIndeks >= 0){

                settLabyrintGrid();
                btnNeste.setDisable(false);
                settUtveiGrid(l.veier.hent(nesteIndeks));
                txtGjeldende.setText("Viser l\u00F8sning " + (nesteIndeks+1) + " av " + antVeier);
            }
            else{
                nesteIndeks++;
                btnForrige.setDisable(true);
            }
        }
    }

    public class NullstillBehandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {

            // nullstiller labyrinten
            settLabyrintGrid();
            txtGjeldende.setText("");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        teater = stage;

        teater.setX(stageWidth);
        teater.setY(stageHeight);
        teater.setTitle("Labyrint-l\u00F8ser");


        // Knapp for å åpne filvelger
        btnAapne = new Button();
        btnAapne.setText("\u00C5pne labyrint-fil");
        btnAapne.setMinWidth(100);
        btnAapne.setLayoutY(stageHeight - 30);
        btnAapne.setLayoutX(10);
        AapneFilBehandler aapne = new AapneFilBehandler();
        btnAapne.setOnAction(aapne);

        btnNeste = new Button();
        btnNeste.setText("Neste l\u00F8sning");
        btnNeste.setMinWidth(100);
        btnNeste.setLayoutY(stageHeight - 30);
        btnNeste.setLayoutX(120);
        btnNeste.setDisable(true);
        NesteLosningBehandler neste = new NesteLosningBehandler();
        btnNeste.setOnAction(neste);

        btnForrige = new Button();
        btnForrige.setText("Forrige l\u00F8sning");
        btnForrige.setMinWidth(100);
        btnForrige.setLayoutY(stageHeight - 30);
        btnForrige.setLayoutX(230);
        btnForrige.setDisable(true);
        ForrigeLosningBehandler forrige = new ForrigeLosningBehandler();
        btnForrige.setOnAction(forrige);

        btnNullstill = new Button();
        btnNullstill.setText("Nullstill");
        btnNullstill.setMinWidth(100);
        btnNullstill.setLayoutY(stageHeight - 30);
        btnNullstill.setLayoutX(stageWidth - 110);
        NullstillBehandler nullstill = new NullstillBehandler();
        btnNullstill.setOnAction(nullstill);

        txtLosninger = new Text("");
        txtLosninger.setY(30);

        txtGjeldende = new Text("");
        txtGjeldende.setY(50);

        // startkulissen. innholder kun knapp for å åpne labyrintfil
        kulisser = new Pane();
        kulisser.getChildren().add(btnAapne);
        kulisser.getChildren().add(txtLosninger);

        teater.setScene(new Scene(kulisser, stageWidth, stageHeight));
        teater.show();
    }



    public void settLabyrintGrid(){
        int celleSize = 10;

        grid = new GridPane();


        int sceneWidth = celleSize * l.kolonner * 2;
        if(sceneWidth < stageWidth){
            sceneWidth = stageWidth;
        }

        int sceneHeight = celleSize * l.rader;
        if(sceneHeight < stageHeight){
            sceneHeight = stageHeight;
        }

        rutenett = new GridCelle[l.kolonner][l.rader];

        for(int x = 0; x < l.kolonner; x++){
            for(int y = 0; y < l.rader; y++){


                GridCelle c = new GridCelle(celleSize);

                if(l.ruter[x][y].tilTegn() == '#'){
                    c.setStyle("-fx-background-color: black");
                }

                rutenett[x][y] = c;
                grid.add(c,x,y);

            }
        }



        kulisser = new Pane();
        btnAapne.setLayoutY(sceneHeight + 10);
        btnNeste.setLayoutY(sceneHeight + 10);
        btnForrige.setLayoutY(sceneHeight + 10);
        btnNullstill.setLayoutY(sceneHeight + 10);
        txtLosninger.setX(sceneWidth / 2 + 20);
        txtGjeldende.setX(sceneWidth / 2 + 20);

        kulisser.getChildren().addAll(btnAapne, btnNeste, btnForrige, btnNullstill);
        kulisser.getChildren().addAll(txtLosninger, txtGjeldende);
        kulisser.getChildren().add(grid);
        //
        teater.setScene(new Scene(kulisser, sceneWidth, sceneHeight + 40));
        teater.show();
    }

    public void settUtveiGrid(String vei){

        if(vei.length() > 0){
            boolean utvei[][];
            utvei = losningStringTilTabell(vei, l.kolonner, l.rader);

            for(int x = 0; x < l.kolonner; x++){
                for(int y = 0; y < l.rader; y++){

                    if(utvei[y][x] == true){
                        rutenett[x][y].setStyle("-fx-background-color: green");
                    }

                }
            }
        }
    }

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

}
