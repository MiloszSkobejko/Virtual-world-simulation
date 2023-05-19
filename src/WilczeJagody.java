import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class WilczeJagody extends Roslina implements Serializable
{
    private static final char icon = 'v';
    private static final int sila = 0;
    private static final char inicjatywa = 0;
    private static final String nazwa = "WilczeJagody";
    private static final int WJ_SPREAD_CHANCE = 98;
    private static final Color kolorWJ = new Color (0x0077b6);



    // Konstruktor x, y
    public WilczeJagody (int x, int y, Swiat swiat)
    {
        super(icon, kolorWJ, sila, inicjatywa, x, y, swiat);
        this.gatunek = nazwa;
        this.SPREAD_CHANCE = WJ_SPREAD_CHANCE;
    }

    // Konstruktor losowa pozycja
    public WilczeJagody (Swiat swiat)
    {
        super(icon, kolorWJ, sila, inicjatywa, swiat);
        this.gatunek = nazwa;
        this.SPREAD_CHANCE = WJ_SPREAD_CHANCE;
    }



    @Override
    public void spawn(int spawn_x, int spawn_y)
    {
        new WilczeJagody(spawn_x, spawn_y, swiat);
    }



    @Override
    public Organizm kolizja(Organizm inny)
    {
        // Zabija atakujacy organizm

        if (inny == null) return null;

        swiat.AddEventText(inny.getGatunek() + " zjadl Wilcze Jagody i zginal\n");
        swiat.remove(this);

        return inny;
    }
}
