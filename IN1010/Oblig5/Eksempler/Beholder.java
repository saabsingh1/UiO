import java.util.HashMap;
import java.util.ArrayList;


class Beholder {
    // liste som inneholder alle hashmapene
    ArrayList<HashMap<String,SubSekvens> > hashmapene = new ArrayList<HashMap<String,SubSekvens> >(); 
    // antall hashmaps i listen hashmapene
    int  antall = 0;

    //returnerer antall hashmaper i listen
    public int antallHashmaper() {
        return antall;
    }

    //legger til ny hashmap
    public void leggTil(HashMap<String,SubSekvens> nyHashm) {
        antall++;
        hashmapene.add(nyHashm);      
    }

    //tar ut en hashmap fra listen
    public HashMap<String,SubSekvens> hentEn () {
        //antall hashmap i listen synker med en
        antall--;
        //returnerer listen over hashmaps minus det foerste hashmapet
        return hashmapene.remove(0);
    }

    // fletter sammen to og to hashmaps til en hashmap
    static HashMap<String,SubSekvens> flettHashmaps (HashMap<String,SubSekvens> hashMap1, HashMap<String,SubSekvens>  hashMap2){
        
        // variabel som lagrer en subsekvens, feks "ABA"
        SubSekvens hentetSubsekvens;  
        // ny hashmap der alle objekter fra de to forrige legges sammen til en uten gjentakelse av substrenger
        HashMap<String,SubSekvens> nyHashmap = new HashMap<String,SubSekvens> ();

        // gaar gjennom subsekvensobjektene i det foerste hashmapet
        // sletter subsekvenser fra hashmap 2 dersom de finnes i hashmap 1
        for(SubSekvens  sub1 : hashMap1.values()) {
            // lagrer subsekvensen vi fjerner fra hashmap2
            hentetSubsekvens = hashMap2.remove(sub1.nokkel());

            // hvis hentetsubsekvens er null
            if (hentetSubsekvens == null) {
                //legger til subesekvensen med string "aba" og sekevns objektet
                nyHashmap.put(sub1.nokkel(), sub1);
            }
            
            //hvis hentetSubsekevns ikke er null
            else {    
                //lagrer vi antall ganger subsekvensen dukker opp				   
                int ant = hentetSubsekvens.antall();
                // subsekvensen forekomst oeker med antall ganger
                sub1.leggTil(ant);
                // legger til den ny hashmapen 
                nyHashmap.put(sub1.nokkel(), sub1);  			
            }
        }   
        // Legger inn resten av hashmapene sdien de ikke finnes i nr 1:		    			    		
        for(SubSekvens  sub2:  hashMap2.values()) {
            // gaar gjennom objektene
            //legger til ny subsekvens
            nyHashmap.put(sub2.nokkel(),sub2);	
        }

        //returnerer den ny hashmapen
        return 	nyHashmap;					
    }

    public void testHele () {
        System.out.println(" Utskrift av antallet i hver hashmap i beholderen; ");
        for  (HashMap<String,SubSekvens> hashN: hashmapene)
            System.out.println(" Storrelsen av HashMap:  " + hashN.size() );
    } 

    
}
