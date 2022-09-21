import java.util.HashMap;


public class Fletter implements Runnable{

    Monitor monitor;
    int id;
    HashMap<String, SubSekvens> forsteHashMap;
    HashMap<String, SubSekvens> andreHashMap;

    public Fletter(Monitor monitor, int id){
        this.monitor = monitor;
        this.id = id;
    }
    
    @Override
    public void run() {
        // Sjekker om alle traadene kjoerer: System.out.println(id + " Kjoerer");
        while (monitor.pasienterMVirus.antallHashmaper() > 1){

            monitor.virusLaas.lock();
            forsteHashMap = monitor.pasienterMVirus.hentEn();
            andreHashMap = monitor.pasienterMVirus.hentEn();
            monitor.virusLaas.unlock();

            // Sjekker hvilke traader som fletter: System.out.println(id + " Fletter");
            HashMap<String,SubSekvens> paavistHash = HashMapBeholder.flettHashmaps(forsteHashMap, andreHashMap);

            monitor.virusLaas.lock();
            monitor.pasienterMVirus.leggTil(paavistHash);
            monitor.virusLaas.unlock();
        }

        
        while (monitor.pasienterUVirus.antallHashmaper() > 1){

            monitor.utenVirusLaas.lock();
            forsteHashMap = monitor.pasienterUVirus.hentEn();
            andreHashMap = monitor.pasienterUVirus.hentEn();
            monitor.utenVirusLaas.unlock();

            HashMap<String,SubSekvens> ikkePaavistHash = HashMapBeholder.flettHashmaps(forsteHashMap, andreHashMap);

            monitor.utenVirusLaas.lock();
            monitor.pasienterUVirus.leggTil(ikkePaavistHash);
            monitor.utenVirusLaas.unlock();
        }
        
    }
}
