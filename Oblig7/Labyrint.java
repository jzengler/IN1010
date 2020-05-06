import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Labyrint{



    //Instansvariabler
    // kolonner(x) og rader(y)
    public int kolonner;
    public int rader;

    // "matrise" til å holde alle rutene
    public Rute[][] ruter;

    // holder alle veier på koordinatform
    protected Liste<String> veier;

    // holder alle veier som grafisk streng
    protected Liste<String> labyrinter;



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
    // static factory for labyrint
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



    //returner en representasjon av labyrinten som streng
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



    // hjelpemetode for å legge til naboer i rute
    private void finnNabo(Rute rute, int x, int y){

         // #+#
         // +x+
         // #+#

         int y_min = y - 1;
         int y_max = y + 1;
         int x_min = x - 1;
         int x_max = x + 1;
         char vegg = '#';

         // sjekk nabo over
         if( y_min >= 0){
             if(ruter[x][y_min].tilTegn() != vegg){
                 rute.leggTilNabo(ruter[x][y_min]);
            }
         }

         // sjekk nabo venstre
         if(x_min >= 0){
             if(ruter[x_min][y].tilTegn() != vegg){
                 rute.leggTilNabo(ruter[x_min][y]);
            }
         }

         // sjekk nabo under
         if(y_max < rader){
             if(ruter[x][y_max].tilTegn() != vegg){
                 rute.leggTilNabo(ruter[x][y_max]);
             }
         }

         // sjekk nabo høyre
         if(x_max < kolonner){
             if(ruter[x_max][y].tilTegn() != vegg){
                 rute.leggTilNabo(ruter[x_max][y]);
            }
         }

    }



    // hjelpemetode for å legge til referanse til labyrint til alle rutene
    private void tilordneLabyrint(){

        // for hver rute registrer naboer
        for(int x = 0; x < kolonner; x++){
            for(int y = 0; y < rader; y++){
                ruter[x][y].tilordneLabyrint(this);
            }
        }
    }



    // metode for å starte søk etter utvei
    public void finnUtveiFra(int x, int y){

        veier = new Lenkeliste<String>();
        labyrinter = new Lenkeliste<String>();

        if(ruter[x][y].tilTegn() != '#'){
            ruter[x][y].finnUtvei();
        }
        else{
            System.out.println("Koordinatene " + x + "," + y + " er ikke en vei");
        }

        // return veier;
    }



    // skriver ut korteste vei og antallet veier funnet.
    public void skrivUtKorteste(){

        if(veier.stoerrelse() != 0 ){
            String kortesteVei = veier.hent(0);

            for(int i = 0; i < veier.stoerrelse(); i++){

                if(veier.hent(i).length() < kortesteVei.length() ){
                    kortesteVei = veier.hent(i);
                }
            }

            System.out.println("\nFant " + veier.stoerrelse() + " veier");

            System.out.println("\nKorteste vei: \n" + kortesteVei);

            String[] vei = kortesteVei.split("-->");
            System.out.println("Veien bestaar av " + vei.length + " ruter");

        }
    }


    // skriver ut alle veier på koordinatform
    public void skrivUtVeierDetaljert(){
        if (veier.stoerrelse() > 0) {

            for (String s : veier){
                System.out.println("\n" + s);
            }

            skrivUtKorteste();
        }
        else {
            System.out.println("Ingen utveier.");
        }
    }


    // skriver ut alle veier grafisk og på koordinatform
    public void skrivUtVeierFull(){

        int antall = veier.stoerrelse();

        if (antall > 0) {

            for (int i = 0; i < antall; i++){
                System.out.println( labyrinter.hent(i) );
                System.out.println( veier.hent(i) );
            }

            skrivUtKorteste();
        }
        else {
            System.out.println("Ingen utveier.");
        }
    }

}
