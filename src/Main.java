import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JFrame gameWindow = new JFrame("Wirtualny Świat");

        //Przciski
        JButton NastepnaTura = new JButton("Następna tura");
        JButton Zapisz = new JButton("Zapisz");
        JButton Wczytaj = new JButton("Wczytaj");

        JButton Wilk_b = new JButton("dod Wilk");
//        JButton Owca_b = new JButton("dod Owca");
//        JButton Lis_b = new JButton("dod Lis");
//        JButton Leniwiec_b = new JButton("dod Leniwiec");
//        JButton Zolw_b = new JButton("dod Zolw");


        Wilk_b.setHorizontalTextPosition(SwingConstants.CENTER);
        Wilk_b.setBackground(new Color(0xB5B5B2));
//        Owca_b.setHorizontalTextPosition(SwingConstants.CENTER);
//        Lis_b.setHorizontalTextPosition(SwingConstants.CENTER);
//        Leniwiec_b.setHorizontalTextPosition(SwingConstants.CENTER);
//        Zolw_b.setHorizontalTextPosition(SwingConstants.CENTER);

        Wczytaj.setHorizontalTextPosition(SwingConstants.CENTER);
        Wczytaj.setVerticalAlignment(SwingConstants.CENTER);
        Wczytaj.setBackground(new Color(0xB5B5B2));
        Zapisz.setHorizontalTextPosition(SwingConstants.CENTER);
        Zapisz.setVerticalAlignment(SwingConstants.CENTER);
        Zapisz.setBackground(new Color(0xB5B5B2));
        NastepnaTura.setHorizontalTextPosition(SwingConstants.CENTER);
        NastepnaTura.setVerticalAlignment(SwingConstants.CENTER);
        NastepnaTura.setBackground(new Color(0xB5B5B2));

        JTextComponent text_console = new JTextPane();
        JScrollPane EventPanel = new JScrollPane(text_console);
        Swiat Ziemia = new Swiat(30, 30, 30, text_console);

        EventPanel.setBounds(Ziemia.getXsize() * Ziemia.getWide() + 10, 0, Ziemia.getToolbarX() - 20, (Ziemia.getYsize() / 2) * Ziemia.getWide() - 20);

        Wczytaj.setBounds(Ziemia.getXsize() * Ziemia.getWide() + 10, (Ziemia.getYsize() / 2) * Ziemia.getWide() + (2 *50), Ziemia.getToolbarX() - 20, 30);
        Wczytaj.addActionListener(e -> Ziemia.load());
        Zapisz.setBounds(Ziemia.getXsize() * Ziemia.getWide() + 10, (Ziemia.getYsize() / 2) * Ziemia.getWide() + 50, Ziemia.getToolbarX() - 20, 30);
        Zapisz.addActionListener(e -> Ziemia.save());
        NastepnaTura.setBounds(Ziemia.getXsize() * Ziemia.getWide() + 10, (Ziemia.getYsize() / 2) * Ziemia.getWide(), Ziemia.getToolbarX() - 20, 30);
        NastepnaTura.addActionListener(e -> Ziemia.wykonajTure());

        // Przyciski Dod zwierzat
        Wilk_b.setBounds(Ziemia.getXsize() * Ziemia.getWide() + 10, (Ziemia.getYsize() / 2) * Ziemia.getWide() + (4 *50), Ziemia.getToolbarX() - 20, 30);
        Wilk_b.addActionListener(e -> Ziemia.SpawnCommand());


        Ziemia.add(Wilk_b);
        Ziemia.add(Wczytaj);
        Ziemia.add(Zapisz);
        Ziemia.add(NastepnaTura);
        Ziemia.add(EventPanel);


        gameWindow.add(Ziemia);
        gameWindow.pack();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setVisible(true);
    }

}
