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
        k = new Kvaternio(0, 0, 0, 0);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void luoKaanteisen() {
        assertFalse(k.kaanteinen() == null);
    }
    
    @Test
    public void kertolaskuLuoKvaternion() {
        assertFalse(k.kerro(new Kvaternio(1.5, 2.6, 100.67, 1000000.0)) == null);
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
