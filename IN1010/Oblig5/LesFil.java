/*
Oppretter klassen lesfil, denne skal lese filene som inneholder immunreportoarer. 
Konstruktoeren tar imot en string filnavn, et objekt av klassen monitor og en boolean verdi resultat. 
Klassen implementerer runnable interface for bruk av traader. 
*/

import java.util.Scanner;
import java.io.*;
import java.util.HashMap;


public class LesFil implements Runnable {
    String filnavn;
    MonitorFB monitor;
    boolean resultat; 
    int subLengde = 3;

    public LesFil(String filnavn, MonitorFB monitor, boolean resultat) {

        this.filnavn = filnavn;
        this.monitor = monitor; 
        this.resultat = resultat;  

    }

    /*
    Run metoden inneholder et scanner objekt som leser inn filene med immunreportoarene.
    Mens while loekka gaar opprettes det substrenger objekter med informasjonen fra filene, 
    og legger disse substrengene inn i et hashmap. Til slutt blir hashmapene lagt til i monitor objektet. 
    */
    @Override
    public void run() {
        String linje;
        String subStreng;
        try {
            Scanner leser = new Scanner(new File("Data/" + filnavn));
            HashMap<String,SubSequence>  subSeqHash = new HashMap <> ();		
            System.out.println(" Virussjekker leser fil   " + filnavn );
            while(leser.hasNextLine()) {
                linje = leser.nextLine();  
                linje = linje.trim();
                if(linje.equals("amino_acid")) {
                    linje = leser.nextLine();
                }
                for (int ind = 0; ind + subLengde <= linje.length(); ind ++) {
                    subStreng = linje.substring(ind,ind+subLengde);
                    subSeqHash.putIfAbsent(subStreng,new SubSequence(subStreng));
                } 
            }
            leser.close();
            monitor.leggTil(resultat, subSeqHash);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}