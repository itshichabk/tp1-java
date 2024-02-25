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

    public int getMoyenne()
    {
        return (c[0] + c[1] + c[2]) / 3;
    }

    public int getByIndex(int index) {
        return c[index];
    }
    public int setByIndex(int index, int val) {
        return c[index] = val;
    }

    @Override
    public String toString()
    {
        return "R: " + c[0] + ", G:" + c[1] + ", B:" + c[2];
    }
}
