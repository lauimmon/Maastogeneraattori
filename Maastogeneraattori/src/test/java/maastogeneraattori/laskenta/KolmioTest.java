/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.laskenta;

import java.awt.Color;
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
public class KolmioTest {
    
    Kolmio k;
    
    public KolmioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        k = new Kolmio(0, 0, 1, 0, 0, 1);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void nurkatOikein() {
        boolean oikein = false;
        if (k.getXKoordinaatti(0) == 0 && k.getYKoordinaatti(0) == 0 && 
                k.getXKoordinaatti(1) == 1 && k.getYKoordinaatti(1) == 0 && 
                k.getXKoordinaatti(2) == 0 && k.getYKoordinaatti(2) == 1) {
            oikein = true;
        }
        assertTrue(oikein);
    }
    
    @Test
    public void variNullKunVariaEiAsetettu() {
        assertEquals(k.getVari(), null);
    }
    
    @Test
    public void variOikeinKunVariAsetettu() {
        k.setVari(Color.gray);
        assertEquals(k.getVari(), Color.gray);
    }
}
