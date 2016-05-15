
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
public class DynaaminenTaulukkoTest {
    
    DynaaminenTaulukko t;
    
    public DynaaminenTaulukkoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        t = new DynaaminenTaulukko();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void luotuTaulukkoTyhja() {
        assertTrue(t.onTyhja());
    }
    
    @Test
    public void lisaamisenJalkeenKokoYksi() {
        t.lisaa(0);
        assertEquals(t.koko(), 1);
    }
    
    @Test
    public void monenLisaamisenJalkeenKokoOikein() {
        for (int i = 0; i < 10; i++) {
            t.lisaa(i);
        }
        assertEquals(t.koko(), 10);
    }
    
    @Test
    public void todellaMonenLisaamisenJalkeenKokoOikein() {
        for (int i = 0; i < 1000; i++) {
            t.lisaa(i);
        }
        assertEquals(t.koko(), 1000);
    }
    
    @Test
    public void voiLisataMontaSamaa() {
        for (int i = 0; i < 10; i++) {
            t.lisaa("testi");
        }
        assertEquals(t.koko(), 10);
    }
    
    @Test
    public void keskelleLisaaminenOnnistuu() {
        for (int i = 0; i < 10; i++) {
            t.lisaa(i);
        }
        t.lisaa(2, 100);
        assertEquals(t.hae(2), 100);
    }
    
    
    
    @Test
    public void poistamisenJalkeenTyhja() {
        t.lisaa(2);
        t.poista(2);
        assertEquals(t.koko(), 0);
    }
    
    @Test
    public void poistamistenJalkeenOikeaKoko() {
        t.lisaa(2);
        t.lisaa(6.7);
        t.lisaa(8.9);
        t.poista(2);
        t.poista(6.7);
        assertEquals(t.koko(), 1);
    }
    
    @Test
    public void olemattomanPoistostaEiTapahduMitaan() {
        t.lisaa(2);
        t.poista(4);
        assertEquals(t.koko(), 1);
    }
    
    @Test
    public void hakuOnnistuu() {
        t.lisaa(2);
        assertEquals(t.hae(0), 2);
    }
    
    @Test
    public void loytaaAlkion() {
        t.lisaa(2);
        t.lisaa(5);
        t.lisaa(8.9);
        assertTrue(t.sisaltaa(5));
    }
    
    @Test
    public void palauttaaOikeinKunAlkiotaEiOle() {
        t.lisaa(2);
        t.lisaa(5);
        t.lisaa(8.9);
        assertFalse(t.sisaltaa(8.99));
    }
    
    @Test
    public void palauttaaIndeksinOikein() {
        t.lisaa(2);
        t.lisaa(5);
        t.lisaa(8.9);
        t.poista(2);
        t.lisaa(1, 4.5);
        t.lisaa(2, 5.5);
        assertEquals(t.indeksi(8.9), 3);
    }
}
