class Lenkeliste <T> implements Liste<T>{ 
//oppretter klassen, den implementerer grensesnittet fra Liste
    class Node{
//oppretter Node klassen
        private Node neste;
        private T x;
//disse "beholderene" skal inneholde en referanse til et element T x, og til neste node i lista
        public Node(T x){
            this.x = x; 
        }

        public Node(T x, Node neste){
            this.neste = neste;
            this.x = x;
        }

    }
//variabel for start noden, den forste i lista 
    private Node start;
 
    public int stoerrelse(){
//metode som returnerer antall noder i lista
        int teller = 0; //teller variabel
        Node denneNoden = start;
        while(denneNoden != null){ //bruker while lokke som iritrer gjennom lista
            teller ++; // saa lenge noden ikke er tom, oker vi telleren
            denneNoden = denneNoden.neste; //henviser hele tiden til neste node
        }
        return teller; 
    }
    
    public void leggTil (T x){
//metode som legger til en node paa slutten av lista
        Node ny = new Node(x); //nye node objektet, som tar inn x fra parameteret i metoden
        if (start == null){ //dersom lista er tom legger vi til objektet straks
            start = ny;
        }
        else{ // hvis ikke iritrerer vi til vi kommer til slutten av lista
            Node denneNoden = start; //starter med forste node i lista
            while (denneNoden.neste != null){ //saa lenge neste node ikke er tom gaar vi videre til neste etter
                denneNoden = denneNoden.neste;
            }
            denneNoden.neste = ny; //legger til nytt node objekt i slutten 
        } 
    }

    public T fjern(){ 
        //metode somfjerner og returnerer innholdet i forste noden
        if (stoerrelse() == 0){ //dersom lista er tom gir vi ut en feilmelding
            throw new UgyldigListeIndeks(-1);
        }

        else{
            T svar = start.x; // variabel som holder paa innholdet i forste node
            start = start.neste; //forste noden blir naa det som tidligere laa etter forste i lista
            return svar; 
        }         
    } 

    public void sett(int pos, T x){ 
        //metode sombytter ut innholdet i en gitt node plassering med innhold fra parameter
        if (pos < 0 || pos >= stoerrelse() || stoerrelse() == 0){
            throw new UgyldigListeIndeks(pos);
        } // dersom lista er tom, eller vi gir en ugyldig plassering, feilmelding

        T data = x; //variabel som holder paa innhold fra parameter
        Node denneNoden = start;
        for(int i = 0; i < pos; i++){ //iritrerer til vi kommer til gitt plassering
            denneNoden = denneNoden.neste;
        }
        denneNoden.x = data; //bytter ut innholdet i noden med det fra parameter
    }

    public void leggTil(int pos, T x){
        //metode som legger til nytt element paa gitt plassering og skyver de andre i lista en plass bak
        if (pos < 0||pos > stoerrelse()){
            throw new UgyldigListeIndeks(pos);
        } //feilmld dersom lista er tom eller uglydig plassering
        else {
            Node denneNoden = start; //variabel for start noden
            Node ny = new Node(x); //variabel for ny node vi legger inn med innhold fra parameter til metoden

            if(pos==0){ //dersom plasseringen er indeks 0 i lista
                ny.neste = start; //lager vi plass til nye elementet
                start = ny; //dytter start en bak, og nye elementet blir naa i start posisjon
            }
            else { //hvis ikke iriterer vi til gitt posisjon og legger inn noden 
                for(int i = 0; i < pos-1; i++){
                    denneNoden = denneNoden.neste; //peker paa neste til vi kommer til gitt plass
                }
                ny.neste = denneNoden.neste; //lager plass til nye noden
                denneNoden.neste = ny; //setter noden inn
            }
        }     
    }

    public T fjern(int pos){
        //metode som fjerner element i gitt node posisjon og returnerer innholdet
        if (pos < 0 || pos >= stoerrelse() || stoerrelse() == 0){
            throw new UgyldigListeIndeks(pos);
        }
        Node denneNoden = start; 
        for(int i = 0; i < pos-1; i++){ //iriterer til vi kommer til riktig posisjon 
            denneNoden = denneNoden.neste;
        }
        if(pos == 0){ //dersom posisjonen er null 
            T svar = start.x;
            start = start.neste; //tar vi ut innholdet fra forste node, og noden etter er naa angitt som start nodne 
            return svar;
        }
        Node fjernetNode = denneNoden.neste; //holder paa noden vi vil fjerne 

        denneNoden.neste = fjernetNode.neste; //kobler sammen nodene som var foer og etter den vi vil fjerne 
        return fjernetNode.x;
    }

    public T hent(int pos){
        //metode som returnerer innholdet i gitt node posisjon 
        if (pos < 0 || pos >= stoerrelse() || stoerrelse() == 0){
            throw new UgyldigListeIndeks(pos);
        }

        Node denneNoden = start;
        for(int i = 0; i < pos; i++){ //iritrerer fram til riktig posisjon 
            denneNoden = denneNoden.neste;
        }

        if(pos == 0){ //dersom posisjon er 0, returnerer vi innholdet i noden 
            T svar = start.x;
            return svar;
        }
        
        T data = denneNoden.x; // returnerer innholdet i gitt node 
        return data;

    }
    
    public String toString(){ //toString metode som skriver ut innholdet i nodene som string
        Node denneNoden = start;
        String elementer = " ";
        while(denneNoden != null){ //saa lenge noden ikke er tom legger vi til innholdet 
            elementer += "|" + denneNoden.x;
            denneNoden = denneNoden.neste; //iritrerer gjennom lista
        }
        return "Lenkeliste:" + elementer + "|";
    }

    

}