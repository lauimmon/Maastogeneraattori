/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.algoritmit;

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
public class OmaGeneraattoriTest {
    
    private OmaGeneraattori g;
    private int perlinKoko;
    private int resoluutio;
    private int nakyvatBlokit;
    
    public OmaGeneraattoriTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        perlinKoko = 10;
        resoluutio = 100;
        nakyvatBlokit = 3;
        g = new OmaGeneraattori(perlinKoko, resoluutio, nakyvatBlokit);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void luoOikeanKokoisenMaaston() {
        double[][] maasto = g.luoMaasto();
        
        assertEquals(maasto.length, resoluutio * nakyvatBlokit);
    }
    
    @Test
    public void alussaMaastonKeskikohtaKokoMaailmanKeskella() {
        boolean oikein = true;
        if (g.getMaastonKeskikohtaX() != perlinKoko / 2) {
            oikein = false;
        }
        if (g.getMaastonKeskikohtaY() != perlinKoko / 2) {
            oikein = false;
        }
        assertTrue(oikein);
    }
    
    @Test
    public void maastonKeskikohtaLiikkuuOikein() {
        g.liikuta(1.0, -0.5);
        
        boolean oikein = true;
        if (g.getMaastonKeskikohtaX() != perlinKoko / 2 + 1.0 * nakyvatBlokit) {
            oikein = false;
        }
        if (g.getMaastonKeskikohtaY() != perlinKoko / 2 - 0.5 * nakyvatBlokit) {
            oikein = false;
        }
        assertTrue(oikein);
    }
    
    @Test
    public void eiLiikuYliReunan() {
        g.liikuta(10.0, -0.5);
        
        boolean oikein = true;
        if (g.getMaastonKeskikohtaX() == perlinKoko / 2 + 10.0 * nakyvatBlokit) {
            oikein = false;
        }
        if (g.getMaastonKeskikohtaY() != perlinKoko / 2 - 0.5 * nakyvatBlokit) {
            oikein = false;
        }
        assertTrue(oikein);
    }

}
