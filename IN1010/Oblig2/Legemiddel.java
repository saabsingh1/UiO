abstract class Legemiddel {
    protected String navn;
    protected int pris;
    protected double virkestoff;
    protected int ID = 1;
    protected static int teller = 1;

    public Legemiddel (String navn, int pris, double virkestoff){
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        ID = teller++; 
    }

    public int hentID(){
        return ID;
    }
    
    public String hentNavn(){
        return navn;
    }

    public int hentPris(){
        return pris;
    }

    public double hentVirkestoff(){
        return virkestoff;
    }

    public int settNyPris(int nyPris){
        pris = nyPris;
        return pris;
    }

    public String toString(){
        return "Navn: " + navn + "\n" + "ID: " + ID + "\n" + "Pris: " + pris + "kr" + "\n" +  "Virkestoff: " + virkestoff + " mG" + "\n";
    }

}