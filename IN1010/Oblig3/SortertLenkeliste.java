class SortertLenkeliste <T extends Comparable<T>> extends Lenkeliste<T>{ 
//oppretter klassen, "T" arver fra comparable klassen, mens selve klassen arver fra Lenkeliste

    public void leggTil(T x){ //metode for aa legge til element i lista, som blir sortert etter strl

        for (int i = 0; i < stoerrelse(); i++){ //iritrerer gjennom lista
            if (hent(i).compareTo(x) > 0) { //dersom innholdet paa plass i er mindre enn x
                super.leggTil(i, x); //bruker super for aa legge til paa pos i
                return;
            }
        }
        super.leggTil(x); //ellers legg til paa slutten
    }


    public T fjern(){ //metode som fjerner storste element 
        return fjern(stoerrelse()-1); //bakerste elementet er storste, fjerner derfor her
    }


    public void sett(int pos, T x){ //metodene er utilgjengelige, gir feilmld
        throw new UnsupportedOperationException ("Dette er ikke mulig");
    }

    public void leggTil(int pos, T x){ //samme her
        throw new UnsupportedOperationException ("Dette er ikke mulig");
    }





}