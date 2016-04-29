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
public class KvaternioTest {
    
    private Kvaternio k;
    
    public KvaternioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        k = new Kvaternio(-8.5, 4.0, 1.56, -100000.0);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void luoKaanteisen() {
        assertFalse(k.kaanteinen() == null);
    }
    
    @Test
    public void kaanteisenMerkitOikein() {
        boolean oikein = true;
        Kvaternio kaanteinen = k.kaanteinen();
        if ((k.getX() > 0 && kaanteinen.getX() > 0) || (k.getX() < 0 && kaanteinen.getX() < 0)) {
            oikein = false;
        }
        if ((k.getY() > 0 && kaanteinen.getY() > 0) || (k.getY() < 0 && kaanteinen.getY() < 0)) {
            oikein = false;
        }
        if ((k.getZ() > 0 && kaanteinen.getZ() > 0) || (k.getZ() < 0 && kaanteinen.getZ() < 0)) {
            oikein = false;
        }
        if ((k.getW() > 0 && kaanteinen.getW() < 0) || (k.getW() < 0 && kaanteinen.getW() > 0)) {
            oikein = false;
        }
        assertTrue(oikein);
    }
    
    @Test
    public void kaanteisenSkaalatOikein() {
        boolean oikein = true;
        Kvaternio kaanteinen = k.kaanteinen();
        double skaala = k.getX() * k.getX() + k.getY() * k.getY() + k.getZ() * k.getZ() + k.getW() * k.getW();
        if (kaanteinen.getX() != - k.getX() / skaala) {
            oikein = false;
        }
        if (kaanteinen.getY() != - k.getY() / skaala) {
            oikein = false;
        }
        if (kaanteinen.getZ() != - k.getZ() / skaala) {
            oikein = false;
        }
        if (kaanteinen.getW() != k.getW() / skaala) {
            oikein = false;
        }
        assertTrue(oikein);
    }
    
    @Test
    public void kertolaskuLuoKvaternion() {
        assertFalse(k.kerro(new Kvaternio(1.5, 2.6, 100.67, 1000000.0)) == null);
    }
    
    @Test
    public void kertolaskuAntaaOikeanTuloksen() {
        
    }
    
    @Test
    public void kaantoLuoVektorin() {
        assertFalse(k.kaanna(new Vektori(2.0, 3.0, 4.0)) == null);
    }
    
    @Test
    public void uusiKaantoLuoKvaternion() {
        assertFalse(Kvaternio.uusiKaanto(8.6, 0, 1, 100000) == null);
    }
}
