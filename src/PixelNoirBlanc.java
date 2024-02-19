import java.util.*;
import java.io.*;

public class PixelNoirBlanc implements Pixel {

    private int luminosite;

    public PixelNoirBlanc() {
        luminosite = 0;
    }

    public PixelNoirBlanc(int luminosite) {
        this.luminosite = luminosite;
    }

    public void eclaircir_noircir(int v, int max)
    {
        luminosite += v;

        if (luminosite < 0)
            luminosite = 0;

        else if (luminosite > max)
            luminosite = max;
    }

    public void lire(Scanner s) {
        luminosite = s.nextInt();
    }

    public void ecrire(FileWriter fw) throws IOException {
        fw.write(luminosite);
    }

    @Override
    public String toString() {
        return Integer.toString(luminosite);
    }

}

