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


    public void eclaircir_noircir(int v, int max) {

    }

    public int getCouleurMoyenne()
    {
        return c.getMoyenne();
    }

    public Couleur getCouleur()
    {
        return c;
    }

    public void lire(Scanner s) {
        int[] rgb = new int[3];

        for (int i = 0; i < 3; i++)
            rgb[i] = s.nextInt();

        c.setC(rgb[0], rgb[1], rgb[2]);
    }

    public void ecrire(FileWriter fw) throws IOException {
        fw.write(toString());
    }

    @Override
    public String toString() {
        String text = "";

        for (int i = 0; i < 3; i++)
            text += c.getByIndex(i) + " ";

        return text;
    }
}