import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class Guarana extends Roslina implements Serializable
{
    private static final char icon = 'g';
    private static final int sila = 0;
    private static final int boost = 3;
    private static final char inicjatywa = 0;
    private static final String nazwa = "Guarana";
    private static final int GUARANA_SPREAD_CHANCE = 91;
    private static final Color kolorGuarany = new Color (0xef233c);


    // Konstruktor x, y
    public Guarana (int x, int y, Swiat swiat)
    {
        super(icon, kolorGuarany, sila, inicjatywa, x, y, swiat);
        this.gatunek = nazwa;
        this.SPREAD_CHANCE = GUARANA_SPREAD_CHANCE;
    }

    // Konstruktor losowa pozycja
    public Guarana (Swiat swiat)
    {
        super(icon, kolorGuarany, sila, inicjatywa, swiat);
        this.gatunek = nazwa;
        this.SPREAD_CHANCE = GUARANA_SPREAD_CHANCE;
    }



    @Override
    public void spawn(int spawn_x, int spawn_y)
    {
        new Guarana(spawn_x, spawn_y, swiat);
    }



    @Override
    public Organizm kolizja(Organizm inny)
    {
        if (inny == null) return null;

        swiat.AddEventText(inny.getGatunek() + " zwieksza swoja sile o " + boost + "\n");
        inny.ZwiekszSile(boost);

        return null;
    }
}
