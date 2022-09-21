class Presepter extends HviteResepter{
    protected static int rabatt = 0;
    protected int pris = 0;  

    public Presepter(Legemiddel legemiddel, Lege utskrivendeLege, int pasientID){
        super(legemiddel, utskrivendeLege, pasientID, 3);
    }

    
    public String farge(){
        return "Hvit";
    }

    public int prisAaBetale(){
        pris = legemiddel.hentPris();
        pris = - 108;
        if (pris < 0){
            pris = 0;
        }
        return pris;
    }


    public String toString(){
        return "Legemiddel: " + legemiddel.hentNavn() + "\n" + utskrivendeLege + "\n" + "PasientID: " + pasientID + "\n" +  "Reit: " + reit + "\n" +  "ReseptID: " + reseptID + "\n" + "Pris: " + prisAaBetale() + "\n" + "Type resept: " + farge() + "\n";
    }
}