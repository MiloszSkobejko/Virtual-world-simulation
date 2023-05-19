import java.awt.*;
import java.io.Serializable;
import java.util.Random;
import java.util.Set;

public class Leniwiec extends Zwierze implements Serializable
{
    private static final char icon = 'L';
    private static final int sila = 2;
    private static final char inicjatywa = 1;
    private static final String nazwa = "Leniwiec";
    private static final Color kolorLeniwca = new Color (0x415a77);
    private boolean lastmove;



    // Konstruktor x, y
    public Leniwiec (int x, int y, Swiat swiat)
    {
        super(icon, kolorLeniwca, sila, inicjatywa, x, y, swiat);
        this.gatunek = nazwa;
        this.lastmove = false;
    }

    // Konstruktor losowa pozycja
    public Leniwiec (Swiat swiat)
    {
        super(icon, kolorLeniwca, sila, inicjatywa, swiat);
        this.gatunek = nazwa;
        this.lastmove = false;
    }


    @Override
    public void akcja()
    {
        if (!lastmove)
        {
            super.akcja();
            lastmove = true;
        }
        else
        {
            swiat.AddEventText("Leniwiec ruszal sie ostatnio, teraz zostaje na swoim miejscu\n");
            lastmove = false;
            wiek++;
        }
    }



    @Override
    public void spawn(int spawn_x, int spawn_y)
    {
        new Leniwiec(spawn_x, spawn_y, swiat);
    }



    @Override
    public Organizm kolizja(Organizm inny)
    {
        return null;
    }

}
