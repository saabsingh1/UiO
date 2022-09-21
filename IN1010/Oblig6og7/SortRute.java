import java.util.ArrayList;
/*
Oppretter klassen SortRute som arver fra superklassen Rute. klassen representerer ruter i labyrinten som 
det ikke skal vaere mulig aa gaa videre i, de er blindveier. 
*/
public class SortRute extends Rute {

    public SortRute (int rad, int kolonne, Labyrint l){
        super(rad, kolonne, l);

    }
    //metoden returner et tegn som representerer ruta i labyrinten naar vi printer denne ut til terminalen. 
    @Override
    protected char tilTegn(){
        return '#'; 
    }
    //her returnerer vi ettersom sortRute er en blindvei, hvor det ikke skal vaere mulig aa gaa videre fra. 
    @Override
    protected void gaa(ArrayList <Tuppel> utveiene){
        return; 
    }
}
