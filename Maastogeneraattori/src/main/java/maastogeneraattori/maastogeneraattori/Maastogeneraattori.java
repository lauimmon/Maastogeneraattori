/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.maastogeneraattori;

/**
 *
 * @author lauimmon
 */
public class Maastogeneraattori {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TimanttiNelio tn = new TimanttiNelio(5);
        
        tn.asetaArvot(50, 100, 10);
        tn.tulosta();
    }
    
}
