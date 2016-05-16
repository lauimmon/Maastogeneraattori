
package maastogeneraattori.laskenta;

/**
 * Vektoriluokan avulla voi tehdä laskutoimituksia vektoreilla.
 * 
 * @author lauimmon
 */
public class Vektori {
    private double x, y, z;

    /**
     * Luo kolmiulotteisen vektorin, jonka pituus x-suunnassa on x, y-suunnassa y ja z-suunnassa z.
     * 
     * @param x x-arvo
     * @param y y-arvo
     * @param z z-arvo
     */
    
    public Vektori(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * Luo kaksiulotteisen vektorin, eli kolmiuloittesen vektorin, jonka z-suuntainen pituus on 0.
     * 
     * @param x x-arvo
     * @param y y-arvo
     */
    
    public Vektori(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }
    
    /**
     * Laskee kahden vektorin välisen yhteenlaskun.
     * 
     * @param v lisättävä vektori
     * @return vektori, joka on näiden vektorien summa
     */
    
    public Vektori lisaa(Vektori v) {
        return new Vektori(x + v.x, y + v.y, z + v.z);
    }
    
    /**
     * Laskee kahden vektorin erotuksen.
     * 
     * @param v vähennettävä vektori
     * @return vektori, joka on näiden vektorien erotus
     */
    
    public Vektori vahenna(Vektori v) {
        return new Vektori(x - v.x, y - v.y, z - v.z);
    }

    /**
     * Laskee kahden vektorin välisen pistetulon.
     * 
     * @param v pistetulon toinen osapuoli
     * @return tuloksena skalaari
     */
    
    public double pistetulo(Vektori v) {
        return x * v.x + y * v.y + z * v.z;
    }
    
    /**
     * Laskee kahden vektorin välisen ristitulon.
     * 
     * @param v ristitulon toinen osapuoli
     * @return vektori, joka on näiden vektoreiden ristitulon tulos
     */
    
    public Vektori ristitulo(Vektori v) {
        return new Vektori(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
    }
    
    /**
     * Laskee vektorin pituuden.
     * 
     * @return vektorin pituus
     */
    
    public double pituus(){
        return Math.sqrt(pistetulo(this));
    }
    
    /**
     * Luo tämän vektorin suuntaisen yksikkövektorin.
     * 
     * @return yksikkövektori
     */
    
    public Vektori normalisoi() {
        return skaalaa(1.0 / this.pituus());
    }

    /**
     * Luo tämän vektorin suuntaisen vektorin, jonka pituus on kerrottu parametrina annetulla luvulla
     * 
     * @param skaala pituuden kerroin
     * @return skaalattu vektori
     */
    
    public Vektori skaalaa(double skaala) {
        return new Vektori(x * skaala, y * skaala, z * skaala);
    }
    
    /**
     * @return vektorin x-komponetti
     */

    public double getX() {
        return x;
    }

    /**
     * @return vektorin y-komponetti
     */

    public double getY() {
        return y;
    }

    /**
     * @return vektorin z-komponetti
     */

    public double getZ() {
        return z;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vektori other = (Vektori) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        if (Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.z)) {
            return false;
        }
        return true;
    }

    
    
    
}
