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

    public void copier(Image toCopy)
    {
        this.nom = toCopy.getNom();
        this.width = toCopy.getWidth();
        this.height = toCopy.getHeight();
        this.max = toCopy.getMax();
        this.matrice = toCopy.getMatrice();
    }

    public void extraire(int x1, int y1, int x2, int y2)
    {
        if (x1 < x2 && y1 < y2) {
            ArrayList<Pixel[]> nm = new ArrayList<Pixel[]>();

            for (int i = 0; i < getHeight(); i++) {
                if (i >= y1 && i <= y2) { nm.add(new Pixel[x2 - x1 + 1]); }

                for (int j = 0; j < getWidth(); j++) {
                    if ((i >= y1 && i <= y2) && (j >= x1 && j <= x2)) {
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

    public void reduire()
    {

    }

    public boolean sontIdentiques(Image img)
    {
        if ((getWidth() != img.getWidth()) || (getHeight() != img.getHeight())
                || (!Objects.equals(getNom(), img.getNom())) || (getMax() != img.getMax())) {
            return false;
        }
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j <getWidth(); j++) {
                if (!Objects.equals(getPixel(i,j).toString(), img.getPixel(i,j).toString())) {
                    return false;
                }
            }
        }
        return true;
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

    public String getNom()
    {
        return nom;
    }
}
