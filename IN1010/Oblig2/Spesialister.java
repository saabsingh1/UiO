class Spesialister extends Lege implements Godkjenningsfritak{
    protected String kontrollID;

    public Spesialister (String navn, String kontrollID){
        super (navn);
        this.kontrollID = kontrollID;

    }

    public String hentKontrollID(){
        return kontrollID;
    }

    public String toString(){
        return "Lege: " + navn + "\n" + "KontrollID: " + hentKontrollID();
    }
}