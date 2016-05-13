
package maastogeneraattori.maastogeneraattori;

import maastogeneraattori.laskenta.Vektori;



/**
 *
 * @author lauimmon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
//        TimanttiNelio m = new TimanttiNelio(100);
//        
//        m.luoMaasto(0, 1, 1.0);
//        
//        Maailma maailma = new Maailma(m.getMaasto());
//
//        
//        JFrame frame = new JFrame("Maasto");
//        
//        frame.setPreferredSize(new Dimension(1000, 1000));
//        
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        
//        Grafiikka g = new Grafiikka(maailma);
//        frame.getContentPane().add(g);
//        
//        frame.pack();
//        frame.setVisible(true);
//        
//        g.piirra();
//        

       
        
        Kayttoliittyma kali = new Kayttoliittyma(400, 50, 2);
        kali.run();
        
        
        
    }
    
}
