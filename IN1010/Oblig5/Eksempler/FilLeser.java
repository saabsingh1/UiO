import java.util.Scanner;
import java.io.*;
import java.util.HashMap;

public class FilLeser implements Runnable {
    String filnavn;
    Monitor monitor;
    boolean paavist;
    int subLengde;
    
    public FilLeser(String filnavn, Monitor monitor, boolean paavist, int subLengde) {
        this.filnavn = filnavn; 
        this.monitor = monitor;
        this.paavist = paavist;
        this.subLengde = subLengde;
    }

    @Override
    public void run() {
        String linje;
        String subStreng;
        
        try {
            Scanner scanner = new Scanner(new File(filnavn));

            HashMap<String,SubSekvens>  subHashMap = new HashMap <> ();	
    
            while(scanner.hasNextLine()) {
                linje = scanner.nextLine();
                if(linje.equals("amino_acid")) {
                    linje = scanner.nextLine();
                }
                    //linje = linje.trim();
                for (int ind = 0; ind + subLengde <= linje.length(); ind ++) {
                    subStreng = linje.substring(ind,ind+subLengde);
                    subHashMap.putIfAbsent(subStreng, new SubSekvens(subStreng));
                } 
            }
            scanner.close();
            monitor.leggTil(paavist, subHashMap);
            
        }
        
        catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
