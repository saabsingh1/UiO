class IntegrasjonsTest {
    public static void main (String [] args){
        Narkotisk heroin = new Narkotisk ("Heroin", 500, 100, 3);
        Vanedannende morfin = new Vanedannende ("Morfin", 200, 50, 2);
        Vanlig paracet = new Vanlig ("Paracet", 100, 500);
        Lege lege = new Lege ("Magix Max");
        Spesialister utskrivendeLege = new Spesialister ("Wa Drogba", "AB12");
        HviteResepter resept1 = new HviteResepter (morfin, utskrivendeLege, 1, 2);
        MilitaerResepter resept2 = new MilitaerResepter (morfin, utskrivendeLege, 2, 2);
        Presepter resept3 = new Presepter(paracet, lege, 3);
        BlaaResepter resept4 = new BlaaResepter(heroin, utskrivendeLege, 4, 3);

        System.out.println(heroin);
        System.out.println(morfin);
        System.out.println(paracet);
        System.out.println(utskrivendeLege);
        System.out.println(resept1);
        System.out.println(resept2);
        System.out.println(resept3);
        System.out.println(resept4);
    }
}