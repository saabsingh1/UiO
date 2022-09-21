public class Bil2 {
    private static int teller;
    private String bilnr;

    public Bil2 () {
        teller ++;
        String tallTilString = Integer.toString(teller);
        bilnr = tallTilString;
        
    }
    
    public void skrivUt() {
        System.out.println("Jeg er en bil, og mitt registreringsnummer er: " + bilnr);  
    }
}
