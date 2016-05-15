
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
public class LinkitettyListaTest {
    
    LinkitettyLista lista;
    
    public LinkitettyListaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        lista = new LinkitettyLista();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void aluksiTyhja() {
        assertTrue(lista.onkoTyhja());
    }

    @Test
    public void lisaaAvaimenOikein() {
        lista.lisaa("testi", 234.5);
        assertEquals(lista.getAlku().getAvain(), "testi");
    }
    
    @Test
    public void lisaaArvonOikein() {
        lista.lisaa("testi", 234.5);
        assertEquals(lista.getAlku().getArvo(), 234.5);
    }
    
    @Test
    public void lisaamisenJalkeenSeuraavaOikein() {
        lista.lisaa("testi", 234.5);
        lista.lisaa("testi2", 56.8);
        assertEquals(lista.getAlku().getAvain(), "testi2");
    }
    
    @Test
    public void poistoToimiiYhdella() {
        lista.lisaa("testi", 234.5);
        lista.poista("testi");
        assertEquals(lista.getAlku(), null);
    }
    
    @Test
    public void poistonJalkeenTyhja() {
        lista.lisaa("testi", 234.5);
        lista.poista("testi");
        assertTrue(lista.onkoTyhja());
    }
    
    @Test
    public void poistoToimiiUsealla() {
        lista.lisaa("testi", 234.5);
        lista.lisaa("testi2", 56.8);
        lista.lisaa("testi3", 56.7);
        lista.poista("testi");
        assertEquals(lista.getAlku().getAvain(), "testi3");
    }
    
    @Test
    public void poistoTyhjaanListaanToimii() {
        lista.poista("testi");
        assertEquals(lista.getAlku(), null);
    }
    
    @Test
    public void loytaaSolmun() {
        lista.lisaa("testi", 234.5);
        lista.lisaa("testi2", 56.8);
        lista.lisaa("testi3", 67);
        assertEquals(lista.etsi("testi"), 234.5);
    }
    
    @Test
    public void eiLoydaMitaanJosSolmuaEiOle() {
        lista.lisaa("testi", 234.5);
        lista.lisaa("testi2", 56.8);
        lista.lisaa("testi3", 67);
        assertEquals(lista.etsi("testi4"), null);
    }
}
