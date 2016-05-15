
package maastogeneraattori.tietorakenteet;

import maastogeneraattori.grafiikka.RGB;
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
public class SolmuTest {
    
    Solmu solmu;
    
    public SolmuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        solmu = new Solmu("testi", new RGB(0, 0, 1));
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void avainOikein() {
        assertEquals(solmu.getAvain(), "testi");
    }
    
    @Test
    public void arvoOikein() {
        assertEquals(solmu.getArvo(), new RGB(0, 0, 1));
    }
    
    @Test
    public void seuraavaSolmuAlussaNull() {
        assertEquals(solmu.getSeuraava(), null);
    }
    
    @Test
    public void asettaaSeuraavanOikein() {
        Solmu seuraava = new Solmu("seuraava", new RGB(1, 0, 0));
        solmu.asetaSeuraava(seuraava);
        assertEquals(solmu.getSeuraava(), seuraava);
    }
    
    @Test
    public void asettaaArvonOikein() {
        solmu.asetaArvo(new RGB(0, 1, 0));
        assertEquals(solmu.getArvo(), new RGB(0, 1, 0));
    }
}
