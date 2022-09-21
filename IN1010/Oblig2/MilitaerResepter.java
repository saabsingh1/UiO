class MilitaerResepter extends HviteResepter{
    protected int rabatt = 0;
    protected int pris = 0;

    public MilitaerResepter(Legemiddel legemiddel, Lege utskrivendeLege, int pasientID, int reit){
        super(legemiddel, utskrivendeLege, pasientID, reit);

    }

    
    public String farge(){
        return "Hvit";
    }

    public int prisAaBetale(){
        return 0;
    }
public String toString(){
        return "Legemiddel: " + legemiddel.hentNavn() + "\n" + utskrivendeLege + "\n" + "PasientID: " + pasientID + "\n" +  "Reit: " + reit + "\n" +  "ReseptID: " + reseptID + "\n" + "Pris: " + prisAaBetale() + "\n" + "Type resept: " + farge() + "\n";
    }
}