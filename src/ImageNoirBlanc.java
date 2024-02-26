import java.util.*;
import java.io.*;

public class ImageNoirBlanc extends Image
{
    public ImageNoirBlanc()
    {
        super();
    }

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

    public void verifyType(File f) throws FileNotFoundException, NoSuchElementException, WrongImageTypeException
    {
        try
        {
            Scanner in = new Scanner(f);
            String type = in.nextLine();

            if(Objects.equals(type, "P3"))
            {
                throw new WrongImageTypeException();
            }
            else if(!Objects.equals(type, "P2"))
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

    public void initializePixels()
    {
        for (int i = 0; i < getHeight(); i++)
        {
            for (int j = 0; j < getWidth(); j++)
            {
                getMatrice().get(i)[j] = new PixelNoirBlanc();
            }
        }
    }

    public void lireMatrice(File f) throws FileNotFoundException
    {
        try
        {
            Scanner in = new Scanner(f);

            in.nextLine();
            in.nextLine();
            setMax(in.nextInt());

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
    }

    public void ecrire(File f)
    {
        try
        {
            FileWriter fw = new FileWriter(f);

            fw.write("P2\n" + getWidth() + " " + getHeight() + "\n" + getMax() + "\n");

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
        catch(IOException e)
        {
            System.out.println("Erreur lors de l'écriture du fichier.");
        }
    }

    public void eclaircir_noircir(int v)
    {
        for (int i = 0; i < getHeight(); i++)
        {
            for (int j = 0; j < getWidth(); j++)
            {
                getPixel(i, j).eclaircir_noircir(v, getMax());
            }
        }
    }

    public void reduire()
    {
        ArrayList<Pixel[]> newMatrice = new ArrayList<>();
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