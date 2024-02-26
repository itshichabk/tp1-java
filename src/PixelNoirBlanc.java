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

    public int getLuminosite()
    {
        return luminosite;
    }

    public void setLuminosite(int luminosite)
    {
        this.luminosite = luminosite;
    }

    public void lire(Scanner s)
    {
        luminosite = s.nextInt();
    }

    public void ecrire(FileWriter fw) throws IOException
    {
        try
        {
            fw.write(toString());
        }
        catch(IOException e)
        {
            throw new IOException("Erreur lors de l'écriture du pixel.");
        }
    }

    @Override
    public String toString()
    {
        return Integer.toString(luminosite);
    }

    public PixelNoirBlanc reduire(PixelNoirBlanc droite, PixelNoirBlanc bas, PixelNoirBlanc basDroite)
    {
        int moyenneLum = (luminosite + droite.luminosite + bas.luminosite + basDroite.luminosite) / 4;

        return new PixelNoirBlanc(moyenneLum);
    }

}

