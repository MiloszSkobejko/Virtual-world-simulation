import java.awt.*;
import java.io.Serializable;

public class Papuga extends Zwierze implements Serializable
{
    private static final char icon = 'P';
    private static final int sila = 1;
    private static final char inicjatywa = 8;
    private static final String nazwa = "Papuga";
    private static final Color kolorPapugi = new Color (0xff477e);



    // Konstruktor x, y
    public Papuga (int x, int y, Swiat swiat)
    {
        super(icon, kolorPapugi, sila, inicjatywa, x, y, swiat);
        this.gatunek = nazwa;
    }

    // Konstruktor losowa pozycja
    public Papuga (Swiat swiat)
    {
        super(icon, kolorPapugi, sila, inicjatywa, swiat);
        this.gatunek = nazwa;
    }



    @Override
    public void spawn(int spawn_x, int spawn_y)
    {
        new Papuga(spawn_x, spawn_y, swiat);
    }



    @Override
    public Organizm kolizja(Organizm inny)
    {
        return null;
    }

}
