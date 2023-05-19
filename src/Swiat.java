import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Swiat extends JPanel
{
    private final int size_x;
    private final int size_y;
    private final int wide;
    private final int toolbar_size_x;
    private JTextComponent text_console;
    private String event_text;

    private int iloscOrganizmow;
    private int tura;
    private ArrayList<Organizm> organizmy;


    private void PoczatkoweOrganizmy()
    {
        /* Organizmy poczatkowe */
        new Wilk(this);
        new Wilk(this);
        new Wilk(this);
        new Wilk(this);
        new Wilk(this);

        new Lis(this);
        new Lis(this);
        new Owca( this);
        new Owca( this);
        new Owca( this);

        new Zolw(this);
        new Zolw(this);
        new WilczeJagody(this);
        new WilczeJagody(this);
        new Trawa(this);

        new Guarana(this);
        new Leniwiec(this);
    }



    private void rysujSwiat(Graphics g)
    {
        g.setColor(new Color(0xA0EDEDE9, true));

        for (int i = 0; i < size_x; i++)
        {
            for (int j = 0; j < size_y; j++)
                g.drawRect(i * wide,j * wide,wide,wide);

        }

        // TOOLBAR DRAWING
        //g.fillRect(size_x * wide, 0, toolbar_size_x, size_y * wide);

        for (Organizm organizm : organizmy) {
            organizm.rysowanie(g);
        }
    }



    private void EventTextFieldClear()
    {
        text_console.setText("");
        event_text = "";
    }


    //Tymczasowy konstruktor
    public Swiat(int s_x, int s_y, int widesize, JTextComponent text_console)
    {
        this.text_console = text_console;
        this.organizmy = new ArrayList<>();
        this.iloscOrganizmow = 0;
        this.tura = 0;

        this.size_x = s_x;
        this.size_y = s_y;
        this.wide = widesize;

        this.toolbar_size_x = wide * (size_x / 3);

        setPreferredSize(new Dimension(size_x * wide + toolbar_size_x, size_y * wide));
        setFocusable(true);
        setBackground(Color.white);
        setLayout(null);

        PoczatkoweOrganizmy();
    }



    public void paint(Graphics g)
    {
        super.paint(g);
        rysujSwiat(g);
    }



    public void append(Organizm organizm)
    {
        if (organizm == null)
            return;

        organizmy.add(organizm);
        iloscOrganizmow++;
    }



    public void remove(Organizm organizm)
    {
        if (organizm == null)
            return;

        organizmy.remove(organizm);
        iloscOrganizmow--;
    }



    public Organizm isfree(Organizm organizm)
    {

        for (int i = 0; i < organizmy.size(); i++)
        {
            // Sprawdzanie, czy na polu na ktorym ma stanac organizm, jest juz jakis organizm
            // Sprawdza tez czy porownuje siebie
            if (organizm.overlap(organizmy.get(i)) && (organizmy.get(i) != organizm))
            {

                // Organizmy są tego samego gatunku,zwraca "siebie"
                if (organizm.getGatunek() == organizmy.get(i).getGatunek())
                    return organizm;

                // Organizmy nie są tego samego gatunku, zwraca referencje drugiego organizmu; nastepuje walka
                return organizmy.get(i);
            }
        }

        // Pole jest zupełnie puste
        return null;
    }



    public void AddEventText(String text){
        this.event_text += text;
    }



    public void wykonajTure()
    {
        EventTextFieldClear();
        Collections.sort(organizmy);

        for (int i = 0; i < organizmy.size(); i++)
        {
            organizmy.get(i).akcja();
        }

        for (int i = 0; i < organizmy.size(); i++)
        {
            organizmy.get(i).debugInfo();
        }


        tura++;
        text_console.setText(event_text);
        repaint();
    }



    public void save()
    {
        SaveMenager saving = new SaveMenager();
        saving.save_objects(organizmy, this);
    }



    public void load()
    {
        SaveMenager loading = new SaveMenager();
        ArrayList<Organizm> plik_organizmy = loading.read_objects(this);


        if (plik_organizmy != null)
        {
            //this.iloscOrganizmow = plik_organizmy.size();
            this.organizmy.clear();
            this.organizmy = plik_organizmy;
        }



        for (Organizm organizm : this.organizmy)
        {
            organizm.fixReference(this);

            if (organizm.outOfworld(organizm.x, organizm.y))
                remove(organizm);
        }

        repaint();
    }


    public void SpawnCommand()
    {
        new Wilk(this);
        repaint();
    }


    public int getXsize() {return size_x;}
    public int getYsize() {return size_y;}
    public int getWide() {return wide;}
    public int getToolbarX() {return toolbar_size_x;}
    public int getTura() {return tura;}

}




