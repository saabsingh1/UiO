class TestLegemiddel{
        public static void main (String [] args){
        Narkotisk heroin = new Narkotisk ("Heroin", 500, 100, 3);
        Vanedannende morfin = new Vanedannende ("Morfin", 200, 50, 2);
        Vanlig paracet = new Vanlig ("Paracet", 100, 500);
        System.out.println(heroin);
        System.out.println("Tester forventede egenskaper... " + "\n" + "Navn: " + TestLegemiddel.TestLegemiddelNavn(heroin, "Heroin"));
        System.out.println("ID:  " + TestLegemiddel.TestLegemiddelID(heroin, 1));
        System.out.println("Pris: "+ TestLegemiddel.TestLegemiddelPris(heroin, 500));
        System.out.println("Virkestof: " + TestLegemiddel.TestLegemiddelVirkestoff(heroin, 100));
        System.out.println("Narkotisk Styrke: " + TestLegemiddel.TestNarkotiskStyrke(heroin, 3) + "\n");
        System.out.println(morfin);
        System.out.println("Tester forventede egenskaper... " + "\n"+ "Navn: " + TestLegemiddel.TestLegemiddelNavn(morfin, "Morfin"));
        System.out.println("ID:  " + TestLegemiddel.TestLegemiddelID(morfin, 2));
        System.out.println("Pris: " +  TestLegemiddel.TestLegemiddelPris(morfin, 200));
        System.out.println("Virkestoff: " + TestLegemiddel.TestLegemiddelVirkestoff(morfin, 50));
        System.out.println("Vanedannende Styrke: " + TestLegemiddel.TestVanedannendeStyrke(morfin, 2) + "\n");
        System.out.println(paracet);
        System.out.println("Tester forventede egenskaper... " + "\n"+ "Navn: " + TestLegemiddel.TestLegemiddelNavn(paracet, "Paracet"));
        System.out.println("ID:  " + TestLegemiddel.TestLegemiddelID(paracet, 3));
        System.out.println("Pris: " +  TestLegemiddel.TestLegemiddelPris(paracet, 100));
        System.out.println("Virkestoff: " + TestLegemiddel.TestLegemiddelVirkestoff(paracet, 500));
    
    }
        public static boolean TestLegemiddelNavn(Legemiddel legemiddel, String forventetLegemiddelNavn){
            return legemiddel.hentNavn() == forventetLegemiddelNavn;
        }

        public static boolean TestLegemiddelID(Legemiddel legemiddel, int forventetLegemiddelID){
            return legemiddel.hentID() == forventetLegemiddelID;
        }

        public static boolean TestLegemiddelPris(Legemiddel legemiddel, int forventetLegemiddelPris){
            return legemiddel.hentPris() == forventetLegemiddelPris;
        }

        public static boolean TestLegemiddelVirkestoff(Legemiddel legemiddel, double forventetLegemiddelVirkestoff){
            return legemiddel.hentVirkestoff() == forventetLegemiddelVirkestoff;
        }

        public static boolean TestNarkotiskStyrke (Narkotisk narkotisk, int forventetNarkotiskStyrke){
            return narkotisk.hentNarkotiskStyrke() == forventetNarkotiskStyrke;
        }

        public static boolean TestVanedannendeStyrke (Vanedannende vanedannende, int forventetVanedannendeStyrke){
            return vanedannende.hentVanedannendeStyrke() == forventetVanedannendeStyrke;
        }

}



    


