import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class tester {

    public static void main(String[] args) throws FileNotFoundException {
        String filnavn = "2.in";
        File fil = new File(filnavn);
        Labyrint l = new Labyrint(fil);
        System.out.println(l);
        l.finnUtveiFra(1,1);
        

    }
        
   
    
}
