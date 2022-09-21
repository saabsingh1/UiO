class HviteResepter extends Resept{
    public HviteResepter  (Legemiddel legemiddel, Lege utskrivendeLege, int pasientID, int reit){
        super (legemiddel, utskrivendeLege, pasientID, reit); 
    }

    public String farge(){
        return "Hvit";
    }

    public int prisAaBetale(){
        return legemiddel.hentPris();
 
   }
   public String toString(){
        return "Legemiddel: " + legemiddel.hentNavn() + "\n" + utskrivendeLege + "\n" + "PasientID: " + pasientID + "\n" +  "Reit: " + reit + "\n" +  "ReseptID: " + reseptID + "\n" + "Pris: " + prisAaBetale() + "\n" + "Type resept: " + farge() + "\n";
    }
}