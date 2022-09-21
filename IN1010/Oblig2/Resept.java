abstract class Resept{
    protected Lege utskrivendeLege; 
    protected Legemiddel legemiddel; 
    protected int pasientID; 
    protected int reit; 
    protected int reseptID = 1;
    protected static int teller = 1;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientID, int reit){
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasientID = pasientID;
        this.reit = reit;
        reseptID = teller++;
    }

    public boolean bruk(){
        if(reit>0){
            reit--;
            return true;
        }
        return false;
    }

    public int hentReseptID(){
        return reseptID;
    }
    public String hentLegemiddel(){
        return legemiddel.hentNavn();
    }
    public String hentLege(){
        return utskrivendeLege.hentNavn(); 
    }
    public int hentPasientID(){
        return pasientID;
    }

    public int hentReit(){
        return reit;
    }

    
    abstract public String farge();
    
    abstract public int prisAaBetale();

    public String toString(){
        return "Legemiddel: " + legemiddel.hentNavn() + "\n" + utskrivendeLege + "\n" + "PasientID: " + pasientID + "\n" +  "Reit: " + reit + "\n" +  "ReseptID: " + reseptID + "\n" + "Pris: " + prisAaBetale() + "\n";
    }
    
} 