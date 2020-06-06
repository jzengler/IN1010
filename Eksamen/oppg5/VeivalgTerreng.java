import java.util.Random;

class VeivalgTerreng extends Terreng{
    // KLASSEVARIABLER



    // INSTANSVARIABLER


    // KONSTRUKTOER
    VeivalgTerreng(String stederFil, String gjenstanderFil){
        super(stederFil, gjenstanderFil);

        int antallSteder = steder.length;

        // steder = (VeivalgSted[])steder;

        for(int i = 0; i < antallSteder; i++){


            // random vei hoyre
            int h = r.nextInt(antallSteder);
            // random vei venstre
            int v = r.nextInt(antallSteder);

            // caster til VeivalgSted siden den inneholde unike metoder
            // er det bad practice?
            ((VeivalgSted)steder[i]).settVeier( ((VeivalgSted)steder[i].nesteSted), (VeivalgSted)steder[h], (VeivalgSted)steder[v] );
        }
    }


    // METODER
}
