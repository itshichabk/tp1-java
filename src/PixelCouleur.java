/**
 * @author Antoine Auger, Hicham Abekiri, Mathis Leduc
 * @version 1.0
 *
 * Cette classe implémente un pixel coloré.
 */

import java.util.Scanner;
import java.io.*;

public class PixelCouleur implements Pixel {

    /**
     * Couleur du pixel
     */
    private Couleur c;

    /**
     * Crée un pixel noir
     */
    public PixelCouleur() {
        c = new Couleur();
    }

    /**
     * Crée un pixel coloré à partir d'un mélange de rouge, vert et bleu
     * @param r valeur de la couleur rouge
     * @param g valeur de la couleur verte
     * @param b valeur de la couleur bleue
     */
    public PixelCouleur(int r, int g, int b) {
        c = new Couleur(r, g, b);
    }

    /**
     * Éclaircit ou noircit le pixel
     * @param v Valeur à ajouter (ou soustraire) à la luminosité du pixel
     * @param max Valeur de luminosité maximale du pixel, transmise par l'image
     */
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

    /**
     * Retourne la moyenne de rouge, vert et bleu de la couleur du pixel
     * @return la couleur moyenne du pixel coloré
     */
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

    /**
     * Lit les valeurs rouge+vert+bleu à partir du fichier puis définit la couleur du pixel
     * @param s Scanner passé par référence par l'image, le curseur avance donc au fur et à mesure
     */
    public void lire(Scanner s) {
        int[] rgb = new int[3];

        for (int i = 0; i < 3; i++)
            rgb[i] = s.nextInt();

        c.setC(rgb[0], rgb[1], rgb[2]);
    }

    /**
     * Ajoute un pixel coloré à un fichier
     * @param fw Le fichier destination
     * @throws IOException si une erreur d'écriture a lieu.
     */
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

    /**
     * Affiche les trois valeurs de la couleur du pixel
     * @return les trois valeurs rouge + vert + bleu de la couleur du pixel
     */
    @Override
    public String toString() {
        String text = "";

        for (int i = 0; i < 3; i++)
            text += c.getByIndex(i) + " ";

        return text;
    }

    /**
     * Retourne un nouveau pixel coloré créé à partir du pixel actuel et des 3 autres pixels qui l'entourent à droite et en bas.
     * Combine les couleurs des 4 pixels en un.
     * @param droite le pixel situé à droite du pixel réducteur
     * @param bas le pixel situé en dessous du pixel réducteur
     * @param basDroite le pixel situé en bas à droite du pixel réducteur
     * @return le nouveau pixel coloré réduit
     */
    public PixelCouleur reduire(PixelCouleur droite, PixelCouleur bas, PixelCouleur basDroite)
    {
        int moyenneR = (c.getR() + droite.getCouleur().getR() + bas.getCouleur().getR() + basDroite.getCouleur().getR()) / 4;
        int moyenneG = (c.getG() + droite.getCouleur().getG() + bas.getCouleur().getG() + basDroite.getCouleur().getG()) / 4;
        int moyenneB = (c.getB() + droite.getCouleur().getB() + bas.getCouleur().getB() + basDroite.getCouleur().getB()) / 4;

        return new PixelCouleur(moyenneR, moyenneG, moyenneB);
    }
}