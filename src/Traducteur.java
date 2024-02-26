/**
 * @author Antoine Auger, Hicham Abekiri, Mathis Leduc
 * @version 1.0
 *
 * Cette classe implémente le traducteur et l'ensemble des méthodes de l'utilisateur final.
 */

import java.io.*;

public class Traducteur
{
    /**
     * Importe une image à partir d'un fichier
     * @param i L'image à initialiser
     * @param f Le fichier à importer
     * @throws IOException si une erreur de lecture a lieu (par exemple si le fichier n'existe pas)
     * @throws WrongImageTypeException si l'image passée en paramètre a un type différent de celui du fichier.
     */
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

    /**
     * Enregistre une image dans un fichier
     * @param i l'image à enregistrer
     * @param f le fichier destination
     * @throws IOException si une erreur d'écriture a lieu
     */
    public static void ecrire(Image i, File f) throws IOException
    {
        if(i instanceof ImageCouleur)
            ((ImageCouleur) i).ecrire(f);

        else if(i instanceof ImageNoirBlanc)
            ((ImageNoirBlanc) i).ecrire(f);
    }

    /**
     * Copie les informations d'une image à une autre
     * @param main l'image source
     * @param toCopy l'image destination
     * @throws WrongImageTypeException si les deux images sont de types différents
     */
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

    /**
     * Extraire un sous ensemble d'une image à partir de du point x1,y1 jusqu’à x2,y2
     * @param i l'image à extraire
     * @param p1 Position horizontale du point 1
     * @param c1 Position verticale du point 1
     * @param p2 Position horizontale du point 2
     * @param c2 Position verticale du point 2
     */
    public static void extraire(Image i, int p1, int c1, int p2, int c2)
    {
        i.extraire(p1, c1, p2, c2);
    }

    /**
     * Éclaircir ou noircir tous les pixels d'une image
     * @param i L'image à manipuler
     * @param v Valeur de luminosité (positive ou négative)
     */
    public static void eclaircir_noircir(Image i, int v)
    {
        if(i instanceof ImageCouleur)
            ((ImageCouleur) i).eclaircir_noircir(v);

        else if(i instanceof ImageNoirBlanc)
            ((ImageNoirBlanc) i).eclaircir_noircir(v);
    }

    /**
     * Retourne la couleur prépondérante d'une image colorée
     * @param i l'image dont la couleur prépondérante sera calculée (doit être colorée)
     * @return la couleur prépondérante de l'image
     * @throws WrongImageTypeException si une image non colorée a été envoyée
     */
    public static Couleur couleur_preponderante(Image i) throws WrongImageTypeException
    {
        if(i instanceof ImageCouleur)
            return ((ImageCouleur)i).couleur_preponderante();

        else
            throw new WrongImageTypeException("couleur_preponderante ne peut être utilisée que sur une image colorée.");
    }

    /**
     * Permet de déterminer si deux images sont identiques
     * @param i1 La première image
     * @param i2 La deuxième image
     * @return si les deux images sont identiques ou non
     */
    public static boolean sontIdentiques(Image i1, Image i2)
    {
       return i1.sontIdentiques(i2);
    }

    /**
     * Permet de tourner de 90 degrés l’image.
     * @param i1 L'image à pivoter
     */
    public static void pivoter90(Image i1)
    {
        i1.pivoter90();
    }

    /**
     * Réduit l'image en combinant chaque ensemble de 4 pixels en un seul.
     * @param i L'image à réduire
     */
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
