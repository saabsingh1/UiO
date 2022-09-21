import java.util.ArrayList;
/*
oppretter klassen aapning som arver fra Hvitrute. instanser av klassen skal representere aapnigner
i labyrinten. 
*/
public class Aapning extends HvitRute {

    public Aapning(int rad, int kolonne, Labyrint l){
        super(rad, kolonne, l); //arver konstruktoer fra superklassen. 
    }

    @Override
    protected char tilTegn(){
        return '.'; 
    }

    /*
    i denne klassen er gaa metoden ferdig med aa gaa, og vi kaller ikke den videre paa noen naboer. 
    vi oppretter arraylist med utveier, og legger til tuppelet til ruta. vi legger til slutt lista med utvei
    i tuppelLista fra metoden i labyrint. 
    */
    @Override
    public void gaa(ArrayList <Tuppel> utveiene){
        
        utvei = new ArrayList<>(utveiene);
        utvei.add(new Tuppel(rad, kolonne)); 
        l.tuppelListe.add(utvei);  
        
    }
    
}
