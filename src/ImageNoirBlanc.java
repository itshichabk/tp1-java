import java.util.*;
import java.io.*;

public class ImageNoirBlanc extends Image
{
    private int maximum;

    public ImageNoirBlanc()
    {
        super();
    }

    public void setMaximum(int max)
    {
        maximum = max;
    }

    public void lire(File f) throws IOException
    {
        readDimensions(f);

        for (int i = 0; i < getHeight(); i++)
        {
            for (int j = 0; j < getWidth(); j++)
            {
                getMatrice().get(i)[j] = new PixelNoirBlanc();
            }
        }

        Scanner in = new Scanner(f);

        in.nextLine();
        in.nextLine();
        maximum = in.nextInt();

        for(int i = 0; i < getHeight(); i++)
        {
            for(int j = 0; j < getWidth(); j++)
            {
                getPixel(i, j).lire(in);
            }
        }
    }

    public void ecrire(File f) throws IOException
    {
        FileWriter fw = new FileWriter(f);

        fw.write("P2\n" + getWidth() + " " + getHeight() + "\n" + getMax() + "\n");

        int iCount = 0, jCount = 0;

        for (int i = 0; i < getHeight(); i++)
        {
            for (int j = 0; j < getWidth(); j++)
            {
                getPixel(i, j).ecrire(fw);
                fw.write(" ");
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
                getPixel(i, j).eclaircir_noircir(v, maximum);
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
            newMatrice.add(new PixelNoirBlanc[newWidth]);

            for (int j = 0; j < newWidth; j++)
            {
                PixelNoirBlanc pixelCourant = (PixelNoirBlanc)getPixel(2*i, 2*j);
                PixelNoirBlanc pixelDroite = new PixelNoirBlanc();
                PixelNoirBlanc pixelBas = new PixelNoirBlanc();
                PixelNoirBlanc pixelBasDroite = new PixelNoirBlanc();

                if(2*j - 1 < getWidth())
                {
                    pixelDroite.setLuminosite(((PixelNoirBlanc) getPixel(2*i, 2*j + 1)).getLuminosite());
                }

                if(2*i - 1 < getHeight())
                {
                    pixelBas.setLuminosite(((PixelNoirBlanc) getPixel(2*i + 1, 2*j)).getLuminosite());
                }

                if(2*j - 1 < getWidth() && 2*i - 1 < getHeight())
                {
                    pixelBasDroite.setLuminosite(((PixelNoirBlanc) getPixel(2*i + 1, 2*j + 1)).getLuminosite());
                }

                newMatrice.get(i)[j] = pixelCourant.reduire(pixelDroite, pixelBas, pixelBasDroite);
            }
        }

        setWidth(newWidth);
        setHeight(newHeight);
        updateMatrice(newMatrice);
    }
}