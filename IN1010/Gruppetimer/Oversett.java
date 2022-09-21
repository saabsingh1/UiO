class Person {
    private static int alder = 0;
    private String tekst; 
    public Person (int alder, String navn){
        alder = alder;
        tekst = navn;
    }

    public void SkrivUt(){
        System.out.println (navn + alder);
    }

    public void HaBursdag(){
        alder = alder ++;
    }
}


// litt uferdig, kanskje fordi vi ikke opretter et objekt 