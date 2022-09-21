import java.util.ArrayList; //importere relevante tilegg. 

/*
Oppretter klassen rute. Klassen er abstrakt slik at det ikke direkte gaar an aa lage et objekt av denne, 
men heller subklassene som skal arve egeneskapene fra denne klassen. 
*/
abstract class Rute {
    // en haug av instansvariabler og referanser. 
    protected int rad = 0;
    protected int kolonne = 0;
    Labyrint l;
    //referanser til naboer, foreloepig satt til null. 
    protected Rute naboVest = null;
    protected Rute naboNord = null;
    protected Rute naboOst = null; 
    protected Rute naboSor = null; 
    protected ArrayList<Tuppel> utvei; 
    protected boolean harGaat = false; //boolean verdi som viser hvorvidt ruten har blitt "besoekt" av gaa metoden.
     
    
     
    //konstruktoeren tar imot rad og kolonne som parametere, samt en referanse til labyrinten.
    protected Rute (int rad, int kolonne, Labyrint l){
        this.rad = rad;
        this.kolonne = kolonne;
        this.l = l;
      
        
    }
    //abstrakt metode 
    abstract char tilTegn(); 
    
    /*
    sett metoder for eventuelle naboer, disse tar imot en rute som argument i parameteret.
    via filinnlesningen og bestemNabo metoden i Labyrint blir disse rutene satt som naboer til ruta vi er i.  
    */
    public void settNaboVest(Rute r){
        naboVest = r;
    }

    public void settNaboNord(Rute r){
        naboNord = r;
    }

    public void settNaboOst(Rute r){
        naboOst = r;
    }

    public void settNaboSor(Rute r){
        naboSor = r; 
    }
    
    //abstrakt metode 
    abstract void gaa(ArrayList <Tuppel> tupler);

    
    public void finnUtvei(){
        utvei = new ArrayList<>(); 
        gaa(utvei);
    }

    //hjelpemetode for aa vise koordinatene til veien i gaa metoden, ikke brukt videre. 
    public void koordinater(){
        System.out.println("(" + this.rad + "," + this.kolonne + ")"); 

    }

    public int hentRad(){
        return rad; 
    }

    public int hentKolonne(){
        return kolonne; 
    }
}