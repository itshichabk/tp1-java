import java.io.*;
import java.util.*;

public interface Pixel
{
    void eclaircir_noircir(int v, int max);
    void lire(Scanner s);
    void ecrire(java.io.FileWriter bw) throws IOException;

    String toString();
}