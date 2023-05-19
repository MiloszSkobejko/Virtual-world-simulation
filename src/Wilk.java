import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class Wilk extends Zwierze implements Serializable
{
    private static final char icon = 'W';
    private static final int sila = 9;
    private static final char inicjatywa = 5;
    private static final String nazwa = "Wilk";
    private static final Color kolorWilka = new Color (0x353535);


    // Konstruktor x, y
    public Wilk (int x, int y, Swiat swiat)
    {
        super(icon, kolorWilka, sila, inicjatywa, x, y, swiat);
        this.gatunek = nazwa;
    }

    // Konstruktor losowa pozycja
    public Wilk (Swiat swiat)
    {
        super(icon, kolorWilka, sila, inicjatywa, swiat);
        this.gatunek = nazwa;
    }




    @Override
    public void spawn(int spawn_x, int spawn_y)
    {
        new Wilk(spawn_x, spawn_y, swiat);
    }



    @Override
    public Organizm kolizja(Organizm inny)
    {
        return null;
    }

}
