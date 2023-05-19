import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class Trawa extends Roslina implements Serializable
{
    private static final char icon = 't';
    private static final int sila = 0;
    private static final char inicjatywa = 0;
    private static final String nazwa = "Trawa";
    private static final int TRAWA_SPREAD_CHANCE = 85;
    private static final Color kolorTrawy = new Color (0x3a5a40);



    // Konstruktor x, y
    public Trawa (int x, int y, Swiat swiat)
    {
        super(icon, kolorTrawy, sila, inicjatywa, x, y, swiat);
        this.gatunek = nazwa;
        this.SPREAD_CHANCE = TRAWA_SPREAD_CHANCE;
    }

    // Konstruktor losowa pozycja
    public Trawa (Swiat swiat)
    {
        super(icon, kolorTrawy, sila, inicjatywa, swiat);
        this.gatunek = nazwa;
        this.SPREAD_CHANCE = TRAWA_SPREAD_CHANCE;
    }


    @Override
    public void spawn(int spawn_x, int spawn_y)
    {
        new Trawa(spawn_x, spawn_y, swiat);
    }



    @Override
    public Organizm kolizja(Organizm inny)
    {
        // nigdy nie wykorzystana przy trawie, trawa nie ma specyfikacji przy kolizji z innym organizmem
        return null;
    }
}
