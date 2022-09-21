class TestResept{

    public static void main(String [] args){
    
        Narkotisk heroin = new Narkotisk ("Heroin", 500, 100, 3);
        Vanedannende morfin = new Vanedannende ("Morfin", 200, 50, 2);
        Vanlig paracet = new Vanlig ("Paracet", 100, 500);
        Spesialister utskrivendeLege = new Spesialister ("Wa Lat", "AB12");
        HviteResepter resept1 = new HviteResepter (morfin, utskrivendeLege, 1, 2);
        MilitaerResepter resept2 = new MilitaerResepter (morfin, utskrivendeLege, 2, 2);
        Presepter resept3 = new Presepter(paracet, utskrivendeLege, 3);
        BlaaResepter resept4 = new BlaaResepter(heroin, utskrivendeLege, 4, 3);
        
        System.out.println(resept1);
        System.out.println("Tester forventede egenskaper... " + "\n" + "Legemiddel: " + TestReseptLegemiddel(resept1, "Morfin"));
        System.out.println("Lege: " + TestReseptLege(resept1, "Wa Lat"));
        System.out.println("PasientID: " + TestReseptPasientID(resept1, 1));
        System.out.println("Bruker resept: " + resept1.bruk());
        System.out.println("Sjekker antall gjenvaerende reit: " + resept1.hentReit());
        System.out.println("ReseptID: " + TestReseptReseptID(resept1, 1));
        System.out.println("Pris: " + TestReseptPris(resept1, 200));
        System.out.println("Type: " + TestReseptFarge(resept1, "Hvit") + "\n");
        System.out.println(resept2); 
        System.out.println("Tester forventede egenskaper... " + "\n" + "Legemiddel: " + TestReseptLegemiddel(resept2, "Morfin"));
        System.out.println("Militaerresept, tester pris: " + TestReseptPris(resept2, 0) + "\n");
        System.out.println(resept3); 
        System.out.println("Tester forventede egenskaper... " + "\n" + "Legemiddel: " + TestReseptLegemiddel(resept3, "Paracet"));
        System.out.println("Presept, tester pris: " + TestReseptPris(resept3, 0) + "\n");
        System.out.println(resept4);
        System.out.println("Tester forventede egenskaper... " + "\n" + "Legemiddel: " + TestReseptLegemiddel(resept4, "Heroin"));
        System.out.println("Blaaresept, tester pris: " + TestReseptPris(resept4, 125) + "\n");
        





    }

        public static boolean TestReseptLegemiddel(Resept resept, String forventetLegemiddel){
            return resept.hentLegemiddel() == forventetLegemiddel; 
        }

        public static boolean TestReseptLege(Resept resept, String forventetLege){
            return resept.hentLege() == forventetLege;
        }

        public static boolean TestReseptPasientID(Resept resept, int forventetPasientID){
            return resept.hentPasientID() == forventetPasientID;
        }

        public static boolean TestReseptReseptID(Resept resept, int forventetReseptID){
            return resept.hentReseptID() == forventetReseptID;
        }
    
        public static boolean TestReseptReit(Resept resept, int forventetReit){
            return resept.hentReit() == forventetReit;
        }

        public static boolean TestReseptFarge(Resept resept, String forventetFarge){
            return resept.farge() == forventetFarge;
        }

        public static boolean TestReseptPris(Resept resept, int forventetPris){
            return resept.prisAaBetale() == forventetPris;
        }
    


}