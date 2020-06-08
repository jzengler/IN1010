public class Spill implements Runnable{

    Terreng terreng;
    Spiller spiller;



    Spill(Terreng terreng, Spiller spiller){
        this.terreng = terreng;
        this.spiller = spiller;
    }


    public void startSpill(){

        // start spillet

        for(int i = 0; i < Spillkontroll.ANTALL_TREKK; i++){
            spiller.nyttTrekk();
        }

    }

    public void run(){
        startSpill();
    }

}
