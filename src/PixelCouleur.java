import java.util.Scanner;
import java.io.*;

public class PixelCouleur  implements Pixel {

    private Couleur c; // R - 0 / G - 1 / B - 2

    public PixelCouleur() {
        c = new Couleur();
    }

    public PixelCouleur(int r, int g, int b) {
        c = new Couleur(r, g, b);
    }

    public void eclaircir_noircir(int v, int max)
    {
        for (int i = 0; i < 3; i++) {
            this.c.setByIndex(i, this.c.getByIndex(i) + v);

            if (this.c.getByIndex(i) > max) {
                this.c.setByIndex(i, max);
            } else if (this.c.getByIndex(i) < 0) {
                this.c.setByIndex(i, 0);
            }
        }
    }

    public int getCouleurMoyenne()
    {
        return c.getMoyenne();
    }

    public Couleur getCouleur()
    {
        return c;
    }

    public void setCouleur(Couleur c)
    {
        this.c = c;
    }

    public void lire(Scanner s) {
        int[] rgb = new int[3];

        for (int i = 0; i < 3; i++)
            rgb[i] = s.nextInt();

        c.setC(rgb[0], rgb[1], rgb[2]);
    }

    public void ecrire(FileWriter fw) throws IOException
    {
        try
        {
            fw.write(toString());
        }
        catch(IOException e)
        {
            throw new IOException("Erreur lors de l'écriture du pixel.");
        }
    }

    @Override
    public String toString() {
        String text = "";

        for (int i = 0; i < 3; i++)
            text += c.getByIndex(i) + " ";

        return text;
    }

    public PixelCouleur reduire(PixelCouleur droite, PixelCouleur bas, PixelCouleur basDroite)
    {
        int moyenneR = (c.getR() + droite.getCouleur().getR() + bas.getCouleur().getR() + basDroite.getCouleur().getR()) / 4;
        int moyenneG = (c.getG() + droite.getCouleur().getG() + bas.getCouleur().getG() + basDroite.getCouleur().getG()) / 4;
        int moyenneB = (c.getB() + droite.getCouleur().getB() + bas.getCouleur().getB() + basDroite.getCouleur().getB()) / 4;

        return new PixelCouleur(moyenneR, moyenneG, moyenneB);
    }
}