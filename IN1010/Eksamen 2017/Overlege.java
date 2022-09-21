class Overlege extends Lege{
    String spesType;

    Overlege(String ansattID, String navn, int legeNr, String spesType){
        super(ansattID, navn, legeNr);
        this.spesType = spesType; 
    }
    
}
