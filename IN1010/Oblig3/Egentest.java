class Egentest{
    public static void main (String[] args){
        Liste<Integer> liste = new Lenkeliste<Integer>();

        liste.leggTil(1);
        liste.leggTil(2);
        liste.leggTil(3);
        System.out.println("Storrelse: " + liste.stoerrelse());
        System.out.println("Fjerna: " + liste.fjern(0));
        System.out.println("Storrelse: " + liste.stoerrelse());
        liste.leggTil(0,50);
        //System.out.print("Hentet: " + liste.hent(0) + "\n");
        System.out.println("Storrelse: " + liste.stoerrelse());
        liste.leggTil(5);
        System.out.println(liste);

        
        
    }
}
