public class Spill{


    public static Spiller[] startSpill(Terreng terreng, Spiller[] spillere){

        // start spillet
        System.out.println("\nDen magiske reisen begynner...\n");

        //  la alle spillere faa utfoere trekk i tur og orden
        for(int i = 0; i < Spillkontroll.ANTALL_TREKK; i++){
            for(int j = 0; j < spillere.length; j++){

                spillere[j].nyttTrekk();

            }
        }



        System.out.println("\nDen magiske reisen er over\n");

        return spillere;
    }
}
