/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.maastogeneraattori;

import maastogeneraattori.algoritmit.Perlinkohina;
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
        Perlinkohina pk = new Perlinkohina(50);
        
        double[][] maasto = pk.luoMaasto(50, 0, 0);
        
        System.out.print("[");
        for (int i = 0; i < maasto.length; i++) {
            for (int j = 0; j < maasto.length; j++) {
                System.out.print(maasto[i][j] + ", ");
            }
            System.out.println("");
        }
        System.out.println("]");
        
        maasto = pk.luoMaasto(50, 0, 0);
        
        System.out.print("[");
        for (int i = 0; i < maasto.length; i++) {
            for (int j = 0; j < maasto.length; j++) {
                System.out.print(maasto[i][j] + ", ");
            }
            System.out.println("");
        }
        System.out.println("]");
        
    }
    
}
