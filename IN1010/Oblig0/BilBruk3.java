class BilBruk3 { //oppretter klassen 
    public static void main (String [] args){ // main metoden 
        Bil3 Audi = new Bil3(); // opretter objektene 
        Person Saab = new Person(Audi); // henviser til bil objektet 
        Saab.skrivUt(); //printer ut 
    }
}