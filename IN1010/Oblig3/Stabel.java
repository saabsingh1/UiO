class Stabel <T> extends Lenkeliste<T>{
//stabel klassen arver fra lenkeliste
    public void leggPaa(T x){ //samme som i Lenkeliste 
        leggTil(x);
    }

    public T taAv(){ //metode som fjerner bakerste element 
        return fjern(stoerrelse()-1); //fjerner element paa siste plassen
    }
}