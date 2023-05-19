import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class Zolw extends Zwierze implements Serializable
{

    private static final char icon = 'Z';
    private static final int sila = 2;
    private static final char inicjatywa = 1;
    private static final String nazwa = "Zolw";
    private static final Color kolorZolwia = new Color (0xadc178);
    private static final int CHANCE_TO_STAY = 75;
    private static final int defend = 5;



    // Konstruktor x, y
    public Zolw (int x, int y, Swiat swiat)
    {
        super(icon, kolorZolwia, sila, inicjatywa, x, y, swiat);
        this.gatunek = nazwa;
    }

    // Konstruktor losowa pozycja
    public Zolw (Swiat swiat)
    {
        super(icon, kolorZolwia, sila, inicjatywa, swiat);
        this.gatunek = nazwa;
    }


    @Override
    public void akcja()
    {
        Random random = new Random();


        // W 75% przypadkow nie zmienia swojego polozenia
        if (random.nextInt(100) > CHANCE_TO_STAY)
            super.akcja();
        else
        {
            swiat.AddEventText("Zolw nigdzie sie nie rusza\n");
            wiek++;
        }
    }


    @Override
    public void spawn(int spawn_x, int spawn_y)
    {
        new Zolw(spawn_x, spawn_y, swiat);
    }



    @Override
    public Organizm kolizja(Organizm inny)
    {
        // Odpiera atak organizmow o sile < defend (domyslnie 5)
        if (inny.getSila() < defend)
            return this;

        return null;
    }

}
