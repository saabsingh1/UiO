import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static int subLengde = 3;
    public static void main (String [] args) throws IOException{

        Beholder personerMVirus = new Beholder();
        Beholder personerUVirus = new Beholder();
        Monitor monitor = new Monitor(personerMVirus, personerUVirus);
        ArrayList<Thread> leseFilTraader = new ArrayList<>();
        ArrayList<Thread> fletterTraader = new ArrayList<>();
        
        String linje;
        String filnavn;
        boolean paavist;
        int antallFlettere;

        if(args.length > 0){
            antallFlettere = Integer.parseInt(args[0]);
        }
        else{
            System.out.println("Programmet blir kjort feil");
            return;
        }
        try {
            Scanner scanner = new Scanner(new File("metadata.csv"));	
     
            System.out.println("Velkommen til HashBeholder test hovedprogram ");

            while(scanner.hasNextLine()) {
                linje = scanner.nextLine();
                if(linje.equals("repertoire_file,CMV")) {
                    linje = scanner.nextLine();
                }
                String [] info = linje.split(",");
                filnavn = info[0];
                System.out.println(" Virussjekker leser fil   " + filnavn );
                paavist = Boolean.parseBoolean(info[1].toLowerCase());
                Thread filleser = new Thread(new FilLeser(filnavn, monitor, paavist, subLengde));
                leseFilTraader.add(filleser);
                filleser.start();		
            }

            scanner.close();
            System.out.println("\n"+"Traadene gaar gjennom en for-loekke for bruk av join. Det tar litt tid.");
            for(Thread traad : leseFilTraader){
                try{
                    //System.out.println("Hvilken traad" + traad);
                    traad.join();
                }
                catch(InterruptedException i){
                    System.out.println("Traaden ble interrupted"); 
                }
            }
            
            //lager flettetraader
            for (int i = 0; i < antallFlettere; i++){
                Thread fletter = new Thread(new Fletter(monitor, i));
                fletterTraader.add(fletter);
                fletter.start();
            }
            for(Thread traad : fletterTraader){
                try{
                    //System.out.println("Hvilken flettetraad" + traad);
                    traad.join();
                }
                catch(InterruptedException i){
                    System.out.println("Traaden ble interrupted"); 
                }
            }

            System.out.println("\n" + "FERDIG med aa flette data ");

            /* Sjekker at begge beholderne kun inneholder en hashmap hver
            System.out.println(personerMVirus.antallHashmaper());
            System.out.println(personerUVirus.antallHashmaper());
            */

            System.out.println("\n" + " Dominante subsekvenser " );
            HashMap<String, SubSekvens> medVirus = personerMVirus.hentEn();
            HashMap<String, SubSekvens> utenVirus = personerUVirus.hentEn();

            for  (SubSekvens sub1 : medVirus.values()) {
                for (SubSekvens sub2 : utenVirus.values()){
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
            System.out.println("Fant ikke filen " + e); 
        }
    }
}
