public class Bil3 { //opretter klassen 
    private static int teller; // instansvariablene 
    private String bilnr;

    public Bil3 () { // konstruktøren oppretter bilnr 
        teller ++;
        String tallTilString = Integer.toString(teller); //konverterer til string 
        bilnr = tallTilString;
        
    }
    
    public String hentNummer() { // returnerer bilnr 
        return bilnr;
    }
    public void skrivUt() { // skriver ut 
        System.out.println("Jeg er en bil, og mitt registreringsnummer er: " + bilnr);  
    }
}
