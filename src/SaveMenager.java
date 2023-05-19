import java.io.*;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class SaveMenager implements Serializable
{

    // Zapisuje tylko dane obiektów typu organizm. Świat i tak jest zawsze tworzony tak samo,
    // Więc nie ma potrzeby zapisywać jego wartości
    private String plik = "organizmy.gz";

    public void save_objects(ArrayList<Organizm> organizmy, Swiat swiat)
    {
        try
        {

            FileOutputStream file_out = new FileOutputStream(plik);
            GZIPOutputStream gz_ostream = new GZIPOutputStream(file_out);
            ObjectOutputStream oostream = new ObjectOutputStream(gz_ostream);

            oostream.writeObject(organizmy);
            oostream.close();
            swiat.AddEventText("Pomyślnie zapisano swiat do pliku! " + plik);

        }
        catch(Exception ex)
        {
            swiat.AddEventText("Problem z zapisem do pliku " + plik);
            ex.printStackTrace();
        }
    }



    public ArrayList<Organizm> read_objects(Swiat swiat)
    {
        ArrayList<Organizm> organizmy;

        try
        {
            FileInputStream file_in = new FileInputStream(plik);
            GZIPInputStream gz_istream = new GZIPInputStream(file_in);
            ObjectInputStream oistream = new ObjectInputStream(gz_istream);

            organizmy = (ArrayList<Organizm>)oistream.readObject();
            oistream.close();
            swiat.AddEventText("Odczytano dane z pliku " + plik);
            return organizmy;

        }
        catch(Exception ex)
        {
            swiat.AddEventText("Nie znaleziono pliku " + plik);
            return null;
        }
    }
}
