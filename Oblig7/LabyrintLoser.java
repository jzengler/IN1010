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
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.*;



public class LabyrintLoser extends Application {

    public Labyrint l;
    public Stage stage;




    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage stage) {

        int stageWidth = 200;
        int stageHeight = 200;

        stage.setX(stageWidth);
        stage.setY(stageHeight);
        stage.setTitle("Labyrint-l\u00F8ser");


        // Knapp for å åpne filvelger
        Button btn = new Button();
        btn.setText("\u00C5pne labyrint-fil");
        btn.setLayoutY(stageHeight - 30);
        btn.setLayoutX(stageWidth/2 - 50);
        AapneFilBehandler aapne = new AapneFilBehandler();
        btn.setOnAction( {aapne; setLabyrintGrid });

        // startkulissen. innholder kun knapp for å åpne labyrintfil
        kulisser = new Pane();
        kulisser.getChildren().add(btn);

        stage.setScene(new Scene(kulisser, stageWidth, stageHeight));
        stage.show();





    }



    public void setLabyrintGrid(){
        int cellSize = 10;

        GridPane grid = new GridPane();


        int sceneWidth = cellSize * l.kolonner;
        int sceneHeight = cellSize * l.rader;

        for(int x = 0; x < l.kolonner; x++){
            for(int y = 0; y < l.rader; y++){

                Rectangle r = new Rectangle(0,0,cellSize,cellSize);

                if(l.ruter[x][y].tilTegn() == '#'){

                    r.setFill(Color.BLACK);
                }
                else{
                    r.setFill(Color.WHITE);
                }
                grid.add(r,x,y);
            }
        }



        Pane kulisser = new Pane();
        kulisser.getChildren().add(grid);
        kulisser.getChildren().add(btn);

        stage.setScene(new Scene(kulisser, sceneWidth, sceneHeight + 20));
        stage.show();
    }




    public class AapneFilBehandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();

            // sett start-sti og begrens filtyper
            fileChooser.setInitialDirectory(new File("C:\\Users\\juzen\\Documents\\GIT\\IN1010\\Oblig7\\labyrinter"));

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
        }
    }
}
