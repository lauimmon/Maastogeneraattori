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
public class VektoriTesti {
    
    private Vektori v;
    
    public VektoriTesti() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        v = new Vektori(2.0, 1.0, 3.5);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void kertolaskunTulosOikein() {
        Vektori v1 = v.kerro(4);
        assertTrue(v1.pituus()/v.pituus() == 4);
    }
}
