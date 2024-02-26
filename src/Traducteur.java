import java.io.*;

public class Traducteur
{
    public static void lire(Image i, File f) throws IOException, WrongImageTypeException
    {
        try
        {
            if(i instanceof ImageCouleur)
                ((ImageCouleur) i).lire(f);

            else if(i instanceof ImageNoirBlanc)
                ((ImageNoirBlanc) i).lire(f);

        }
        catch(WrongImageTypeException e)
        {
            throw new WrongImageTypeException("Type d'image à importer incompatible avec type prédéfini.");
        }

    }

    public static void ecrire(Image i, File f) throws IOException
    {
        if(i instanceof ImageCouleur)
            ((ImageCouleur) i).ecrire(f);

        else if(i instanceof ImageNoirBlanc)
            ((ImageNoirBlanc) i).ecrire(f);
    }

    public static void copier(Image main, Image toCopy) throws WrongImageTypeException
    {
        if((main instanceof ImageNoirBlanc && toCopy instanceof ImageNoirBlanc) ||
           (main instanceof ImageCouleur   && toCopy instanceof ImageCouleur))
        {
            main.copier(toCopy);
        }
        else
        {
            throw new WrongImageTypeException("Impossible de copier: images de types différents.");
        }
    }

    public static void extraire(Image i, int p1, int c1, int p2, int c2)
    {
        i.extraire(p1, c1, p2, c2);
    }

    public static void eclaircir_noircir(Image i, int v)
    {
        if(i instanceof ImageCouleur)
            ((ImageCouleur) i).eclaircir_noircir(v);

        else if(i instanceof ImageNoirBlanc)
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

    public static void reduire(Image i)
    {
        if(i instanceof ImageCouleur)
            ((ImageCouleur) i).reduire();

        else if(i instanceof ImageNoirBlanc)
            ((ImageNoirBlanc) i).reduire();
    }

    public static void main(String[] args)
    {
        Image image = new ImageCouleur();
        Image image2 = new ImageCouleur();

        try
        {
            File f = new File("test.ppm");

            lire(image, f);
            pivoter90(image);
            copier(image, image2);

            eclaircir_noircir(image, -100);
            extraire(image, 150, 150, 250, 250);

            System.out.println(couleur_preponderante(image));
            System.out.println(sontIdentiques(image, image2));

            pivoter90(image);
            reduire(image);

            File fOut1 = new File("testout1.ppm");
            File fOut2 = new File("testout2.ppm");

            ecrire(image2, fOut1);
            ecrire(image, fOut2);
        }

        catch(WrongImageTypeException | IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }

    }
}
