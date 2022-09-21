/*
Oppretter klassen monitor som tar i mot to beholdere i konstruktoeren
en for negative tester, og en for positive. 
importerer relevante tilegg. 
*/
import java.util.*;
import java.util.concurrent.locks.*;

public class MonitorFB {
    public HashBeholder pasienterPOS;
    public HashBeholder pasienterNEG;
    public Lock POSlaas;
    public Lock NEGlaas;
    private Condition flettingPOS;
    private Condition flettingNEG;

    public MonitorFB(HashBeholder pasienterPOS, HashBeholder pasienterNEG){
        this.pasienterPOS = pasienterPOS;
        this.pasienterNEG = pasienterNEG;

        // oppretter conditions og laaser for bruk videre 
        POSlaas = new ReentrantLock();
        NEGlaas = new ReentrantLock();
        flettingPOS = POSlaas.newCondition();
        flettingNEG = NEGlaas.newCondition();
    }

    /*
    metoden leggtil er det essensielle for denne klassen, den tar imot et hashmap og et resultat.
    dersom resultatet er true legges hashmappet til beholderen med positive resultater, og dersom det er false
    blir det lagt til i beholderen for negative resulatater. 
    */
    public void leggTil (boolean resultat, HashMap<String, SubSequence> hash){
        if (resultat == true) {
            POSlaas.lock(); // laaser slik at ikke flere traader proever aa faa tilgang samtidig. 
            try{
                pasienterPOS.leggTil(hash);
                if (pasienterPOS.antall() > 1){
                    flettingPOS.signalAll(); // dersom antallet hashmaps ikke er under 1, signaliserer vi.
                }
            }
            finally{
                POSlaas.unlock(); //laaser til slutt opp. 
            }
        }
        
        else if (resultat == false){
            NEGlaas.lock();
            try{
                pasienterNEG.leggTil(hash);
                if (pasienterNEG.antall() > 1){
                    flettingNEG.signalAll();
                }
            }
            finally{
                NEGlaas.unlock();
            }

        } 
    }
}