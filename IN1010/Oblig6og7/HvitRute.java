import java.util.ArrayList; // importerer relevant pakker
/*
Oppretter klassen hvitrute som arver fra klassen Rute. Intanser av klassen representerer ruter 
i labyrinten som det er mulig aa gaa gjennom, altsaa veier i labyrinten. 
*/
class HvitRute extends Rute {

    public HvitRute (int rad, int kolonne, Labyrint l){
        super(rad, kolonne, l); //konstruktoeren arver fra superklassen. 

    }
    
    /*
    metoden returnerer et tegn, som representerer den hvite ruten i labyrinten,
    naar vi printer ut denne til terminalen. 
    */
    @Override
    protected char tilTegn(){
        return '.';
    }

    /*
    gaa metoden her skal soerge for at alle gyldige naboer kaller paa gaa metoden videre. 
    den tar imot arraylisten med utveiene, og 
    vi starter med aa sjekke at ruten vi er i ikke har blitt gatt allerede. i arraylisten legger vi saa til
    tuppel med rad og kolonne for ruten. vi oppretter en ny arralist med naboer, og kaller paa hjelpemetoden
    finnNaboer som legger til gyldige naboer i lista, og kaller deretter paa gaa metoden ved itere gjennom naboer arraylisten.
    naboene vil da forsoeke aa gaa fra tuppelet til ruta vi er paa, som er i arraylisten utvei.  
    */
    @Override
    protected void gaa(ArrayList <Tuppel> utveiene){
        if (harGaat == true){
            return; 
        }
        utvei = new ArrayList<>(utveiene); 
        utvei.add(new Tuppel(rad, kolonne)); 
        harGaat = true; 
        ArrayList <Rute> naboer = new ArrayList<>(); 
        finnNaboer(naboer);
        for (Rute nabo: naboer){
            nabo.gaa(utvei);
        }
        harGaat = false; 
    }

    /*
    dette er en hjelpemetode for aa kunne kalle paa gaa metoden for naboene til ruta. 
    vi oppretter en liste med instansene av naborutene til klassen. vi iriterer gjennom lista, og legger til 
    gyldige naboer i den andre lista naboer som vi har tatt imot som argument fra gaa metoden. 
    */
    protected void finnNaboer(ArrayList <Rute> naboer){
        Rute [] liste = new Rute[]{naboNord, naboOst, naboSor, naboVest};
        for (Rute naboene : liste){
            if (naboene != null ){
                naboer.add(naboene); 
            }
        } 
    }

    
}
