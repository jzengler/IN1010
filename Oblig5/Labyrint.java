import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Labyrint{

    //Instansvariabler
    // kolonner(x) og rader(y)
    private int kolonner;
    private int rader;
    private Rute[][] ruter;




    //Konstruktør
    private Labyrint(int kolonner, int rader, Rute[][] ruter){

        this.kolonner = kolonner;
        this.rader = rader;
        this.ruter = ruter;

        // for hver rute registrer naboer
        for(int x = 0; x < kolonner; x++){
            for(int y = 0; y < rader; y++){

                // ruter[x][y].iLabyrint(this);
                finnNabo(ruter[x][y], x, y);

            }
        }
    }

    //Metoder

    //returner labyrinten som streng
    public String toString(){
        String rutenett = "\n";

        //legg til alle ruter i en streng. ny linje mellom hver rad.
        for(int y = 0; y < rader; y++){
            for(int x = 0; x < kolonner; x++){
                rutenett += (ruter[x][y].tilTegn() + " ");
            }
            // newline før neste rad
            rutenett += "\n";
        }

        // returner hele strengen
        return rutenett;

    }

    static Labyrint lesFraFil(File fil)throws FileNotFoundException{

        Scanner scanner = new Scanner(fil);

        //les antall rader og kolonner
        int rader = scanner.nextInt();
        int kolonner = scanner.nextInt();
        Rute[][] ruter = new Rute[kolonner][rader];

        //må lese linjeskift etter rader og kolonner.
        scanner.nextLine();

        // array indeks hjelpere
        int x = 0;
        int y = 0;

        //les labyrint-symbolene
        while( scanner.hasNextLine() ){

            // hent neste linje av labyrinten fra filen
            String linje = scanner.nextLine();
            // char c;


            // for hvert tegn i linjen
            for(x = 0; x < kolonner; x++ ){

                char c = linje.charAt(x);


                if( c == '#' ){
                    ruter[x][y] = new SortRute(x, y);
                }
                else if(x == 0 || x == kolonner-1 || y == 0 || y == rader-1){
                    ruter[x][y] = new Aapning(x, y);
                }
                else{
                    ruter[x][y] = new HvitRute(x, y);
                }

            }
            y++;
        }

        Labyrint l = new Labyrint(kolonner, rader, ruter);

        l.tilordneLabyrint();

        return l;

    }

    private void finnNabo(Rute rute, int x, int y){

         // #+#
         // +x+
         // #+#

         // sjekk nabo over
         if( y - 1 >= 0){
             rute.leggTilNabo(ruter[x][y-1]);
         }

         // sjekk nabo venstre
         if(x - 1 >= 0){
             rute.leggTilNabo(ruter[x-1][y]);
         }

         // sjekk nabo under
         if(y + 1 < rader){
             rute.leggTilNabo(ruter[x][y+1]);
         }

         // sjekk nabo høyre
         if(x + 1 < kolonner){
             rute.leggTilNabo(ruter[x+1][y]);
         }

    }

    private void tilordneLabyrint(){

        // for hver rute registrer naboer
        for(int x = 0; x < kolonner; x++){
            for(int y = 0; y < rader; y++){
                ruter[x][y].tilordneLabyrint(this);
            }
        }
    }

    public void finnUtveiFra(int x, int y){

        if(ruter[x][y].tilTegn() != '#'){
            ruter[x][y].finnUtvei();
        }
        else{
            System.out.println("Koordinatene " + x + "," + y + " er ikke en vei");
        }
    }

}
