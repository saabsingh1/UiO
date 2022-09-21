class Vanlig extends Legemiddel{
    public Vanlig(String navn, int pris, double virkestoff){
        super (navn, pris, virkestoff);
    }

    public String toString(){
        return "Navn: " + navn + "\n" + "ID: " + ID + "\n" + "Pris: " + pris + "kr" + "\n" +  "Virkestoff: " + virkestoff + " mG" + "\n";
    }

}
