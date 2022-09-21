class Pasientprio extends PasientAdm{
    
    Pasient [] prioStart = new Pasient[Pasient.MAXPASPRIO + 1]; 
    Pasient [] prioSlutt = new Pasient[Pasient.MAXPASPRIO +1]; 

    void settInnPasient(Pasient p){
        int i = p.prioritet; 
        if(prioStart[i] == null){
            prioStart[i] = p; 
        }
        else{
            prioSlutt[i].neste = p; 
        }
        prioSlutt[i] = p; 
        p.neste = null; 
    }

    Pasient hentUt(Pasient p){
        int i = p.prioritet; 
        Pasient px = prioStart[i]; 
        if(px == null){
            return null; 
        }
        if(px == p){
            prioStart[i] = p.neste; 
            if (prioStart[i] == null)
                prioSlutt[i] = null; 
            return p;  
        }

        while(true) {
            Pasient px2 = px.neste; 
            if (px2 == null) return null; 
            if (px2 == p) {
                px.neste = px2.neste; 
                if (prioSlutt[i] == px2) prioSlutt[i] = px; 
                return px2; 
            }
            px = px2; 
        }
    }

    Pasient hentUt(int i){
        return hentUt(prioStart[i]); 
    }
}