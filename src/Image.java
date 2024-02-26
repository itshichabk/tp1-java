import java.util.*;
import java.io.*;

public class Image {
    private ArrayList<Pixel[]> matrice;
    private int width;
    private int height;
    private int max;
    private String nom;

    public Image()
    {
        matrice = new ArrayList<>();
        width = 0;
        height = 0;
        max = 0;
        nom = "";
    }

    public void readDimensions(File f) throws FileNotFoundException
    {
        try
        {
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
        catch(FileNotFoundException e)
        {
            throw new FileNotFoundException();
        }
    }

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

    public void copier(Image toCopy)
    {
        toCopy.setNom(this.nom);
        toCopy.setWidth(this.width);
        toCopy.setHeight(this.height);
        toCopy.setMax(this.max);
        toCopy.updateMatrice(this.matrice);
    }

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

    public boolean sontIdentiques(Image img)
    {
        if ((getWidth() != img.getWidth()) || (getHeight() != img.getHeight())
                || (!Objects.equals(getNom(), img.getNom())) || (getMax() != img.getMax()))
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

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
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

    public void intializeMatrice()
    {
        for (int i = 0; i < height; i++)
        {
            matrice.add(new Pixel[width]);
        }
    }


}
