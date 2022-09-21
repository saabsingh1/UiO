/*
Klassen tar vare paa en string med sekvens, og et int antall forekomster av en sekvens. 
*/
class SubSequence {
	private String sequence;
	private int occurences = 1;
	SubSequence (String s) {sequence = s;}
	public void addOne ( ) {occurences++;} // metoden oeker forekomster med en.  
	public String nokkel() {return sequence;} // metoden returnerer sekvensen strengen.
	public int antall () {return occurences;} // metoden returnerer antall forekomster. 
	public void leggTil(int ant) {occurences = occurences + ant;} // metoden oeker forekomster med gitt antall. 
}
