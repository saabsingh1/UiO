import java.util.*;
import java.util.concurrent.locks.*;

public class Monitor {
    public Beholder pasienterMVirus;
    public Beholder pasienterUVirus;
    public Lock virusLaas;
    public Lock utenVirusLaas;
    private Condition nokForFlettingVirus;
    private Condition nokForFlettingUtenVirus;

    public Monitor(Beholder pasienterMVirus, Beholder pasienterUVirus) {
        this.pasienterMVirus = pasienterMVirus;
        this.pasienterUVirus = pasienterUVirus;
        virusLaas = new ReentrantLock();
        utenVirusLaas = new ReentrantLock();
        nokForFlettingVirus = virusLaas.newCondition();
        nokForFlettingUtenVirus = utenVirusLaas.newCondition();
    }

    public void leggTil (boolean paavist, HashMap<String, SubSekvens> hm){
        if (paavist == true){
            virusLaas.lock();
            try{
               pasienterMVirus.leggTil(hm);
               if (pasienterMVirus.antallHashmaper() > 1){
                    nokForFlettingVirus.signalAll();
               }
            }
            finally{
                virusLaas.unlock();
            }
        }
        else if (paavist == false){
            utenVirusLaas.lock();
            try{
                pasienterUVirus.leggTil(hm);
                if (pasienterUVirus.antallHashmaper() > 1){
                     nokForFlettingUtenVirus.signalAll();
                }
             }
             finally{
                utenVirusLaas.unlock();
             }
        }
    }
    
   

}
