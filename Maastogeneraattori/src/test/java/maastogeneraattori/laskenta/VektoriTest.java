/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.laskenta;

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
public class VektoriTest {
    
    private Vektori v;
    
    public VektoriTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        v = new Vektori(2.0, -1.0, 3.5);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void summaPalauttaaVektorin() {
        assertFalse(v.lisaa(v) == null);
    }
    
    @Test
    public void vahennysPalauttaaVektorin() {
        assertFalse(v.vahenna(new Vektori(1.0, 0.0, -6.5)) == null);
    }
    
    @Test
    public void pistetuloOikein() {
        assertTrue(v.pistetulo(new Vektori(1.0, 0.0, -6.5)) == -20.75);
    }
    
    @Test
    public void pistetuloItsensaKanssaOnPituusToiseen() {
        assertTrue(v.pistetulo(v) == v.pituus()*v.pituus());
    }
    
    @Test
    public void ristituloPalauttaaVektorin() {
        assertFalse(v.ristitulo(new Vektori(1.0, 0.0, -6.5)) == null);
    }
    
    @Test
    public void pituusOikein() {
        assertTrue(new Vektori(1.0, 0, 0).pituus() == 1);
    }
    
    @Test
    public void normalisoinninTulosYksikkovektori() {
        Vektori y = v.normalisoi();
        assertTrue(y.pituus() == 1);
    }
    
    @Test
    public void skaalaauksenTulosOikeanPituinen() {
        Vektori v1 = v.skaalaa(4);
        assertTrue(v1.pituus()/v.pituus() == 4);
    }
}
