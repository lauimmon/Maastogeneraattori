/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.maailma;

import maastogeneraattori.grafiikka.RGB;
import maastogeneraattori.maailma.Maailma;
import maastogeneraattori.laskenta.Kolmio;
import maastogeneraattori.laskenta.Vektori;
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
public class MaailmaTest {
    
    private Maailma m;
    
    public MaailmaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        m = new Maailma(new double[][] {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}, {7.0, 8.0, 9.0}});
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void asettaaMaaston() {
        assertFalse(m.getMaasto() == null);
    }
    
    @Test
    public void asettaaKartan() {
        assertFalse(m.getKartta() == null);
    }
    
    @Test
    public void asettaaKarttaanJokaisenVektorin() {
        boolean asetettu = true;
        for (Vektori[] rivi : m.getKartta()) {
            for (Vektori v : rivi) {
                if (v == null) {
                    asetettu = false;
                    break;
                }
            }
            if (!asetettu) {
                break;
            }
        }
        assertTrue(asetettu);
    }
    
    @Test
    public void asettaaKolmiomaaston() {
        assertFalse(m.getKolmiomaasto() == null);
    }
    
    @Test
    public void asettaaJokaisenKolmion() {
        boolean asetettu = true;
        for (Kolmio k : m.getKolmiomaasto()) {
            if (k == null) {
                asetettu = false;
                break;
            }
        }
        assertTrue(asetettu);
    }

    @Test
    public void asettaaVarit() {
        assertFalse(m.getVarit() == null);
    }
    
    @Test
    public void asettaaJokaiseenPisteeseenVarin() {
        boolean asetettu = true;
        for (RGB[] rivi : m.getVarit()) {
            for (RGB r : rivi) {
                if (r == null) {
                    asetettu = false;
                    break;
                }
            }
            if (!asetettu) {
                break;
            }
        }
        assertTrue(asetettu);
    }
    
    @Test
    public void asettaaVarjot() {
        assertFalse(m.getVarjot() == null);
    }
    
    @Test
    public void asettaaMin() {
        assertFalse(m.getMin() == Double.MAX_VALUE);
    }
    
    @Test
    public void asettaaMax() {
        assertFalse(m.getMax() == Double.MIN_VALUE);
    }
    
}
