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

        for(int i=  0; i < getHeight() - 1; i++)
        {
            for(int j = 0; j < getWidth() - 1; j++)
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

    }
}