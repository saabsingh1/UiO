class PasientTabell extends PasientAdm {
    Pasient [] senger; 
    int lengde; 
    int letPos = 0; 

    PasientTabell(int lengde){
        this.lengde = lengde; 
        senger = new Pasient[lengde]; 
    }

    void settInnPasient(Pasient p){
        int startPos = letPos; 
        while(true){
            if (senger[letPos] == null){
                senger[letPos] = p;
                p.sengNr = letPos;
                return;  
            }
            letPos ++; 
            if (letPos >= senger.length) letPos = 0; 
            if (letPos == startPos) return; 
        }
    }

    Pasient hentUt(Pasient p){
        senger[p.sengNr] = null; 
        p.sengNr = -1; 
        return p; 
    }

    Pasient hentUt(int i){
        for (int ix = 0; ix < senger.length; ix++)
            if (senger[ix]!= null && senger[ix].prioritet ==i) {
                Pasient px = senger[ix]; 
                px.sengNr = -1; 
                return px; 
            }
            return null; 
    }
}
