class AdministratorOverlege extends Overlege {
    String ansvarsKode; 
    
    AdministratorOverlege(String ansattID, String navn, int legeNr, String spesType, String ansvarsKode){
        super(ansattID, navn, legeNr, spesType);
        this.ansvarsKode = ansvarsKode; 
    }

    String hentAnsvarsKode(){
        return ansvarsKode; 
    }
}
