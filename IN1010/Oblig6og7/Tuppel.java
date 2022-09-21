/*
Oppretter klassen tuppel. Den tar inn rad og kol som parameter og tar vare vare paa
disse verdiene. toStringen til klassen gir saa ut disse verdiene paa "tuppel - format". 
*/
public class Tuppel {
    public int rad; 
    public int kol; 

    public Tuppel(int rad, int kol){
        this.rad = rad;
        this.kol = kol;
    }

    public String toString(){
        return "<" + kol + "," + rad + ">"; 
    }
    
}
