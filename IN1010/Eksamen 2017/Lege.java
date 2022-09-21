class Lege extends Ansatt {
    int legeNr; 

    Lege(String ansattID, String navn, int legeNr){
        super(ansattID, navn);
        this.legeNr = legeNr;
    }
}
