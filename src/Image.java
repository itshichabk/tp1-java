/**
 * @author Antoine Auger, Hicham Abekiri, Mathis Leduc
 * @version 1.0
 *
 * Cette classe implémente une image.
 */

import java.util.*;
import java.io.*;

public class Image
{
    /**
     * Matrice qui contiendra l'ensemble des pixels de l'image
     */
    private ArrayList<Pixel[]> matrice;

    /**
     * Dimensions de l'image (largeur, hauteur)
     */
    private int width, height;

    private int max;

    public Image()
    {
        matrice = new ArrayList<>();
        width = 0;
        height = 0;
        max = 0;
    }

    /**
     * Importe les dimensions de l'image à partir d'un fichier, puis initialise la matrice de pixels avec les dimensions lues.
     * @param f Le fichier à importer
     * @throws FileNotFoundException si l'image n'a pas été trouvée
     */
    public void readDimensions(File f) throws FileNotFoundException
    {
        try
        {
            Scanner in = new Scanner(f);

            in.nextLine();
            width = in.nextInt();
            height = in.nextInt();
            max = in.nextInt();

            intializeMatrice();
        }
        catch(FileNotFoundException e)
        {
            throw new FileNotFoundException();
        }
    }

    /**
     * Remplace la matrice de pixels par une nouvelle
     * @param newMatrice la nouvelle matrice de pixels
     */
    public void updateMatrice(ArrayList<Pixel[]> newMatrice)
    {
        matrice = newMatrice;
    }

    public int getMax()
    {
        return max;
    }

    public void setMax(int max)
    {
        this.max = max;
    }

    /**
     * Copie les informations d'une image à une autre
     * @param toCopy l'image qui recevra les informations de la première image
     */
    public void copier(Image toCopy)
    {
        toCopy.setWidth(this.width);
        toCopy.setHeight(this.height);
        toCopy.setMax(this.max);
        toCopy.updateMatrice(this.matrice);
    }

    /**
     * Extraire un sous ensemble de l’image à partir de du point x1,y1 jusqu’à x2,y2
     * @param x1 Position horizontale du point 1
     * @param y1 Position verticale du point 1
     * @param x2 Position horizontale du point 2
     * @param y2 Position verticale du point 2
     */
    public void extraire(int x1, int y1, int x2, int y2)
    {
        if (x1 < x2 && y1 < y2)
        {
            ArrayList<Pixel[]> nm = new ArrayList<>();

            for (int i = 0; i < getHeight(); i++)
            {
                if (i >= y1 && i <= y2)
                {
                    nm.add(new Pixel[x2 - x1 + 1]);
                }

                for (int j = 0; j < getWidth(); j++)
                {
                    if ((i >= y1 && i <= y2) && (j >= x1 && j <= x2))
                    {
                        nm.get(i - y1)[j - x1] = this.matrice.get(i)[j];
                    }
                }
            }

            this.matrice = nm;
            this.height = nm.size();
            this.width = nm.get(0).length;

        } else {
            System.out.println("Les coordonnées données sont erronnées");
        }
    }

    /**
     * Compare l'image à une autre
     * @param img l'image avec laquelle la comparaison sera effectuée
     * @return si les deux images sont identiques
     */
    public boolean sontIdentiques(Image img)
    {
        if ((getWidth() != img.getWidth()) || (getHeight() != img.getHeight())
                || (getMax() != img.getMax()))
        {
            return false;
        }

        for (int i = 0; i < getHeight(); i++)
        {
            for (int j = 0; j < getWidth(); j++)
            {
                if (!Objects.equals(getPixel(i,j).toString(), img.getPixel(i,j).toString()))
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Permet de tourner de 90 degrés l’image.
     */
    public void pivoter90()
    {
        Image temp = new Image();
        temp.setHeight(getWidth());
        temp.setWidth(getHeight());
        temp.intializeMatrice();

        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                temp.getMatrice().get(j)[getHeight() - 1 - i] = matrice.get(i)[j];
            }
        }
        height = temp.getHeight();
        width = temp.getWidth();
        matrice = temp.getMatrice();
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public ArrayList<Pixel[]> getMatrice()
    {
        return matrice;
    }

    public Pixel getPixel(int y, int x)
    {
        return matrice.get(y)[x];
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Initialise la matrice en tenant compte des dimensions de l'image
     */
    public void intializeMatrice()
    {
        for (int i = 0; i < height; i++)
        {
            matrice.add(new Pixel[width]);
        }
    }


}
