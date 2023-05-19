import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public abstract class Roslina extends Organizm implements Serializable
{
    protected int SPREAD_CHANCE;

    protected Roslina(char ikona, Color kolor, int sila, int inicjatywa, int x, int y, Swiat swiat)
    {
        super(ikona, kolor, sila, inicjatywa, x, y, swiat);
        krolestwo = ROSLINA;
    }

    // Konstruktor losowa pozycja
    protected Roslina (char ikona, Color kolor, int sila, int inicjatywa, Swiat swiat)
    {
        super(ikona, kolor, sila, inicjatywa, swiat);
        krolestwo = ROSLINA;
    }



    @Override
    public void akcja()
    {
        // Zablokowanie rozprzestrzeniania od razu po spawnie
        if (wiek == 0 && swiat.getTura() > 1)
        {
            wiek++;
            return;
        }


        // Rozprzestrzenianie sie rosliny w metodzie kolizja()
        // Jesli nie ma miejsca to sie nie rozprzestrzenia
        Random random = new Random();
        if (random.nextInt(100) > SPREAD_CHANCE && canSpawn(ROSLINA))
            kolizja(ROSLINA);


        wiek++;
    }


    @Override
    public abstract void spawn(int spawn_x, int spawn_y);
    @Override
    public abstract Organizm kolizja(Organizm inny);
}
