class TestLege {
  // Klassevariabler

  // Instansvariabler

  // Konstruktoer

  // main
  public static void main(String[] args) throws Exception {
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

    // Oppretter noen testpasienter
    Pasient pas0 = new Pasient("RCW","2209");
    Pasient pas1 = new Pasient("JZ","0805");
    Pasient pas2 = new Pasient("EV","1604");
    Pasient pas3 = new Pasient("HW","0101");

    // Skriver ut to vanlige hvite resepter. Forventer suksess
    vanliglege.skrivHvitResept(paracetamol,vanliglege,pas0,7);
    vanliglege.skrivHvitResept(pPiller,vanliglege,pas2,10);

    // Sjekker om vanlig lege fikk lagt til to vanlige Legemiddel. Skal ha blitt lagt til
    System.out.println( vanliglege.hentObjektMedResept() );

    // Ber vanlig lege skrive ut narkotisk middel. Forventer feil.
    // vanliglege.skrivHvitResept(morfin,vanliglege,pas0,1); // Feil ble!

    // Sjekker om vanlig lege fikk lagt til narkotisk Legemiddel. Skal ikke ha blitt lagt til
    System.out.println( vanliglege.hentObjektMedResept() );

    // Ber spesialist skrive ut narkotisk middel. Forventer suksess
    superlege.skrivHvitResept(morfin,superlege,pas2,10);

    // Sjekker om spesialist fikk lagt til narkotisk Legemiddel. Skal ha blitt lagt til
    System.out.println( superlege.hentObjektMedResept() );

    // Sjekkerom resepter er lagt til på pasient 0
    System.out.println(pas0.toString());
    //System.out.println(pas0.hentObjektMedResept());

  }

}
