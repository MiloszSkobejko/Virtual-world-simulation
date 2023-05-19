import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public abstract class Zwierze extends Organizm implements Serializable
{
    private static final int MINIMAL_AGE_TO_REPRODUCE = 10;


    protected Zwierze(char ikona, Color kolor, int sila, int inicjatywa, int x, int y, Swiat swiat)
    {
        super(ikona, kolor, sila, inicjatywa, x, y, swiat);
        krolestwo = ZWIERZE;
    }

    // Konstruktor losowa pozycja
    protected Zwierze (char ikona, Color kolor, int sila, int inicjatywa, Swiat swiat)
    {
        super(ikona, kolor, sila, inicjatywa, swiat);
        krolestwo = ZWIERZE;
    }


    protected void move()
    {

        Random random = new Random();

        int move_x;
        int move_y;

        do
        {
            move_x = random.nextInt(3) - 1;
            move_y = random.nextInt(3) - 1;

        } while (outOfworld(x + move_x, y + move_y) || (move_x == 0 && move_y == 0));


        x += move_x;
        y += move_y;
    }


    @Override
    public void akcja()
    {

        swiat.AddEventText(gatunek + " przemieszcza sie\n");

        int prev_x = x;
        int prev_y = y;

        move();


        // Sprawdzanie, czy pole na które ma się ruszyc organizm jest wolne, zajete przez
        // ten sam gatunek, czy zajete przez inny.

        Organizm result = swiat.isfree(this);


        // Pole jest zajety przez ten sam gatunek i dochodzi do rozmnażania
        if (result == this && wiek > MINIMAL_AGE_TO_REPRODUCE)
        {
            x = prev_x;
            y = prev_y;

            if (canSpawn(ZWIERZE))
                kolizja(ZWIERZE);
            else
                swiat.AddEventText("Male zwierze " + gatunek + " nie moze przyjsc na swiat. Za malo miejsca!\n");
        }


        // Pole jest zajety przez inny gatunek i dochodzi do walki
        // Atakakujacym jest zawsze organizm wywolujacy funkcje walka().
        // Czyli ten ktora ma aktualnie ruch
        if (result != null && result != this)
        {

            Organizm temp = result.kolizja(this);


            // Jesli walka zwroci this to organizm przegral i musi sie usunac (przegrywa tez z wilczymi jagodami)
            // temp zawiera wynik metody kolizja(Organizm *), w zaleznosci od kombinacji wykonuje sie jedna z ponizszych
            // opcji: walka(z innym) konczy sie porazka lub kolizja() z innym organizmem konczy sie porazka = usuniecie siebie
            // kolizja() == inny organizm, znaczy, ze ten organizm odparl atak i nie zostanie usuniety. kolizja() == null znaczy,
            // ze drugi organizm przegral
            if (walka(result) == this || temp == this)
            {
                swiat.AddEventText(result.getGatunek() + " zjadl " + gatunek + "\n");
                swiat.remove(this);
                return;
            }
            else if (temp == result)
            {
                // Atak sie nie udal, wracanie na poprzednia pozycje
                swiat.AddEventText(result.getGatunek() + " odparl atak " + gatunek + "\n");
                x = prev_x;
                y = prev_y;
            }
            else
            {
                // Tutaj 2 organizm (*result) przegral
                swiat.remove(result);
                swiat.AddEventText(gatunek + " zjadl " + result.getGatunek() + "\n");
            }
        }

        wiek++;
    }


    @Override
    public abstract void spawn(int spawn_x, int spawn_y);
    @Override
    public abstract Organizm kolizja(Organizm organizm);

}
