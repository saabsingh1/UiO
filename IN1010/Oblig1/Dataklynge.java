import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
//importerer 

public class Dataklynge { //opretter dataklynge klassen 
    private int noderPerRack; //instansvariablene 
    private ArrayList<Rack> listeMedRack = new ArrayList<Rack>(); //arraylist med rack objekter 
    private String fil;
   

    public Dataklynge (String filnavn) { //kontruktøren som tar et filnavn som parameter 
        
        File fil = new File(filnavn); //gjør om filnavn til file objekt 
        Scanner inputFraFil = null; //setter scanner lik null

        
        try { //åpner filen 
            inputFraFil = new Scanner (fil);
            }
        catch (FileNotFoundException ikkeFunnet){ //skriver catch med printout 
            System.out.println("Fant ikke fil");
            }
        noderPerRack = Integer.parseInt(inputFraFil.nextLine()); //setter første linje i tekstfilen til å være lik antall noder per rack, gjør om til int 
        while (inputFraFil.hasNextLine()){ //while løkken kjører så lenge det er linjer i tektsfilen 
            String linje = inputFraFil.nextLine(); //variabelen holder på plass linje for linje fra tektsfilen 
            String [] biter = linje.split(" "); //splitter linja
            int noderFil = Integer.parseInt(biter[0]); //tilordner de forskjellige bitene av linja forskjellige variabler 
            int minneFil = Integer.parseInt(biter[1]);
            int antProsFil = Integer.parseInt(biter[2]);
            for (int i = 0; i < noderFil; i++){ //for løkke som går etter hvor mange noder filteksten bestemmer 
                settInnNode(new Node(minneFil, antProsFil)); //metoden settInnNode hvor vi oppretter nytt node objekt med parametere som får verdi fra tekstfilen etter variablene
                }
            }
        
    }

    public void settInnNode (Node node){ //metoden setter inn node objekter som den tar som parameter 
        for (Rack rack: listeMedRack){//for rack objektene i arraylisten 
            if (!rack.rackSjekk()){ //dersom racket har ledig plass 
            rack.settInn(node); // setter vi inn node objektet 
            return;        
            }
        }        
        Rack nyrack = new Rack (noderPerRack); // oppretter nytt rack objekt dersom rack objektene i arraylisten i løkka over ikke har plass 
        nyrack.settInn(node); //setter inn nodeobjektet i det nye rack objektet 
        listeMedRack.add(nyrack); //setter rack objektet i arraylista 
    }

    public int antProsessorer (){ //metode som teller total antall prosessorer for hvert rack objekt i arraylista 
        int antPros = 0;
        for (Rack rack: listeMedRack){
            antPros += rack.antProsessorer();
        }
        return antPros;
    }

    public int noderMedNokMinne(int paakrevdMinne){ // metode som teller total antall noder med nok minne i arraylista 
        int nokMinne = paakrevdMinne;
        int antNoder = 0;
        for (Rack rack: listeMedRack){
            antNoder += rack.noderMedNokMinne(nokMinne);
        }
        return antNoder;
    }

    public int antRacks(){ //returnerer antall racks i dataklyngen 
        return listeMedRack.size();
    }







}