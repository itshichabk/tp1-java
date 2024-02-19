public class Couleur{
    private int[] c = new int[3]; // R - 0 / G - 1 / B - 2

    public Couleur()
    {
        for (int i = 0; i < 3; i++) c[i] = 0;
    }

    public Couleur(int r, int g, int b)
    {
        c[0] = r;
        c[1] = g;
        c[2] = b;
    }

    public void setC(int r, int g, int b)
    {
        c[0] = r;
        c[1] = g;
        c[2] = b;
    }

    public int getR()
    {
        return c[0];
    }

    public int getG()
    {
        return c[1];
    }

    public int getB() {
        return c[2];
    }

    public int getByIndex(int index) {
        return c[index];
    }
}
