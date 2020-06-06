import java.util.Random;

class Robot implements Brukergrensesnitt{
    // KLASSEVARIABLER



    // INSTANSVARIABLER
    Random r = new Random();


    // KONSTRUKTOER
    Robot(){

    }


    // METODER

    public void giStatus(String status){
        System.out.println(status);
    }

    public int beOmKommando(String spoersmaal, String[] alternativer){

        // velg tilfeldig indeks
        int valg = r.nextInt(alternativer.length);

        return valg;
    }
}
