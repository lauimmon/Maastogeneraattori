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
public class PerlinTest {
    
    private Perlin p;
    private int koko = 10;
    
    public PerlinTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        p = new Perlin(koko);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void asettaaSatunnaisvektorit() {
        boolean asetettu = true;
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                if (p.getRandomVektorit()[i][j] == null) {
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
    
}
