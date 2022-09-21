/* Klassen subSekvens tar vare paa en string og en int antallForekomster 
av stringen. 
*/

class SubSekvens {
	private String strengSekvens;
	private int antallForekomster = 1;

	SubSekvens (String s){
		strengSekvens= s;
	}

	// returnerer strengen med tre bokstaver
	public String nokkel(){
		return strengSekvens;
	}

	// returnerer hvor mange ganger denne noeyaktige strengen forekommer
	public int antall () {
		return antallForekomster;
	}

	// oeker antall forekkomster med en
	public void leggTil ( ) {
		antallForekomster++;
	}

	// oeker antall forekomster med oensket antall
	public void leggTil(int ant){
		antallForekomster = antallForekomster + ant;
	}
}
