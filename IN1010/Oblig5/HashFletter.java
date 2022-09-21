/*
Oppretter klassen HashFletter som fletter sammen to og to hashmap til en. 
Klassen implementerer runnable interfacet til bruk med traader senere. 
kontruktoeren tar inn monitor objekt og en int. 
*/

import java.util.HashMap;
public class HashFletter implements Runnable{

    MonitorFB monitor;
    int id;
    HashMap<String, SubSequence> forsteHashMap;
    HashMap<String, SubSequence> andreHashMap;

    public HashFletter(MonitorFB monitor, int id){
        this.monitor = monitor;
        this.id = id;
    }
    
    /*
    Run metoden har to while loekker som gaar saa lenge antallet hashmaps i de to beholderne er over 1.
    Vi laaser foer, og aapner etter slik at ikke flere traader fletter av gangen, eller henter hashmap. 
    Vi henter to hashmap, for aa saa opprette en hashmap hvor vi lagrer den flettete hashmapen, foer vi saa legger
    denne tilbake i beholderen, og slik gaar det til vi sitter igjen med en hashmap.  
    */
    @Override
    public void run() {
        while (monitor.pasienterPOS.antall() > 1){

            monitor.POSlaas.lock();
            forsteHashMap = monitor.pasienterPOS.hentEn();
            andreHashMap = monitor.pasienterPOS.hentEn();
            monitor.POSlaas.unlock();

            HashMap<String,SubSequence> paavistHash = HashBeholder.flett(forsteHashMap, andreHashMap);

            monitor.POSlaas.lock();
            monitor.pasienterPOS.leggTil(paavistHash);
            monitor.POSlaas.unlock();
        }

        
        while (monitor.pasienterNEG.antall() > 1){

            monitor.NEGlaas.lock();
            forsteHashMap = monitor.pasienterNEG.hentEn();
            andreHashMap = monitor.pasienterNEG.hentEn();
            monitor.NEGlaas.unlock();

            HashMap<String,SubSequence> ikkePaavistHash = HashBeholder.flett(forsteHashMap, andreHashMap);

            monitor.NEGlaas.lock();
            monitor.pasienterNEG.leggTil(ikkePaavistHash);
            monitor.NEGlaas.unlock();
        }
        
    }
}
