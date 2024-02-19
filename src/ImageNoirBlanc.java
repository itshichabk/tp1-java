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
                ((PixelNoirBlanc)getPixel(i, j)).lire(in);
            }
        }
    }

    public void ecrire(File f) throws IOException {
        FileWriter fw = new FileWriter(f);

        fw.write("P2\n" + getWidth() + " " + getHeight() + "\n" + getMax() + "\n");

        for (int i = 0; i < getHeight() - 1; i++) {
            for (int j = 0; j < getWidth() - 1; j++) {
                ((PixelNoirBlanc)getPixel(i, j)).ecrire(fw);
                fw.write(" ");
            }
            fw.write("\n");
        }
    }

    public void eclaircir_noircir(int v) {

    }

    public void reduire() {

    }
}