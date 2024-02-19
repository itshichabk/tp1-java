import java.util.*;
import java.io.*;

public class Image {
    private ArrayList<Pixel[]> matrice;
    private int width;
    private int height;
    private  int max;
    private String nom;

    public Image()
    {
        matrice = new ArrayList<Pixel[]>();
        width = 0;
        height = 0;
        max = 0;
        nom = "";
    }

    public ArrayList<Pixel[]> getMatrice()
    {
        return matrice;
    }

    public Pixel getPixel(int y, int x)
    {
        return matrice.get(y)[x];
    }

    public void readDimensions(File f) throws IOException {
        Scanner in = new Scanner(f);

        in.nextLine();
        width = in.nextInt();
        height = in.nextInt();
        max = in.nextInt();

        for (int i = 0; i < height; i++)
        {
            matrice.add(new Pixel[width]);
        }
    }

    public int getMax() {
        return max;
    }

    public void copier(Image i)
    {

    }

    public void extraire(int p1, int c1, int p2, int c2)
    {

    }

    public void reduire()
    {

    }

    public void pivoter90()
    {

    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

}
