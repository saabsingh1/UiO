import java.io.File; //importerer relevante pakker. 
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
Oppretter klassen labyrint som leser inn en fil og 
tilordner datastrukturen vi trenger videre. Klassen tilordner instansene rader og kolonner til den 
2-dimensjonale represantasjonen av labyrinten i arrayet rutenett. 
*/
public class Labyrint {
    public int rader;
    public int kolonner;  
    public Rute [][] rutenett; 
    public ArrayList <ArrayList<Tuppel>> tuppelListe; 

    public Labyrint(File fil) throws FileNotFoundException {

        Scanner leser = new Scanner(fil); //Oppretter scanner objekt for aa lese inn fil. 
        /*
        tilordner de forskjellige verdiene fra filen. 
        */
        String[] biter = leser.nextLine().split(" ");
        rader = Integer.parseInt(biter[0]); 
        kolonner = Integer.parseInt(biter[1]);
        rutenett = new Rute [rader][kolonner]; 
        int rad = 0; // en teller for radene i filen
        
        /*
        while loekken gaar saa lenge vi har en neste linje i filen. 
        vi iterer linje for linje, og dersom char verdien til et tegn i linja eller koordinatene representer
        henholdsvis hvit rute, sort rute eller en aapnign, saa oppretter vi rute objekter av disse. 
        */
        while(leser.hasNextLine()){
            String linje = leser.nextLine(); 
            for (int i = 0; i <linje.length(); i++){
                
                if (String.valueOf(linje.charAt(i)).equals(".")){
                    if(rad == 0 || i == 0 || rad == rader -1 || i == kolonner -1){
                        Aapning aapning = new Aapning(rad, i, this);
                        rutenett[rad][i] = aapning; 
                    }
                    else{
                        HvitRute hvit = new HvitRute(rad, i, this);
                        rutenett[rad][i] = hvit;
                        
                    }
                }
                else if (String.valueOf(linje.charAt(i)).equals("#")){
                    SortRute sort = new SortRute(rad, i, this);
                    rutenett[rad][i] = sort; 

                }
            }
            rad ++; 

        }
        leser.close();
        fordelNaboer();
    }
    /*
    metoden iterer gjennom rutene og kaller paa metoden bestemNaboer for hver rute. 
    */
    public void fordelNaboer(){
        for (int rad = 0; rad < rader;rad++){
            for (int kolonne = 0; kolonne < kolonner; kolonne++){
                bestemNaboer(rad, kolonne);
            }
        }
    }

    /*
    Metoden bestemmer hvorvidt en rute har naboer som er nord, vest, sor eller ost for seg ved if setninger. 
    den setter saa disse rutene til aa vaere nabo ved kalle paa settnabo metodene i rute klassen. 
    */
    public void bestemNaboer(int rad, int kolonne){
        if(rad > 0 ) {
            rutenett [rad][kolonne].settNaboNord(rutenett[rad-1][kolonne]);
        }

        if(kolonne > 0){
            rutenett[rad][kolonne].settNaboVest(rutenett[rad][kolonne-1]);
        }

        if (rad < rader -1){
            rutenett[rad][kolonne].settNaboSor(rutenett[rad+1][kolonne]);
        }

        if (kolonne < kolonner-1){
            rutenett[rad][kolonne].settNaboOst(rutenett[rad][kolonne+1]);
        }

    }

    /*
    metoden finner utveier fra en gitt rute/koordinat. Oppretter en arraylist med tupler, kaller saa 
    paa finnUtvei metoden i rute klassen, for den gitte ruten. returnerer deretter tuplene fra metoden.  
    */
    public ArrayList <ArrayList<Tuppel>>  finnUtveiFra(int kol, int rad){
        tuppelListe = new ArrayList<>();
        rutenett[rad][kol].finnUtvei();
        return tuppelListe; 
    }
    
    /*
    tostring metode for aa visuelt representere labyrinten i terminalen. 
    */
    public String toString(){
        String utskrift = "";
        
        for (int i = 0; i < rader; i++){
            for(int j = 0; j < kolonner; j++){
                utskrift += rutenett[i][j].tilTegn();
            }
            utskrift += "\n"; 
        }
        return utskrift; 
    }

    public Rute [][] hentRutenett(){
        return rutenett; 
    }
    
}
