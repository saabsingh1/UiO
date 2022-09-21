class Narkotisk extends Legemiddel{
    protected int styrke; 
    public Narkotisk(String navn, int pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }
    public int hentNarkotiskStyrke(){
        return styrke;
    }

    public String toString(){
        return "Navn: " + navn + "\n" + "ID: " + ID + "\n" +  "Pris: " + pris + "kr" + "\n" +  "Virkestoff: " + virkestoff + " mG" + "\n" + "Narkotisk Styrke: " + styrke + "\n";
        
    }
}