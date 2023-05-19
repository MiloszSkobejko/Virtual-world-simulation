import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public abstract class Organizm implements Comparable<Organizm>, Serializable
{
    protected static final int ZWIERZE = 0;
    protected static final int ROSLINA = 1;
    protected static final int LIS_T = 2;

    protected char ikona;
    protected Color kolor;
    protected String gatunek;
    protected int krolestwo;

    protected int sila;
    protected int inicjatywa;
    protected int x;
    protected int y;
    protected int wiek;

    protected Swiat swiat;



    protected Organizm(char ikona, Color kolor, int sila, int inicjatywa, int x, int y, Swiat swiat)
    {
        this.wiek = 0;
        this.kolor = kolor;
        this.ikona = ikona;
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.x = x;
        this.y = y;
        this.swiat = swiat;

        //Dodawanie do swiata
        this.swiat.append(this);
    }



    protected Organizm(char ikona, Color kolor, int sila, int inicjatywa, Swiat swiat)
    {
        this.wiek = 0;
        this.kolor = kolor;
        this.ikona = ikona;
        this.sila = sila;
        this.inicjatywa = inicjatywa;

        Random random = new Random();

        x += random.nextInt(swiat.getXsize());
        y += random.nextInt(swiat.getYsize());

        this.swiat = swiat;
        //Dodawanie do swiata
        this.swiat.append(this);
    }


    
    protected boolean outOfworld(int new_x, int new_y)
    {
        return (new_x >= swiat.getXsize() || new_y >= swiat.getYsize() || new_x < 0 || new_y < 0);
    }




    protected void rysowanie(Graphics g)
    {
        g.setColor(kolor);
        g.fillRect(x * swiat.getWide(), y * swiat.getWide(), swiat.getWide(), swiat.getWide());

        if (krolestwo == ZWIERZE)
        {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.PLAIN, swiat.getWide() - 8));
            g.drawString("" + ikona, x * swiat.getWide() + 4, y * swiat.getWide() + swiat.getWide() - 8);
        }
    }



    public boolean overlap(Organizm inny)
    {
        return (this.x == inny.x && this.y == inny.y);
    }



    protected boolean setRandomPos(int typOrganizmu)
    {
        if (!canSpawn(typOrganizmu)) return false;

        Random random = new Random();

        x += random.nextInt(swiat.getXsize());
        y += random.nextInt(swiat.getYsize());

        return true;
    }




    // Metoda rozmnażania (inna niż move() w zwierze.h, bo zwierze nie sprawdza ws)
    protected boolean canSpawn(int typOrganizmu)
    {

        int prev_x = x;
        int prev_y = y;

        // Iteruj przez wszystkie możliwe zmiany położenia
        for (int i = -1; i <= 1; ++i)
        {
            for (int j = -1; j <= 1; ++j)
            {
                // Nie sprawdzaj tej samej pozycji
                if (i == 0 && j == 0)
                    continue;

                // Sprawdź nową lokalizację
                x = prev_x + i;
                y = prev_y + j;

                Organizm result = swiat.isfree(this);

                switch (typOrganizmu) {
                    case ROSLINA -> {
                        if (result == null && !outOfworld(x, y)) {
                            // Przywróć poprzednie wartości i zwróć true
                            x = prev_x;
                            y = prev_y;
                            return true;
                        }
                    }
                    case ZWIERZE -> {
                        if ((result == null && !outOfworld(x, y)) || (result != null && result.getKrolestwo() == 1 && !outOfworld(x, y))) {
                            // Przywróć poprzednie wartości i zwróć true
                            x = prev_x;
                            y = prev_y;
                            return true;
                        }
                    }
                    case LIS_T -> {
                        if ((result == null && !outOfworld(x, y)) || (result != null && result.getSila() < this.sila && !outOfworld(x, y)))
                        {
                            // Przywróć poprzednie wartości i zwróć true
                            x = prev_x;
                            y = prev_y;
                            return true;
                        }
                    }
                }
            }
        }

        // Przywróć poprzednie wartości i zwróć false
        x = prev_x;
        y = prev_y;
        return false;
    }



    public Organizm walka(Organizm inny)
    {
        // Porownuje 2 organizmy, ktore walczą, zwraca wskaznik na przegrany organizm (aby go usunac)
        if (this.sila > inny.sila || (this.sila == inny.sila && this.wiek > inny.wiek))
            return inny;
        else
            return this;
    }



    public void debugInfo()
    {
        swiat.AddEventText("[" + gatunek + "]" + " S: " + sila + " I: " + inicjatywa + " POS (x: " + x + " y: " + y +" )\n");
    }



    @Override
    public int compareTo(Organizm inny)
    {
        if (inicjatywa == inny.inicjatywa)
        {
            if (wiek == inny.wiek)
                return 0;
            return wiek > inny.wiek ? -1 : 1;
        }

        return inicjatywa > inny.inicjatywa ? -1 : 1;
    }



    // Rozmnazanie organizmow
    public void kolizja(int typOrganizmu)
    {
        int prevX = x;
        int prevY = y;
        int timeout = 0;


        Random random = new Random();

        do {
            x = prevX + random.nextInt(3) - 1;
            y = prevY + random.nextInt(3) - 1;

            if (timeout++ > 255) {
                System.out.println("x=" + x + " y=" + y + "canSpawn: " + canSpawn(0) + "\n");
                x = prevX;
                y = prevY;
                return;
            }
        } while (outOfworld(x, y) || (x == prevX && y == prevY) || (typOrganizmu == 1 ? swiat.isfree(this) != null : swiat.isfree(this) != null && swiat.isfree(this).getKrolestwo() == 0));

        swiat.AddEventText(typOrganizmu == 1 ? gatunek + " sie rozprzestrzenia\n" : "Narodzilo sie nowe zwierze " + gatunek + "[" + x + "," + y +"]\n");

        spawn(x, y);

        x = prevX;
        y = prevY;
    }


    public void fixReference(Swiat swiat) {this.swiat = swiat;}
    public String getGatunek() {return gatunek;}
    public int getSila() {return sila;}
    public int getKrolestwo() {return krolestwo;}
    public void ZwiekszSile(int value)  {sila += value;}

    public abstract void spawn(int spawn_x, int spawn_y);
    public abstract void akcja();
    public abstract Organizm kolizja(Organizm organizm);
}
