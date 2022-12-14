import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pole extends JComponent {
    private int x;
    private int y;
    public boolean odkryte = false;
    public boolean mina = false;
    public boolean vlajka = false;
    private MinovePole mp;

    public Pole(MinovePole mp, int x, int y, boolean mina) {
        this.x = x;
        this.y = y;
        this.mina = mina;
        this.mp = mp;
        this.setSize(MinovePole.velkostPola, MinovePole.velkostPola);
        this.setLocation(x * MinovePole.velkostPola, y * MinovePole.velkostPola);
        this.setBackground(Color.black);
        this.setForeground(Color.gray);
        ClickListener cl = new ClickListener();
        this.addMouseListener(cl);
    }

    public void onClick(MouseEvent e) {
        int btn = e.getButton();
        if(btn == MouseEvent.BUTTON1) {
            this.odkryPole();
            if(!this.mina && this.mp.getPocetMinOkolo(x, y) == 0) {
                this.mp.odkryOkolie(x, y);
            }
        } else if(btn == MouseEvent.BUTTON3 && !this.odkryte) {
            this.vlajka = !this.vlajka;
            this.repaint();
        } else if(btn == MouseEvent.BUTTON2) { // debug
            System.out.println(this.toString());
            System.out.println(this.mp.getPocetMinOkolo(this.x, this.y));
        }
    }

    public void odkryPole() {
        if(this.vlajka || this.odkryte) return;
        this.odkryte = true;
        if(this.mina) {
            // trigger koniec hry
        } else {
            this.setForeground(Color.lightGray);
        }
        this.repaint();
    }

    @Override // debug
    public String toString() {
        return "Pole{" +
                "x=" + x +
                ", y=" + y +
                ", odkryte=" + odkryte +
                ", mina=" + mina +
                '}';
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        g.setColor(getForeground());
        g.fillRect(1, 1, getWidth() - 2, getHeight() - 2);
        if(this.vlajka) {
            g.drawImage(MinovePole.vlajka, 0, 0, null);
        } else if(!this.mina && this.odkryte) {
            int pocetMin = this.mp.getPocetMinOkolo(this.x, this.y);
            if(pocetMin > 0) {
                g.setFont(new Font("Arial", Font.BOLD, 13));
                g.setColor(this.mp.getFarbaPoctuMin(pocetMin));
                g.drawString(""+pocetMin, 7, 14);
            }
        } else if(this.mina && this.odkryte) {
            g.drawImage(MinovePole.mina, 0, 0, null);
        }
    }
}
