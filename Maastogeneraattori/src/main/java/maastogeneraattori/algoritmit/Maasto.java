/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.algoritmit;

import maastogeneraattori.grafiikka.RGB;

/**
 *
 * @author Perus
 */
public interface Maasto {
    public double[][] getMaasto();
    public double suhteellinenKorkeus(double i, double j);
    public RGB getVari(double i, double j);
}
