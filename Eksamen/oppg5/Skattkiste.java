import java.util.Random;
import java.util.ArrayList;

class Skattkiste{
    // KLASSEVARIABLER



    // INSTANSVARIABLER
    // magisk kiste med "ubegrenset" plass
    public ArrayList<Gjenstand> innhold = new ArrayList<Gjenstand>();
    Random r = new Random();


    // KONSTRUKTOER
    Skattkiste(Gjenstand[] gjenstander){

        // Tar imot alle gjenstander saa noen kan velges tilfeldig

        //  sett et random antall gjenstander i intervallet [1,Spill.PLASS_I_SEKK]
        int antallGjenstander = r.nextInt(Spillkontroll.PLASS_I_SEKK)+1;

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

        // Henter en ny tilfeldig gjenstand istedefor aa legge spillerens gjenstand ned.
        // Fikset problemet at spilleren ofte fikk tilbake samme gjenstand
        innhold.add(Terreng.gjenstander[ r.nextInt(Terreng.gjenstander.length)]);

        // returner verdi [100%,110%]
        return verdi + r.nextInt(verdi + 1)/10;

    }

    //  la til parameteren for aa la spilleren velge en gjenstand selv
    //  lagde opprinnelig en overload av metoden
    //  men jeg syntes det ble mer oversiktlig aa benytte random
    //  foer kallet paa denne andre steder
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
