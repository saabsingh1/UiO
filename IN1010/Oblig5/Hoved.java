/*
Oppretter hoved klassen, som inneholder main metoden vaar. 
Importerer ogsaa relevante tileggspakker.
*/
import java.util.Scanner;
import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;

public class Hoved  {
    static int subLengde = 3;
    
    public static void main (String [ ] args) throws IOException {	
        String metadataFilbane = "Data/metadata.csv";
        String linje;
        Boolean resultat;
        String filnavn;
        int antFlettere;

        /*
        Oppretter instanser av de andre klassene vi kommer til aa ha bruk for i hovedprogrammet.
        */

        HashBeholder pasienterPOS = new HashBeholder();
        HashBeholder pasienterNEG = new HashBeholder();
        MonitorFB monitor = new MonitorFB(pasienterPOS, pasienterNEG); 
        ArrayList<Thread> filTraadLeser = new ArrayList<>();
        ArrayList<Thread> traadFletter = new ArrayList<>();




        if(args.length > 0){
            antFlettere = Integer.parseInt(args[0]); // tar imot antall flettere vi skal ta bruk.
        }
        else{
            System.out.println("Husk aa oppgi antall flettere.");
            return;
        }
        
        /*
        Oppretter scanner objekt som leser gjennom metadata filen.
        tilordner bitene videre til forskjellige variabler, og setter i gang traaden. 
        */
        try{
            Scanner leser = new Scanner(new File(metadataFilbane));	

            System.out.println(" Velkommen til VirusAnalyse hovedprogram ");

            while(leser.hasNextLine()) {
                linje = leser.nextLine();   
                linje = linje.trim();
                if(linje.equals("repertoire_file,CMV")){
                    linje = leser.nextLine();
                }		

                String [] biter = linje.split(",");
                filnavn = biter[0];
                System.out.println(" Virussjekker leser fil   " + filnavn );
                resultat = Boolean.parseBoolean(biter[1].toLowerCase());
                Thread traadLesFil = new Thread(new LesFil(filnavn, monitor, resultat));
                filTraadLeser.add(traadLesFil);
                traadLesFil.start();		
            }

            leser.close();
            for(Thread thread : filTraadLeser){ //bruker join, slik at traadene ikke starter foer den forste har avsluttet. 
                try{
                    thread.join();
                }
                catch(InterruptedException i){
                    System.out.println("Traaden stanset"); 
                }
            }
            
    
            for (int i = 0; i < antFlettere; i++){
                Thread tFlett = new Thread(new HashFletter(monitor, i)); //oppretter flettetraader
                traadFletter.add(tFlett);
                tFlett.start(); //starter traaden
            }
            for(Thread thread : traadFletter){
                try{
                    thread.join();
                }
                catch(InterruptedException i){
                    System.out.println("Traaden stanset"); 
                }
            }

            System.out.println("\n" + "Dataene er flettet ");

            
            /*
            Gaar igjennom hashmapene med neg og pos resultat, 
            og ser etter de dominante subsekvensene som gaar igjen. 
            skriver saa ut resultatet av funnene. 
            */

            System.out.println("\n" + " Dominante subsekvenser " );
            HashMap<String, SubSequence> testPOS = pasienterPOS.hentEn();
            HashMap<String, SubSequence> testNEG = pasienterNEG.hentEn();

            for  (SubSequence sub1 : testPOS.values()) {
                for (SubSequence sub2 : testNEG.values()){
                    if (sub1.nokkel().equals(sub2.nokkel())){
                        if (sub1.antall() - sub2.antall() >= 5){
                            System.out.println( sub1.nokkel() + "  med antall:   " + sub1.antall() + " - " + sub2.antall() + " = " + (sub1.antall()-sub2.antall()));
                        }
                    }
                    else{
                        continue;
                    }
                }   
            }
        }
        catch (IOException e) {
            System.out.println("Filen ble ikke funnet " + e); 
        }
    }
}


