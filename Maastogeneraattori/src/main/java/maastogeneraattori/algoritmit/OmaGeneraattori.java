
package maastogeneraattori.algoritmit;

/**
 * Oma generaattori luo maaston yhdistämällä useita Perlinkohinoita päällekkäin. 
 * Generaattorin tarkoitus on luoda mahdollisimman luonnollista maastoa.
 * 
 * @author lauimmon
 */
public class OmaGeneraattori {
    
    private Perlin p1;
    private Perlin p2;
    private Perlin p3;
    private Perlin p4;
    private Perlin p5;
    private Perlin p6;
    private double[][] maasto;
    private double maastonKeskikohtaX;
    private double maastonKeskikohtaY;
    private int resoluutio;
    private int nakyvatBlokit;
    
    /**
     * Tässä luotavassa maastossa luodaan ensin nxn kokoinen Perlinkohinataulukko,
     * jonka koko määrää koko maailman blokkien määrän. Useita muita Perlinkohinatauloita
     * luodaan tämän lisäksi, ja näitä yhdistämällä saadaan lopullinen maasto.
     * 
     * 
     * @param perlinKoko ensimmäisen Perlinkohinataulukon koko, eli maaston blokkien määrä per sivu
     * @param resoluutio pisteiden määrä yhden blokin sisällä per sivu
     * @param nakyvatBlokit eli miten monen blokin kokoinen alue kerralla tehdään
     */

    public OmaGeneraattori(int perlinKoko, int resoluutio, int nakyvatBlokit) {
        p1 = new Perlin(perlinKoko);
        p2 = new Perlin(perlinKoko * 2);
        p3 = new Perlin(perlinKoko * 4);
        p4 = new Perlin(perlinKoko * 8);
        p5 = new Perlin(perlinKoko * 16);
        
        maasto = new double[nakyvatBlokit * resoluutio][nakyvatBlokit * resoluutio];
        maastonKeskikohtaX = (double) perlinKoko / 2;
        maastonKeskikohtaY = (double) perlinKoko / 2;
        this.resoluutio = resoluutio;
        this.nakyvatBlokit = nakyvatBlokit;
    }
    
    /**
     * Luo maaston, jonka koko määräytyy konstruktorissa annettujen parametrien mukaan.
     * 
     * 
     * @return luotu maasto
     */
    
    public double[][] luoMaasto() {
        maasto = new double[maasto.length][maasto[0].length];
        int koko = maasto.length;
        
        double alkuX = maastonKeskikohtaX - (double) nakyvatBlokit / 2;
        
        double alkuY = maastonKeskikohtaY - (double) nakyvatBlokit / 2;
        
        double[][] maasto1 = p1.luoMaasto(koko, alkuX, alkuY, alkuX + (double) nakyvatBlokit, alkuY + (double) nakyvatBlokit);
        double[][] maasto2 = p2.luoMaasto(koko, alkuX * 2, alkuY * 2, (alkuX + (double) nakyvatBlokit) * 2, (alkuY + (double) nakyvatBlokit) * 2);
        double[][] maasto3 = p3.luoMaasto(koko, alkuX * 4, alkuY * 4, (alkuX + (double) nakyvatBlokit) * 4, (alkuY + (double) nakyvatBlokit) * 4);
        double[][] maasto4 = p4.luoMaasto(koko, alkuX * 8, alkuY * 8, (alkuX + (double) nakyvatBlokit) * 8, (alkuY + (double) nakyvatBlokit) * 8);
        double[][] maasto5 = p5.luoMaasto(koko, alkuX * 16, alkuY * 16, (alkuX + (double) nakyvatBlokit) * 16, (alkuY + (double) nakyvatBlokit) * 16);
        
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                maasto[i][j] = maasto1[i][j] + maasto2[i][j] / 2 + maasto3[i][j] / 8 + maasto4[i][j] / 16 + maasto5[i][j] / 32;
            }
        }
        
        return maasto;
    }
        
    /**
     * Liikuttaa maaston keskikohtaa parametreina annetun määrän.
     * 
     * @param dx miten monta blokkia tai miten suuri osuus blokista liikutaan x-suunnassa
     * @param dy miten monta blokkia tai miten suuri osuus blokista liikutaan y-suunnassa
     */
    
    public void liikuta(double dx, double dy) {
        maastonKeskikohtaX += dx * (double) nakyvatBlokit;
        if (maastonKeskikohtaX < (double) nakyvatBlokit / 2) {
            maastonKeskikohtaX = (double) nakyvatBlokit / 2;
        } else if (maastonKeskikohtaX > p1.getRandomVektorit().length - 1 - (double) nakyvatBlokit / 2) {
            maastonKeskikohtaX = p1.getRandomVektorit().length - 1 - (double) nakyvatBlokit / 2;
        }
        maastonKeskikohtaY += dy * (double) nakyvatBlokit;
        if (maastonKeskikohtaY < (double) nakyvatBlokit / 2) {
            maastonKeskikohtaY = (double) nakyvatBlokit / 2;
        } else if (maastonKeskikohtaY > p1.getRandomVektorit()[0].length - 1 - (double) nakyvatBlokit / 2) {
            maastonKeskikohtaY = p1.getRandomVektorit().length - 1 - (double) nakyvatBlokit / 2;
        }
        
    }

    /**
     * 
     * @return luotavan maaston koko blokeissa
     */
    
    public int getNakyvaMaasto() {
        return nakyvatBlokit;
    }
    
    /**
     * 
     * @return luotavan maaston keskikohdan x-koordinaatti Perlintaulukossa.
     */

    public double getMaastonKeskikohtaX() {
        return maastonKeskikohtaX;
    }

    /**
     * 
     * @return luotavan maaston keskikohdan y-koordinaatti Perlintaulukossa.
     */
    
    public double getMaastonKeskikohtaY() {
        return maastonKeskikohtaY;
    }
}
