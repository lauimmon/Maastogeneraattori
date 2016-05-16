
package maastogeneraattori.grafiikka;

/**
 * Luokka helpottaa värien määrittelyä ja niillä laskemista
 *
 * @author lauimmon
 */
public class RGB {
    private double r, g, b;
    
    /**
     * Luo RGB-värin, annettuna arvot punaisen, vihreän ja sinisen osuuksille väliltä [0.0, 1.0]
     * 
     * @param r punaisen määrä
     * @param g vihreän määrä
     * @param b sinisen määrä
     */
    
    public RGB (double r, double g, double b) {
      this.r = r;
      this.g = g;
      this.b = b;
    }
    
    /**
     * Laskee kahden värin välisen summan.
     * 
     * @param rgb lisättä rgb
     * @return värien summa
     */
    
    public RGB summa(RGB rgb) {
      return new RGB (r + rgb.r, g + rgb.g, b + rgb.b);
    }
    
    /**
     * Laskee kahden värin välisen erotuksen.
     * 
     * @param rgb vähennettävä rgb-väri
     * @return värien erotus
     */
    
    public RGB erotus(RGB rgb) {
      return new RGB (r - rgb.r, g - rgb.g, b - rgb.b);
    }
    
    /**
     * Skaalaa värin parametrina annetulla kertoimella.
     * 
     * @param skaala kerroin
     * @return skaalattu väri
     */
    
    public RGB skaalaa(double skaala) {
      return new RGB (r * skaala, g * skaala, b * skaala);
    }
    
    private int toInt(double arvo) {
      if (arvo < 0.0) {
          return 0;
      } else if (arvo > 1.0) {
          return 255;
      } else return (int) (arvo * 255.0);
    }
    
    /**
     * Muuttaa värin Integer-arvoksi.
     * 
     * @return värin Integer-arvo
     */
    
    public int toRGB() {
      return (0xff << 24) | (toInt (r) << 16) |
        (toInt (g) << 8) | toInt (b);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.r) ^ (Double.doubleToLongBits(this.r) >>> 32));
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.g) ^ (Double.doubleToLongBits(this.g) >>> 32));
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.b) ^ (Double.doubleToLongBits(this.b) >>> 32));
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
        final RGB other = (RGB) obj;
        if (Double.doubleToLongBits(this.r) != Double.doubleToLongBits(other.r)) {
            return false;
        }
        if (Double.doubleToLongBits(this.g) != Double.doubleToLongBits(other.g)) {
            return false;
        }
        if (Double.doubleToLongBits(this.b) != Double.doubleToLongBits(other.b)) {
            return false;
        }
        return true;
    }
    
    
}
