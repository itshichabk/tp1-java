/**
 * @author Antoine Auger, Hicham Abekiri, Mathis Leduc
 * @version 1.0
 *
 * Cette classe implémente une image colorée.
 */

import java.util.*;
import java.io.*;

public class ImageCouleur extends Image {

    public ImageCouleur()
    {
        super();
    }

    /**
     * Importe une image noir et blanc à partir d'un fichier
     *
     * @param f Le fichier à lire
     * @throws FileNotFoundException si le fichier n'existe pas
     * @throws NoSuchElementException si le contenu du fichier est invalide
     * @throws WrongImageTypeException si le type d'image dans le fichier est incompatible
     */
    public void lire(File f) throws FileNotFoundException, NoSuchElementException, WrongImageTypeException
    {
        try
        {
            verifyType(f);
            readDimensions(f);
            initializePixels();
            lireMatrice(f);
        }
        catch(FileNotFoundException e)
        {
            throw new FileNotFoundException("Erreur: fichier inexistant.");
        }
        catch(NoSuchElementException e)
        {
            throw new NoSuchElementException("Erreur: fichier corrompu.");
        }
        catch(WrongImageTypeException e)
        {
            throw new WrongImageTypeException();
        }
    }

    /**
     * Vérifie si le fichier est valide et si le type d'image dans le fichier est compatible avec le type d'objet
     *
     * @param f Le fichier à lire
     * @throws FileNotFoundException si le fichier n'existe pas
     * @throws NoSuchElementException si le contenu du fichier est invalide
     * @throws WrongImageTypeException si le type d'image dans le fichier est incompatible
     */
    public void verifyType(File f) throws FileNotFoundException, NoSuchElementException, WrongImageTypeException
    {
        try
        {
            Scanner in = new Scanner(f);
            String type = in.nextLine();

            if(Objects.equals(type, "P2"))
            {
                throw new WrongImageTypeException();
            }
            else if(!Objects.equals(type, "P3"))
            {
                throw new NoSuchElementException();
            }
        }
        catch(FileNotFoundException e)
        {
            throw new FileNotFoundException();
        }
        catch(NoSuchElementException e)
        {
            throw new NoSuchElementException();
        }
    }

    /**
     * Initialise des pixels "vides" dans la matrice pour que leurs couleurs soient importées par la suite.
     */
    public void initializePixels()
    {
        for (int i = 0; i < getHeight(); i++)
        {
            for (int j = 0; j < getWidth(); j++)
            {
                getMatrice().get(i)[j] = new PixelCouleur();
            }
        }
    }

    /**
     * Importe le contenu de l'image depuis le fichier dans l'objet
     * @param f Le fichier à importer
     * @throws FileNotFoundException si le fichier n'existe pas
     * @throws NoSuchElementException si le contenu du fichier est invalide
     */
    public void lireMatrice(File f) throws FileNotFoundException, NoSuchElementException
    {
        try
        {
            Scanner in = new Scanner(f);

            in.nextLine();
            in.nextLine();
            in.nextLine();

            for(int i = 0; i < getHeight(); i++)
            {
                for(int j = 0; j < getWidth(); j++)
                {
                    getPixel(i, j).lire(in);
                }
            }
        }
        catch(FileNotFoundException e)
        {
            throw new FileNotFoundException();
        }
        catch(NoSuchElementException e)
        {
            throw new NoSuchElementException();
        }
    }

    /**
     * Enregistre l'image dans un fichier PPM
     * @param f le fichier destination
     */
    public void ecrire(File f)
    {
        try
        {
            FileWriter fw = new FileWriter(f);

            fw.write("P3\n" + getWidth() + " " + getHeight() + "\n" + getMax() + "\n");

            for (int i = 0; i < getHeight(); i++)
            {
                for (int j = 0; j < getWidth(); j++)
                {
                    getPixel(i, j).ecrire(fw);
                }
                fw.write("\n");
            }

            fw.flush();
            fw.close();
        }
        catch(IOException e)
        {
            System.out.println("Erreur lors de l'écriture du fichier.");
        }
    }

    /**
     * Éclaircir ou noircir tous les pixels de l'image
     * @param v Valeur de luminosité (positive ou négative)
     */
    public void eclaircir_noircir(int v)
    {
        for (int i = 0; i < getHeight(); i++)
        {
            for (int j = 0; j < getWidth(); j++)
            {
                getPixel(i, j).eclaircir_noircir(v, this.getMax());
            }
        }
    }

    /**
     * Réduit l'image en combinant chaque ensemble de 4 pixels en un seul.
     */
    public void reduire()
    {
        ArrayList<Pixel[]> newMatrice = new ArrayList<>();
        int newWidth = getWidth() / 2;
        int newHeight = getHeight() / 2;

        for (int i = 0; i < newHeight; i++)
        {
            newMatrice.add(new PixelCouleur[newWidth]);

            for (int j = 0; j < newWidth; j++)
            {
                PixelCouleur pixelCourant = (PixelCouleur)getPixel(2*i, 2*j);
                PixelCouleur pixelDroite = new PixelCouleur();
                PixelCouleur pixelBas = new PixelCouleur();
                PixelCouleur pixelBasDroite = new PixelCouleur();

                if(2*j - 1 < getWidth())
                {
                    pixelDroite.setCouleur(((PixelCouleur) getPixel(2*i, 2*j + 1)).getCouleur());
                }

                if(2*i - 1 < getHeight())
                {
                    pixelBas.setCouleur(((PixelCouleur) getPixel(2*i + 1, 2*j)).getCouleur());
                }

                if(2*j - 1 < getWidth() && 2*i - 1 < getHeight())
                {
                    pixelBasDroite.setCouleur(((PixelCouleur) getPixel(2*i + 1, 2*j + 1)).getCouleur());
                }

                newMatrice.get(i)[j] = pixelCourant.reduire(pixelDroite, pixelBas, pixelBasDroite);
            }
        }

        setWidth(newWidth);
        setHeight(newHeight);
        updateMatrice(newMatrice);
    }

    /**
     * Retourne la couleur prépondérante de l'image.
     * @return la couleur prépondérante de l'image.
     */
    public Couleur couleur_preponderante()
    {
        int max = ((PixelCouleur)getPixel(0, 0)).getCouleurMoyenne();
        int[] coordsMax = {0, 0};

        for (int i = 0; i < getHeight(); i++)
        {
            for (int j = 0; j < getWidth(); j++)
            {
                if(((PixelCouleur)getPixel(i, j)).getCouleurMoyenne() > max)
                {
                    max = ((PixelCouleur)getPixel(i, j)).getCouleurMoyenne();

                    coordsMax[0] = i;
                    coordsMax[1] = j;
                }
            }
        }

        return ((PixelCouleur)getPixel(coordsMax[0], coordsMax[1])).getCouleur();
    }
}