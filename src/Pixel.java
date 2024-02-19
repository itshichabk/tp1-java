import java.io.*;
import java.util.*;

public interface Pixel
{
    public void eclaircir_noircir(int v, int max);
    public void lire(Scanner s);
    public void ecrire(java.io.FileWriter bw) throws IOException;

    String toString();
    // reduire??????
}