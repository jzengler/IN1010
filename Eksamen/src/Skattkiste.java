import java.util.Random;
import java.util.ArrayList;

class Skattkiste{
    // KLASSEVARIABLER



    // INSTANSVARIABLER
    private ArrayList<Gjenstand> innhold = new ArrayList<Gjenstand>();


    // KONSTRUKTOER
    Skattkiste(Gjenstand[] gjenstander){

        Random r = new Random();

        //  sett et random antall gjenstander i intervallet [1,5]
        int antallGjenstander = r.nextInt(5) + 1;

        // fyll med tilfeldig gjenstander, utelater "tomme" plasser
        for(int i = 0; i < antallGjenstander; i++){
                innhold.add( gjenstander[ r.nextInt(gjenstander.length) ] );
        }
    }


    // METODER

    // public Gjenstand[] hentInnhold(){
    //
    // }
    //
    public int leggNedGjenstand(Gjenstand gjenstand){

        int verdi = gjenstand.hentVerdi();
        innhold.add(gjenstand);

        Random r = new Random();

        return verdi + r.nextInt(verdi)/10;

    }

    public Gjenstand taUtGjenstand(){

        // velg tilfeldig indeks fra listen med gjenstander
        Random r = new Random();
        int i = r.nextInt( innhold.size() );

        // fjern gjenstand og returner
        return innhold.remove(i);
    }


    public String toString(){

        String alleGjenstander = "";

        for(int i = 0; i < innhold.size(); i++){
            alleGjenstander = alleGjenstander + innhold.get(i) + "\n";
        }

        return alleGjenstander;
    }
}
