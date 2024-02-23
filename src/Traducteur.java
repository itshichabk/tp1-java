import java.io.*;

public class Traducteur {

    private static boolean color;
    private static Image image;

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

    public static Couleur couleur_preponderante(Image i)
    {
        return ((ImageCouleur)i).couleur_preponderante();
    }

    public static boolean sontIdentiques(Image i1, Image i2)
    {
       return i1.sontIdentiques(i2);
    }

    public static void pivoter90(Image i1)
    {
        i1.pivoter90();
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
        File nf1 = new File("normal.pgm");
        File nf2 = new File("pivot.pgm");

        initialize(false);

        Image copieImage = new ImageNoirBlanc();

        lire(image, f);
        ecrire(image, nf1);
        image.pivoter90();
        //lire(copieImage, nf1);

        //System.out.println(sontIdentiques(image, copieImage));
        //System.out.println(couleur_preponderante(image));

        //eclaircir_noircir(image, 100);

        ecrire(image, nf2);
    }
}