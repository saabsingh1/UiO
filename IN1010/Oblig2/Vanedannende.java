class Vanedannende extends Legemiddel{
    protected int styrke;
    public Vanedannende(String navn, int pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    public int hentVanedannendeStyrke(){
        return styrke;
    }

    public String toString(){
        return "Navn: " + navn + "\n" + "ID: " + ID + "\n" + "Pris: " + pris + "kr" + "\n" +  "Virkestoff: " + virkestoff + " mG" + "\n" + "Vanedannende Styrke: " + styrke + "\n";
    }
}