import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class Owca extends Zwierze implements Serializable
{
    private static final char icon = 'O';
    private static final int sila = 4;
    private static final char inicjatywa = 4;
    private static final String nazwa = "Owca";
    private static final Color kolorOwcy = new Color (0xedf2f4);



    // Konstruktor x, y
    public Owca (int x, int y, Swiat swiat)
    {
        super(icon, kolorOwcy, sila, inicjatywa, x, y, swiat);
        this.gatunek = nazwa;
    }

    // Konstruktor losowa pozycja
    public Owca (Swiat swiat)
    {
        super(icon, kolorOwcy, sila, inicjatywa, swiat);
        this.gatunek = nazwa;
    }



    @Override
    public void spawn(int spawn_x, int spawn_y)
    {
        new Owca(spawn_x, spawn_y, swiat);
    }



    @Override
    public Organizm kolizja(Organizm inny)
    {
        // Owca nie ma specyfikacji przy kolizji z innym organizmem
        return null;
    }
}
