class BlaaResepter extends Resept{
    protected double pris;
    public BlaaResepter(Legemiddel legemiddel, Lege utskrivendeLege, int pasientID, int reit){
        super(legemiddel, utskrivendeLege, pasientID, reit);
    }
    
    public String farge(){
        return "Blaa";
    }

    public int prisAaBetale(){
        pris = legemiddel.hentPris() * 0.25;
        return (int)Math.round(pris);
    }

    public String toString(){
        return "Legemiddel: " + legemiddel.hentNavn() + "\n" + utskrivendeLege + "\n" + "PasientID: " + pasientID + "\n" +  "Reit: " + reit + "\n" +  "ReseptID: " + reseptID + "\n" + "Pris: " + prisAaBetale() + "\n" + "Type resept: " + farge() + "\n";
    }
}