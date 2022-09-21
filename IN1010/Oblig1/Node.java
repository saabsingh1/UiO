class Node{ //oppretter node klassen 
    private int minnestorrelse; //instansvariablene 
    private int prosessorantall;
    private int minneKrav; 

    public Node (int minne, int prossesor) { //konstruktøren til klassen, tar imot 2 parametere 
        minnestorrelse = minne;
        prosessorantall = prossesor;
    }

    public int antProsessorer(){ //metode som returnerer antall prosessorer for noden 
        return prosessorantall;
    }

    public boolean nokMinne(int paakrevdMinne){ //metode som returnerer boolean om noden har nok minne i forhold til parameteret  
        minneKrav = paakrevdMinne;
        if(minnestorrelse >= minneKrav){
            return true; //returnerer true om det er likt eller mer 
        }
        return false; //ellers false 
    }
} 