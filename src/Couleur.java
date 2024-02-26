/**
 * @author Antoine Auger, Hicham Abekiri, Mathis Leduc
 * @version 1.0
 *
 * Cette classe implémente une couleur.
 */

public class Couleur{

    /**
     * Un tableau de trois entiers représentant les quantités de rouge, de vert et de bleu respectivement
     */
    private int[] c = new int[3];

    /**
     * Construit une couleur noire.
     */
    public Couleur()
    {
        for (int i = 0; i < 3; i++) c[i] = 0;
    }

    /**
     * Crée une couleur avec des valeurs RGB définies
     * @param r Rouge
     * @param g Vert
     * @param b Bleu
     */
    public Couleur(int r, int g, int b)
    {
        c[0] = r;
        c[1] = g;
        c[2] = b;
    }

    /**
     * Définit la couleur avec des valeurs RGB définies
     * @param r Rouge
     * @param g Vert
     * @param b Bleu
     */
    public void setC(int r, int g, int b)
    {
        c[0] = r;
        c[1] = g;
        c[2] = b;
    }

    /**
     * Retourne la quantité de rouge dans la couleur
     * @return la quantité de rouge
     */
    public int getR()
    {
        return c[0];
    }

    /**
     * Retourne la quantité de vert dans la couleur
     * @return la quantité de vert
     */
    public int getG()
    {
        return c[1];
    }

    /**
     * Retourne la quantité de bleu dans la couleur
     * @return la quantité de bleu
     */
    public int getB()
    {
        return c[2];
    }

    /**
     * Retourne la moyenne de rouge, vert et bleu.
     * @return la moyenne de rouge, vert et bleu.
     */
    public int getMoyenne()
    {
        return (c[0] + c[1] + c[2]) / 3;
    }

    /**
     * Obtenir une des trois quantités de couleurs RGB en utilisant un index
     * @param index Index de la couleur (0: rouge, 1: vert, 2: bleu)
     * @return la quantité de la couleur voulue
     */
    public int getByIndex(int index)
    {
        return c[index];
    }

    /**
     * Définit la quantité d'une des trois couleurs RGB en utilisant un index
     * @param index Index de la couleur (0: rouge, 1: vert, 2: bleu)
     * @param val Nouvelle valeur de la couleur
     */
    public void setByIndex(int index, int val)
    {
        c[index] = val;
    }

    /**
     * Affiche les informations RGB d'une couleur
     * @return la quantité de rouge, de vert, et de bleu de la couleur
     */
    @Override
    public String toString()
    {
        return "R: " + c[0] + ", G:" + c[1] + ", B:" + c[2];
    }
}
