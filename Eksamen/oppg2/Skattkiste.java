import java.util.Random;
import java.util.ArrayList;

class Skattkiste{
    // KLASSEVARIABLER



    // INSTANSVARIABLER
    // magisk kiste med "ubegrenset" plass
    public ArrayList<Gjenstand> innhold = new ArrayList<Gjenstand>();


    // KONSTRUKTOER
    Skattkiste(Gjenstand[] gjenstander){

        // Tar imot alle gjenstander saa noen kan velges tilfeldig

        //  sett et random antall gjenstander i intervallet [1,Spill.PLASS_I_SEKK]
        Random r = new Random();
        int antallGjenstander = r.nextInt(Spill.PLASS_I_SEKK)+1;

        // fyll med tilfeldig gjenstander
        for(int i = 0; i < antallGjenstander; i++){
                innhold.add( gjenstander[ r.nextInt(gjenstander.length) ] );
        }
    }


    // METODER

    public Gjenstand[] hentInnhold(){

        Gjenstand[] ting = new Gjenstand[ innhold.size() ];

        for(int i = 0; i < ting.length; i++){
            ting[i] = innhold.get(i);
        }

        return ting;

    }

    public int leggNedGjenstand(Gjenstand gjenstand){

        // hent verdien til gjenstanden
        int verdi = gjenstand.hentVerdi();

        // legg gjenstanden i kisten uten aa endre verdi
        // legges bakerst
        innhold.add(gjenstand);

        // returner verdi [100%,110%]
        Random r = new Random();
        return verdi + r.nextInt(verdi + 1)/10;

    }

    // public Gjenstand taUtGjenstand(){
    //
    //     // velg tilfeldig indeks fra listen med gjenstander
    //     Random r = new Random();
    //     int i = r.nextInt( innhold.size() );
    //
    //     // fjern gjenstand og returner
    //     return innhold.remove(i);
    // }

    //  overload for aa la spilleren velge en gjenstand selv
    public Gjenstand taUtGjenstand(int indeks){

        // fjern gjenstand og returner
        return innhold.remove(indeks);
    }

    public int antallGjenstander(){
        return innhold.size();
    }

    public String toString(){

        String alleGjenstander = "";

        for(int i = 0; i < innhold.size(); i++){
            alleGjenstander = alleGjenstander + innhold.get(i) + "\n";
        }

        return alleGjenstander;
    }
}
