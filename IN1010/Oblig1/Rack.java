class Rack{ //oppretter klassen rack 
    private int maxAntallNoder; // instansvariablene 
    private Node[] rackMedNoder; // array som skal innholde node objekter 

    public Rack (int noder){ //konstruktør som tar imot maksantall noder per rack som parameter 
        maxAntallNoder = noder;
        rackMedNoder = new Node [maxAntallNoder]; //fyller opp rack (array) av noder med maksantall noder 
    }
    public void settInn (Node node){ //metode som setter inn node objekter i array dersom det er ledig plass 
         for (int i = 0; i < rackMedNoder.length; i++) { //iritrerer gjennom arrayet 
            if (rackMedNoder[i] == null){ //dersom en indeks i arrayet ikke inneholder node objekt 
                rackMedNoder[i] = node; //sett inn node og returner
                return;
            }
        }     
    }
    public int getAntNoder(){ //metode som returnerer antallet noder i et rack
        return rackMedNoder.length;
    }

    public int antProsessorer(){ //metode som returnerer total antall prosessorer i racket 
        int antPros = 0; //lager en teller 
        for (Node node: rackMedNoder){ //iritrerer hvert node objekt i racket 
            if (node != null){ //dersom det ikke er tomt, øk telleren med antall prosessorer i node objektet 
                antPros += node.antProsessorer();
            }
            
        }
        return antPros; //returner antallet 
    }

    public int noderMedNokMinne(int paakrevdMinne){ // metode som returnerer noder som har ledig minne, tar imot parameter som er minnekravet man vil sjekke opp mot 
        int antNoder = 0; //teller 
        for (Node node: rackMedNoder){
            if (node != null && node.nokMinne(paakrevdMinne) == true){ //dersom noden ikke noden er tom og har paakrevd minne
                antNoder += 1; //øk teller 
            }
        }
        return antNoder;
    }

    public boolean rackSjekk (){ //hjelpemetode som returnerer en boolean etter hvorvidt racken har tom plass 
        return rackMedNoder[maxAntallNoder -1] != null;
    }
}