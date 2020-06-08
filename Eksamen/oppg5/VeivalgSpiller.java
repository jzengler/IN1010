class VeivalgSpiller extends Spiller{
    // KLASSEVARIABLER



    // INSTANSVARIABLER



    // KONSTRUKTOER
    VeivalgSpiller(String navn, Sted her, Brukergrensesnitt grensesnitt){
        super(navn, her, grensesnitt);
    }


    // METODER
    public void nyttTrekk(){
        super.nyttTrekk();

        int indeks = grensesnitt.beOmKommando("\nHvilken vei vil du gaa?", ((VeivalgSted)her).hentVeier());


        grensesnitt.giStatus(hentNavn() + "Trasker " + ((VeivalgSted)her).hentVeier()[indeks] );


        // overskriver "her" som kjoeres i slutten av super.nyttTrekk()
        // kanskje ikke en veldig god maate aa gjoere det paa?
        her = ((VeivalgSted)her).gaaVidere(indeks);
    }
}
