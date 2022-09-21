import java.util.Scanner;
import java.io.*;
import java.util.HashMap;

public class VirusHoved  {
    static int subLengde = 3;
    static String metadataFilbane = "../filbane/til/metadata.txt";//Fyll inn!
    public static void main (String [ ] args) throws IOException {	
        String linje;
        // char type;
        HashBeholder bufSyk = new HashBeholder();
        // int antallFiler = 0;
        try{
            Scanner leser = 
                new Scanner(new File(metadataFilbane));			
            System.out.println(" Velkommen til HashBeholder test hovedprogram ");

            while(leser.hasNextLine()) {
                linje = leser.nextLine();   
                linje = linje.trim();
                lesFil(linje, bufSyk);		

            }
            leser.close();
        } catch (IOException e) {System.out.println(e.getMessage()); }
        System.out.println( );	
        bufSyk.testHele();

        while (bufSyk.antall() > 1) {
            HashMap<String,SubSequence> res = 
                HashBeholder.flett(bufSyk.hentEn(), bufSyk.hentEn());
            bufSyk.leggTil(res);
        }

        System.out.println(" FERDIG med aa samle data ");

        HashMap<String,SubSequence> hSyk = bufSyk.hentEn();		
        System.out.println( );	
        System.out.println(" Indikatorer paa syke pasienter  " );
        for  (SubSequence sS: hSyk.values()) {
            if(sS.antall() > 7)
                System.out.println( sS.nokkel() + "  med antall:   " + sS.antall());
        }	
        System.out.println(" FERDIG. Programmet terminerer. ");
    }

    static  void  lesFil(String filnavn, HashBeholder alleHashMaps) {
        String linje, subStreng;
        try {
            Scanner leser = 
                new Scanner(new File("tekstfiler/" + filnavn));

            HashMap<String,SubSequence>  subSeqHash = new HashMap <> ();		
            System.out.println(" Virussjekker leser fil   " + filnavn );


            while(leser.hasNextLine()) {
                linje = leser.nextLine();  
                linje = linje.trim();
                for (int ind = 0; ind + subLengde <= linje.length(); ind ++) {
                    subStreng = linje.substring(ind,ind+subLengde);
                    subSeqHash.putIfAbsent(subStreng,new SubSequence(subStreng));
                } 
            }
            leser.close();
            alleHashMaps.leggTil(subSeqHash);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }


}
