class Monitor extends Thread{

    private Rute denne;
    private Rute forrige;
    private String vei;

    Monitor(Rute forrige, Rute denne, String vei){
        this.forrige = forrige;
        this.vei = vei;
        this.denne = denne;
    }

    public void run(){

        // System.out.println("gaar til " + denne);
        denne.gaa(forrige, vei);


    }

}
