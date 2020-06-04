class BrukPant{

    public static void main(String[] args) {

        LitenPlastflaskePant plastLP = new LitenPlastflaskePant(1000, "plfLP001", "NO_LPl");

        System.out.println("Skriver ut info om liten plastflaske med pant");
        System.out.println("Volum: " + plastLP.hentVolum() + " cm3");
        System.out.println("ProdId: " + plastLP.hentProdId());
        System.out.println("Pant: " + plastLP.hentPant() + " oere");
        System.out.println("ReturId: " + plastLP.hentReturId() + "\n");


        LitenPlastflaskePantNedbrytbar plastLPN = new LitenPlastflaskePantNedbrytbar(1000, "plfLPN001", "NO_LPlN", 100);

        System.out.println("Skriver ut info om liten nedbrytbar plastflaske med pant");
        System.out.println("Volum: " + plastLPN.hentVolum() + " cm3");
        System.out.println("ProdId: " + plastLPN.hentProdId());
        System.out.println("Pant: " + plastLPN.hentPant() + " oere");
        System.out.println("ReturId: " + plastLPN.hentReturId());
        System.out.println("Nedbrytningstid: " + plastLPN.hentNedbrytningstid() + " aar\n");



        StorPappflaskePantNedbrytbar pappSPN = new StorPappflaskePantNedbrytbar(2000, "pafSPN001", 15, 250, "NO_SPaN", 10);

        System.out.println("Skriver ut info om liten nedbrytbar plastflaske med pant");
        System.out.println("Volum: " + pappSPN.hentVolum() + " cm3");
        System.out.println("ProdId: " + pappSPN.hentProdId());
        System.out.println("Vekt: " + pappSPN.hentVekt() + " g");
        System.out.println("Pant: " + pappSPN.hentPant() + " oere");
        System.out.println("ReturId: " + pappSPN.hentReturId());
        System.out.println("Nedbrytningstid: " + pappSPN.hentNedbrytningstid() + " aar\n");

    }

}



abstract class Emballasje{

    //KLASSEVARIABLER



    //INSTANSVARIABLER
    // volum i cm3
    protected double volum;

    // produksjonsidentifikator
    protected String prodId;



    //KONSTRUKTOER
    Emballasje(int volum, String prodId){
        System.out.println("konstruktoeren til klassen Emballasje utfoeres");
        this.volum = volum;
        this.prodId = prodId;
    }



    //METODER
    public double hentVolum(){
        return volum;
    }

    public String hentProdId(){
        return prodId;
    }
}



abstract class Plastemballasje extends Emballasje{

    //KLASSEVARIABLER



    //INSTANSVARIABLER



    //KONSTRUKTOER
    Plastemballasje(int volum, String prodId){
        super(volum, prodId);
        System.out.println("konstruktoeren til klassen Plastemballasje utfoeres");
    }


    //METODER

}



abstract class Pappemballasje extends Emballasje{

    //KLASSEVARIABLER



    //INSTANSVARIABLER
    // vekt i gram
    protected int vekt;


    //KONSTRUKTOER
    Pappemballasje(int volum, String prodId, int vekt){
        super(volum, prodId);
        System.out.println("konstruktoeren til klassen Pappemballasje utfoeres");
        this.vekt = vekt;
    }


    //METODER
    public int hentVekt(){
        return vekt;
    }
}



interface Pant{
    // panteverdi i oere
    public int hentPant();
    // returordning-identifikator
    public String hentReturId();
}



interface Nedbrytbar{
    // Nedbrytningstid i aar
    public int hentNedbrytningstid();
}



class LitenPlastflaskePant extends Plastemballasje implements Pant{

    //KLASSEVARIABLER



    //INSTANSVARIABLER
    // returordning-identifikator
    protected String returId;

    //KONSTRUKTOER
    LitenPlastflaskePant(int volum, String prodId, String returId){
        super(volum, prodId);
        System.out.println("konstruktoeren til klassen LitenPlastflaskePant utfoeres");
        this.returId = returId;
    }


    //METODER
    public int hentPant(){
        return 100;
    }

    public String hentReturId(){
        return returId;
    }
}



class LitenPlastflaskePantNedbrytbar extends LitenPlastflaskePant implements Nedbrytbar{

    //KLASSEVARIABLER



    //INSTANSVARIABLER
    // returordning-identifikator
    protected String returId;
    // Nedbrytningstid i aar
    protected int nedbrytningstid;


    //KONSTRUKTOER
    LitenPlastflaskePantNedbrytbar(int volum, String prodId, String returId, int nedbrytningstid){
        super(volum, prodId, returId);
        System.out.println("konstruktoeren til klassen LitenPlastflaskePantNedbrytbar utfoeres");
        this.nedbrytningstid = nedbrytningstid;
    }


    //METODER
    public int hentPant(){
        return 100;
    }

    public String hentReturId(){
        return returId;
    }

    public int hentNedbrytningstid(){
        return nedbrytningstid;
    }

}



class StorPappflaskePantNedbrytbar extends Pappemballasje implements Pant, Nedbrytbar{

    //KLASSEVARIABLER



    //INSTANSVARIABLER
    // panteverdi i oere
    protected int pant;
    // returordning-identifikator
    protected String returId;
    // Nedbrytningstid i aar
    protected int nedbrytningstid;


    //KONSTRUKTOER
    StorPappflaskePantNedbrytbar(int volum, String prodId, int vekt, int pant, String returId, int nedbrytningstid){
        super(volum, prodId, vekt);
        System.out.println("konstruktoeren til klassen LitenPlastflaskePant utfoeres");
        this.pant = pant;
        this.returId = returId;
        this.nedbrytningstid = nedbrytningstid;
    }


    //METODER
    public int hentPant(){
        return pant;
    }

    public String hentReturId(){
        return returId;
    }

    public int hentNedbrytningstid(){
        return nedbrytningstid;
    }

}
