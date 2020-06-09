import java.util.Random;

public class Spill implements Runnable{

    Terreng terreng;
    Spiller spiller;

    Random r = new Random();


    Spill(Terreng terreng, Spiller spiller){
        this.terreng = terreng;
        this.spiller = spiller;
    }


    public void startSpill(){

        // start spillet

        for(int i = 0; i < Spillkontroll.ANTALL_TREKK; i++){
            spiller.nyttTrekk();

            // sett traaden til aa sove [250,500]ms
            // unngaar at alle robot-traadene fullfoerer for mange trekk foer terminal-spiller
            try{
            Thread.sleep( r.nextInt(250)+251 );
            }
            catch(InterruptedException e){
                System.out.println(e);
            }

        }

    }

    public void run(){

        startSpill();
    }

}
