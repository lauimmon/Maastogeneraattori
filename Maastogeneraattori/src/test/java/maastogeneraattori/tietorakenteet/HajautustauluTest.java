
package maastogeneraattori.tietorakenteet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lauimmon
 */
public class HajautustauluTest {
    
    Hajautustaulu ht;
    
    public HajautustauluTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ht = new Hajautustaulu();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void lisaysToimiiYhdella() {
        ht.lisaa("testi", 45);
        assertEquals(ht.hae("testi"), 45);
    }
    
    @Test
    public void lisaysToimiiMonella() {
        for (int i = 0; i < 100; i++) {
            ht.lisaa(i*987, i);
        }
        assertEquals(ht.hae(987), 1);
    }
    
    @Test
    public void eiLisaaJosParametriNull() {
        ht.lisaa(null, 45);
        assertFalse(ht.sisaltaaArvon(45));
    }
    
    @Test
    public void poistoToimii() {
        for (int i = 0; i < 100; i++) {
            ht.lisaa(i*987, i);
        }
        ht.poista(987);
        for (int i = 100; i < 120; i++) {
            ht.lisaa(i*987, i);
        }
        assertEquals(ht.hae(987), null);
    }
    
    @Test
    public void eiPoistaJosParametriNull() {
        ht.lisaa("testi", 45);
        ht.lisaa(null, 45);
        ht.poista(null);
        assertTrue(ht.sisaltaaArvon(45));
    }
}
