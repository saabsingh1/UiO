class Hovedprogram { //oppretter klassen hovedprogram 
    public static void main (String [] args){ //main metoden 
        /*Node node1 = new Node (64,1);
        Node node2 = new Node (1024,2);
        Dataklynge abel = new Dataklynge (12);
        


        for (int i = 0; i < 650; i++){
            //abel.settInnNode(node1);


        }
        
        for (int j = 0; j < 16; j++){
            //abel.settInnNode(node2);
        }

        */

        Dataklynge fraFil = new Dataklynge ("dataklynge.txt"); //dataklynge objekt som tar en tekstfil som parameter  











        System.out.println("32GB: " + fraFil.noderMedNokMinne(32)); //printout 
        System.out.println("64GB: " + fraFil.noderMedNokMinne(64));
        System.out.println("128GB: " + fraFil.noderMedNokMinne(128));

        System.out.println("Antall prosessorer: " + fraFil.antProsessorer());
        System.out.println("Antall racks: " + fraFil.antRacks());
        
    }
}