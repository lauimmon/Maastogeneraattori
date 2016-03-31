/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori;

import java.util.ArrayList;
import java.util.List;
import maastogeneraattori.maastogeneraattori.TimanttiNelio;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Perus
 */
public class TimanttiNelioTest {
    
    private TimanttiNelio tn;
    
    public TimanttiNelioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        tn = new TimanttiNelio(9);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void luoOikeanKokoisenRuudukonKunKoko9() {
        assertEquals(tn.getRuudukko().length, 9);
    }

    @Test
    public void luoOikeanKokoisenRuudukonKunKoko100() {
        tn = new TimanttiNelio(100);
        assertEquals(tn.getRuudukko().length, 129);
    }
    
    @Test
    public void luoOikeanKokoisenRuudukonKunKoko1() {
        tn = new TimanttiNelio(1);
        assertEquals(tn.getRuudukko().length, 2);
    }
    
    @Test
    public void asettaaNurkatOikein(){
        boolean vaarinAsetettu = false;
        for (int i = 0; i < 1000; i++) {
            tn.asetaArvot(0, 1, 1);
            double[][] ruudukko = tn.getRuudukko();
            if (ruudukko[0][0] < 0 || ruudukko[0][0] > 1) {
                vaarinAsetettu = true;
                break;
            }
            if (ruudukko[0][ruudukko.length - 1] < 0 || ruudukko[0][ruudukko.length - 1] > 1) {
                vaarinAsetettu = true;
                break;
            }
            if (ruudukko[ruudukko.length - 1][0] < 0 || ruudukko[ruudukko.length - 1][0] > 1) {
                vaarinAsetettu = true;
                break;
            }
            if (ruudukko[ruudukko.length - 1][ruudukko.length - 1] < 0 || ruudukko[ruudukko.length - 1][ruudukko.length - 1] > 1) {
                vaarinAsetettu = true;
                break;
            }
        }
        
        assertFalse(vaarinAsetettu);
    }
    
    @Test
    public void asettaaArvonJokaPisteelle() {
        boolean asetettu = true;
        
        for (int i = 0; i < 100; i++) {
            tn = new TimanttiNelio(i);
            tn.asetaArvot(10000, 10001, 0.1);
            double[][] r = tn.getRuudukko();

            for (double[] rivi : r) {
                for (double s : rivi) {
                    if (s == 0) {
                        asetettu = false;
                        break;
                    }
                }
            }
            if (!asetettu) {
                break;
            }
        }
        
        
        assertTrue(asetettu);
    }
}
