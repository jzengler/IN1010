public class Spill{


    public static Spiller startSpill(Terreng terreng, Spiller spiller){

        // start spillet
        System.out.println("\nDen magiske reisen begynner...\n");

        for(int i = 0; i < Spillkontroll.ANTALL_TREKK; i++){
            spiller.nyttTrekk();
        }



        System.out.println("\nDen magiske reisen er over\n");
        return spiller;
    }
}
