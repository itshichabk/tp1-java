/**
 * @author Antoine Auger, Hicham Abekiri, Mathis Leduc
 * @version 1.0
 *
 * Cette classe implémente une exception lancée lorsque le mauvais type d'image est manipulé.
 */

public class WrongImageTypeException extends Exception
{
    public WrongImageTypeException() {}

    public WrongImageTypeException(String message)
    {
        super(message);
    }
}
