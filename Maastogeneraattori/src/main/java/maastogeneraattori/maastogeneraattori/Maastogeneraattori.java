/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.maastogeneraattori;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author lauimmon
 */
public class Maastogeneraattori {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TimanttiNelio tn = new TimanttiNelio(100);
        
        tn.asetaArvot(55, 55, 0.00001);
        //tn.tulosta();
        tn.tallenna();
    }
    
}
