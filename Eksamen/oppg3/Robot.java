import java.util.Random;

class Robot implements Brukergrensesnitt{
    // KLASSEVARIABLER



    // INSTANSVARIABLER



    // KONSTRUKTOER
    Robot(){

    }


    // METODER

    public void giStatus(String status){
        System.out.println(status);
    }

    public int beOmKommando(String spoersmaal, String[] alternativer){

        // skriv ut spoersmaal og alternativene til terminal
        // System.out.println("\n" + spoersmaal);
        //
        // for(int i = 0; i < alternativer.length; i++){
        //     System.out.println(i + ": " + alternativer[i]);
        // }

        Random r = new Random();
        int valg = r.nextInt(alternativer.length);

        // giStatus("ROBOT valgte: " + alternativer[valg]);



        return valg;
    }
}
