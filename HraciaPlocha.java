import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HraciaPlocha {
    private JFrame frame;

    public HraciaPlocha(int sirka, int vyska, int pocetMin) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                frame = new JFrame("Miny");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setPreferredSize(new Dimension(sirka * MinovePole.velkostPola + 16, (vyska+2) * MinovePole.velkostPola - 1)); // milujem rozlisenia :)

                System.out.println("Velkost okna: " + frame.getSize());
                MinovePole mp = new MinovePole(frame, sirka, vyska, pocetMin);

                frame.add(mp);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
