import java.util.*;
import java.io.*;

public class ImageCouleur extends Image {

    public ImageCouleur() {
        super();
    }

    public void lire(File f) throws IOException {
        readDimensions(f);

        for (int i = 0; i < getHeight(); i++)
        {
            for (int j = 0; j < getWidth(); j++)
            {
                getMatrice().get(i)[j] = new PixelCouleur();
            }
        }

        Scanner in = new Scanner(f);

        in.nextLine();
        in.nextLine();
        in.nextLine();

        for(int i=  0; i < getHeight(); i++)
        {
            for(int j = 0; j < getWidth(); j++)
            {
                ((PixelCouleur)getPixel(i, j)).lire(in);
            }
        }
    }

    public void ecrire(File f) throws IOException
    {
        FileWriter fw = new FileWriter(f);

        fw.write("P3\n" + getWidth() + " " + getHeight() + "\n" + getMax() + "\n");

        for (int i = 0; i < getHeight(); i++)
        {
            for (int j = 0; j < getWidth(); j++)
            {
                ((PixelCouleur)getPixel(i, j)).ecrire(fw);
            }
            fw.write("\n");
        }

        fw.flush();
        fw.close();
    }

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

    public void reduire()
    {
        ArrayList<Pixel[]> newMatrice = new ArrayList<Pixel[]>();
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