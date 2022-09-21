import java.util.HashMap;
import java.util.ArrayList;

class HashBeholder {
    ArrayList<HashMap<String,SubSequence> > alleH = new ArrayList<HashMap<String,SubSequence> >(); 
    int  antall = 0;

    static HashMap<String,SubSequence> flett (HashMap<String,SubSequence> subSeqHash1, HashMap<String,SubSequence>  subSeqHash2){
        SubSequence hentaSub;  
        
        HashMap<String,SubSequence> subSeqHashNy = new HashMap<String,SubSequence> ();
        for(SubSequence  sub1:  subSeqHash1.values()) {
            hentaSub = subSeqHash2.remove(sub1.nokkel());
            if (hentaSub == null) {
                subSeqHashNy.put(sub1.nokkel(), sub1);
            }
            else {    				   
                int ant = hentaSub.antall();
                sub1.leggTil(ant);
                subSeqHashNy.put(sub1.nokkel(), sub1);  			
            }
        }   
        // Legger inn resten av subSeqHash2:		    			    		
        for(SubSequence  sub2:  subSeqHash2.values()) {
            subSeqHashNy.put(sub2.nokkel(),sub2);	
        }			 							
        return 	subSeqHashNy;					
    }

    public void testHele () {
        System.out.println(" Utskrift av antallet i hver hashmap i beholderen; ");
        for  (HashMap<String,SubSequence> hashN: alleH)
            System.out.println(" Storrelsen av HashMap:  " + hashN.size() );
    } 

    public int antall( ) {return antall;}

    public void leggTil(HashMap<String,SubSequence> hInn) {
        antall++;
        alleH.add(hInn);      
    }

    public HashMap<String,SubSequence> hentEn () {
        antall--;
        return alleH.remove(0);
    }
}
