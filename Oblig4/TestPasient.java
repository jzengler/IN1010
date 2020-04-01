class TestPasient {
  // Klassevariabler

  // Instansvariabler

  // Konstruktoer

  // main
  public static void main(String[] args) {
    // Oppretter 4 instanser av legemidler
    Narkotisk morfin = new Narkotisk("morfin", 1000, 10, 70);
    Vanedannende otrivin = new Vanedannende("Otrivin", 100, 10, 18);
    Vanedannende viagra = new Vanedannende("Viagra", 75, 5, 9);
    Vanlig paracetamol = new Vanlig("Paracetamol", 10, 500);
    Vanlig pPiller = new Vanlig("P-piller", 200, 50);

    // Skriver ut alle instansene av legemidler
    // System.out.println(morfin);
    // System.out.println(otrivin);
    // System.out.println(paracet);
    // System.out.println(pPiller);

    // Oppretter to instanser av leger
    Lege vanliglege = new Lege("Kjip-Lege");
    Spesialist superlege = new Spesialist("Super-Lege", 1337);

    // Skriver ut alle instansene av leger
    //System.out.println(vanliglege);
    //System.out.println(superlege);

    Pasient pas0 = new Pasient("RCW","2209");
    Pasient pas1 = new Pasient("JZ","0805");
    Pasient pas2 = new Pasient("EV","1604");
    Pasient pas3 = new Pasient("HW","0101");


    System.out.println("PasientID foerste pasient: " + pas0.hentId());
    System.out.println("PasientID andre pasient: " + pas1.hentId());


    // Oppretter noen instanser av resepter
    Blaa blaaresept1 = new Blaa(otrivin,superlege,pas0,4);
    Blaa blaaresept2 = new Blaa(viagra,vanliglege,pas0,10);
    Hvit hvitreseptvanlig = new Hvit(paracetamol,vanliglege,pas1,13);
    MilResept hvitMilResept = new MilResept(morfin,superlege,pas2,2);
    PResept hvitpresept = new PResept(pPiller,vanliglege,pas3);

    // Skriver ut tester for å se om det stemmer med input ovenfor
    // System.out.println(blaaresept1);
    // System.out.println(hvitreseptvanlig);
    // System.out.println(hvitMilResept);
    // System.out.println(hvitpresept);

    // Legger resepter til pasienter. 1 av 4 skal bestå testen
    pas0.leggTilResept(blaaresept1);
    pas0.leggTilResept(blaaresept2);
    pas0.leggTilResept(hvitreseptvanlig);
    pas0.leggTilResept(hvitMilResept);
    pas0.leggTilResept(hvitpresept);
    pas1.leggTilResept(blaaresept1);
    pas1.leggTilResept(hvitreseptvanlig);
    pas1.leggTilResept(hvitMilResept);
    pas1.leggTilResept(hvitpresept);
    pas2.leggTilResept(blaaresept1);
    pas2.leggTilResept(hvitreseptvanlig);
    pas2.leggTilResept(hvitMilResept);
    pas2.leggTilResept(hvitpresept);
    pas3.leggTilResept(blaaresept1);
    pas3.leggTilResept(hvitreseptvanlig);
    pas3.leggTilResept(hvitMilResept);
    pas3.leggTilResept(hvitpresept);

    // pas0.hentResepter();
    // pas1.hentResepter();
    // pas2.hentResepter();
    // pas3.hentResepter();
    // System.out.println( pas0.hentResepter() );
    // System.out.println( pas1.hentResepter() );
    // System.out.println( pas2.hentResepter() );
    // System.out.println( pas3.hentResepter() );

//    System.out.println(pas0.toString());

    // System.out.println(pas1.toString());
    // System.out.println(pas2.toString());
    // System.out.println(pas3.toString());

    System.out.println(pas0.hentObjektMedResept());

  }

}
