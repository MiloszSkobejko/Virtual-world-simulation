import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class Lis extends Zwierze implements Serializable
{

    private static final char default_icon = 'L';
    private static final int default_sila = 3;
    private static final char default_inicjatywa = 7;
    private static final String nazwa = "Lis";
    private static final Color kolorLisa = new Color (0xCB4506);



    @Override
    protected void move()
    {
        int prev_x = x;
        int prev_y = y;
        int _timeout = 0;

        Organizm result;

        do
        {
            x = prev_x;
            y = prev_y;


            if (_timeout > 0)
                swiat.AddEventText("Lis wyczul zagrozenie! Zmiana kierunku.\n");

            super.move();
            result = swiat.isfree(this);

            _timeout++;
            if (_timeout > 255)
            {
                x = prev_x;
                y = prev_y;
                wiek++;
                return;
            }

        } while ((result != null && result.getSila() > sila) && canSpawn(2));
    }



    // Konstruktor x, y
    public Lis (int x, int y, Swiat swiat)
    {
        super(default_icon, kolorLisa, default_sila, default_inicjatywa, x, y, swiat);
        this.gatunek = nazwa;
    }


    // Konstruktor losowa pozycja
    public Lis (Swiat swiat)
    {
        super(default_icon, kolorLisa, default_sila, default_inicjatywa, swiat);
        this.gatunek = nazwa;
    }


    @Override
    public void spawn(int spawn_x, int spawn_y)
    {
        new Lis(spawn_x, spawn_y, swiat);
    }



    @Override
    public Organizm kolizja(Organizm inny)
    {
        return null;
    }

}
