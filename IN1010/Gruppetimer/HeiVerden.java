import java.util.Scanner;

class HeiVerden {
    public static void main (String[] args){
        Scanner inputfrabruker = new Scanner(System.in);
        System.out.println("Hei, hva heter du?");

        String navn = inputfrabruker.nextLine();  
        System.out.println("Hei " + navn + "! Velkommen til IN1010!");
    }
}

