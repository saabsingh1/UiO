import java.awt.*;
import java.awt.event.*;
import javax.swing.*; //importerer relevante pakker 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList; 
/*
Oppretter hovedklassen GUI. Her legger jeg inn JFilechooser funksjonen, og 
oppretter selve hovedvinduet til programmet. Videre legger vi inn exit operasjonen, og 
kaller paa metoden i spillebrett som initsialiserer GUI delen i klassen. 
NB!: har tatt utgangspunkt i Dag sin "tripp trapp sko" fra forelesningen og jobbet videre derfra. 
*/
public class GUI{
    protected static Labyrint l; 
    File fil;         
    public static void main(String[] args) throws FileNotFoundException {
        JFileChooser velger = new JFileChooser();
        int resultat = velger.showOpenDialog(null);
        File fil = null; 
        if (resultat == JFileChooser.APPROVE_OPTION){
             fil = velger.getSelectedFile();
        }
        else{
            // Cancel
        }   
        try {
            l = new Labyrint(fil);
        } catch (FileNotFoundException e) {
            System.out.println("FEIL: Kunne ikke lese fil");
            System.exit(1);
        }
        System.out.println(l);
        JFrame vindu = new JFrame("- Maze Runner - ");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Spillbrett brett = new Spillbrett();
        brett.initGUI();
        vindu.add(brett);
        vindu.pack();
        vindu.setVisible(true);   
    }
    /*
    ruteknapp klassen representerer selve rutene i labyrinten. Den arver fra JButton og som paramenterer vil den ta imot int rad og 
    kolonne slik at vi faar plassert den deretter i GUI labyrinten. Den tar ogsaa imot en instans av Spill klassen 
    som er selve rutenettet vi faar opp i GUI. 
    */
    static class RuteKnapp extends JButton{
        Spill spillet;  
        int rad; 
        int kolonne; 

        RuteKnapp (Spill s, int rad, int kolonne){
            spillet = s; 
            this.rad = rad; 
            this.kolonne = kolonne; 
        }
        /*
        Skiller GUI koden i egne metoder, gjor det lettere aa holde styr paa denne delen. 
        dimensjonen paa rutene settes her, og dersom de er enten hvite eller sorte ruter settes 
        bakgrunnen til ruten deretter. 
        */
        void initGUI(){
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            setPreferredSize(new Dimension(25,25));
            setText(" ");
            if(l.rutenett[rad][kolonne] instanceof SortRute){
                this.setBackground(Color.BLACK);
                this.setOpaque(true);
            }
            else{
                this.setBackground(Color.WHITE);
                this.setOpaque(true);
            }
            /*
            rutevelger er klassen som skal fargelegge utveiene fra ruten vi velger i labyrinten. 
            klassen implementerer actionlistener, og i metoden actionperformed (som er det som skl skje naar
            vi velger en rute), legger vi inn rad og kolonne til ruten, og kaller paa finnutvei metoden fra labyrint. 
            vi faar en liste med tupler, og bruker hjelpemetoden settnybakgrunn for aa fargelegge rutene som er en del av 
            utveien. til slutt legger vi til action listner funksjonen for alle rutene. 
            */
            class Rutevelger implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent event){
                    l.finnUtveiFra(kolonne, rad);
                    if(! l.tuppelListe.isEmpty()){
                        ArrayList<Tuppel> utveier = l.tuppelListe.get(0);
                        for (Tuppel tuppel: utveier){
                            settNyBakgrunn(tuppel.rad, tuppel.kol);

                        }
                    } 
                }
            }
            addActionListener(new Rutevelger());
            
        }
        /*
        hjelpemetode for aa sette ny farge paa rutene som er en del av utveien. 
        */
        void settNyBakgrunn(int rad, int kolonner){
            spillet.ruteListe[rad][kolonner].setBackground(Color.GREEN);
            spillet.ruteListe[rad][kolonner].setOpaque(true);
        }
       
    }
    /*
    klassen Spillbrett arver fra Jpanel, og representerer panelet rutenett ligger paa. 
    klassen har en referanse til spill klassen, som er rutenettet vart. 
    */
    static class Spillbrett extends JPanel {
        JButton sluttknapp; 
        JLabel statustekst; 
        Spill spillet; 
    
        Spillbrett(){
            spillet = new Spill(this);
        }
        /*
        GUI koden initaliserer GUI metoden i spill klassen, og legger til rutenettet paa panelet. 
        vi legger inn to enkle JButton her, en for aa avslutte spillet, og en for aa restarte. 
        */
        void initGUI(){
            spillet.initGUI();
            add(spillet);
    
            statustekst = new JLabel("Velg en rute for aa se utvei");
            add(statustekst);
            /*
            klassen stoppbehandler er inspirert av tripp trapp tresko fra dag sin forelesning. 
            */
            sluttknapp = new JButton("Exit"); 
            class Stoppbehandler implements ActionListener{
                @Override
                public void actionPerformed (ActionEvent event){
                    System.exit(0);
                }
            }
            sluttknapp.addActionListener(new Stoppbehandler());
            add(sluttknapp);

            /*
            for klassen resetbehandler gaar vi igjennom lista med tupler vi brukte tidligere for aa
            markere hvilke utvei vi hadde gaat, for aa resette labyrinten igjen, og farge rutene hvite. 
            */
            JButton resetknapp = new JButton("Restart");
            class Resetbehandler implements ActionListener{
                @Override
                public void actionPerformed (ActionEvent event){
                    ArrayList<Tuppel> utveier = l.tuppelListe.get(0);
                    for (Tuppel tuppel: utveier){
                        spillet.settResetBakgrunn(tuppel.rad, tuppel.kol);
                    }
                }
            }
            resetknapp.addActionListener(new Resetbehandler());
            add(resetknapp);
        }
    }
    /*
    klassen spill arver ogsaa fra Jpanel, og er selve rutenettet labyrinten bestaar av. 
    den tar imot et spillbrett som parameter, og i konstruktoren oppretter vi ruteknappene vaare. 
    */
    static class Spill extends JPanel {
        Spillbrett brettet; 
        public RuteKnapp [][] ruteListe;  
        boolean ferdig = false; 
        int rad; 
        int kolonne; 
        /*
        vi opprettere to-dimensjonalt ruteknapp array, slik vi gjorde med ruter i labyrint, og iriterer
        gjennom rutene i labyrint, for aa saa lage ruteknapper av disse. 
        */
        Spill(Spillbrett b){
            brettet = b;
            ruteListe = new RuteKnapp[l.rader][l.kolonner];
            for (int i = 0; i < l.rader; i++){
                for(int j = 0; j< l.kolonner; j++){
                    ruteListe[i][j] = new RuteKnapp(this, i, j); 
                }
            }        
        }
        /*
        i GUI koden oppretter vi rutenettet ved GridLayout, og kaller paa GUI koden paa ruteknapp objektene
        vi legger dem saa til i rutenettet. 
        */
        void initGUI(){
            setLayout(new GridLayout(l.rader, l.kolonner));
            for (int i = 0; i < l.rader; i++){
                for(int j = 0; j< l.kolonner; j++){
                    ruteListe[i][j].initGUI();
                    this.add(ruteListe[i][j]);

                }
            }
        }
        /*
        hjelpemetode for aa tilbakestille labyrinten. 
        */
        void settResetBakgrunn(int rad, int kolonne){
            ruteListe[rad][kolonne].setBackground(Color.WHITE);
            ruteListe[rad][kolonne].setOpaque(true);        
        } 
    }
}








