/**
 * @author Antoine Auger, Hicham Abekiri, Mathis Leduc
 * @version 1.0
 *
 * Cette classe implémente un pixel monochrome.
 */

import java.util.*;
import java.io.*;

public class PixelNoirBlanc implements Pixel {

    /**
     * Luminosité du pixeml
     */
    private int luminosite;

    /**
     * Crée un pixel noir
     */
    public PixelNoirBlanc() {
        luminosite = 0;
    }

    /**
     * Crée un pixel monochrome avec une luminosité définie
     * @param luminosite La luminosité du pixel
     */
    public PixelNoirBlanc(int luminosite) {
        this.luminosite = luminosite;
    }

    /**
     * Éclaircit ou noircit le pixel
     * @param v Valeur à ajouter (ou soustraire) à la luminosité du pixel
     * @param max Valeur de luminosité maximale du pixel, transmise par l'image
     */
    public void eclaircir_noircir(int v, int max)
    {
        luminosite += v;

        if (luminosite < 0)
            luminosite = 0;

        else if (luminosite > max)
            luminosite = max;
    }


    public int getLuminosite()
    {
        return luminosite;
    }

    public void setLuminosite(int luminosite)
    {
        this.luminosite = luminosite;
    }

    /**
     * Lit la valeur (luminosité) à partir du fichier puit la définit dans le pixel
     * @param s Scanner passé par référence par l'image, le curseur avance donc au fur et à mesure
     */
    public void lire(Scanner s)
    {
        luminosite = s.nextInt();
    }

    /**
     * Ajoute un pixel noir et blanc à un fichier
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

    @Override
    public String toString()
    {
        return Integer.toString(luminosite);
    }

    /**
     * Retourne un nouveau pixel créé à partir du pixel actuel et des 3 autres pixels qui l'entourent à droite et en bas.
     * Combine les luminosités des 4 pixels en un.
     * @param droite le pixel situé à droite du pixel réducteur
     * @param bas le pixel situé en dessous du pixel réducteur
     * @param basDroite le pixel situé en bas à droite du pixel réducteur
     * @return le nouveau pixel noir et blanc réduit
     */
    public PixelNoirBlanc reduire(PixelNoirBlanc droite, PixelNoirBlanc bas, PixelNoirBlanc basDroite)
    {
        int moyenneLum = (luminosite + droite.luminosite + bas.luminosite + basDroite.luminosite) / 4;

        return new PixelNoirBlanc(moyenneLum);
    }

}

