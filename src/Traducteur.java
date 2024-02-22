import java.io.*;
import java.util.*;

public class Traducteur {

    private static boolean color;
    private static Image image;
    private static File file;

    public static void lire(Image i, File f) throws IOException
    {
        if(color)
            ((ImageCouleur) i).lire(f);
        else
            ((ImageNoirBlanc) i).lire(f);

    }

    public static void ecrire(Image i, File f) throws IOException
    {
        if(color)
            ((ImageCouleur) i).ecrire(f);
        else
            ((ImageNoirBlanc) i).ecrire(f);
    }

    public static void copier(Image i1, Image i2)
    {

    }

    public static void eclaircir_noircir(Image i, int v)
    {
        if(color)
            ((ImageCouleur) i).eclaircir_noircir(v);
        else
            ((ImageNoirBlanc) i).eclaircir_noircir(v);
    }

    public static void initialize(boolean isColor)
    {
        color = isColor;

        if(color)
            image = new ImageCouleur();
        else
            image = new ImageNoirBlanc();
    }

    public static void main(String args[]) throws IOException
    {
        File f = new File("test.pgm");
        File nf = new File("ecrire.txt");

        initialize(false);

        lire(image, f);

        eclaircir_noircir(image, 100);

        ecrire(image, nf);
    }
}