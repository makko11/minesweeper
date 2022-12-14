import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MinovePole extends JComponent {
    public static int velkostPola = 20;
    public static Image mina = new ImageIcon("mina.png").getImage();
    public static Image vlajka = new ImageIcon("vlajka.png").getImage();

    private int sirka;
    private int vyska;
    private int pocetMin;
    private Pole[][] polia;

    public MinovePole(JFrame contentPane, int sirka, int vyska, int pocetMin) {
        this.sirka = sirka;
        this.vyska = vyska;
        this.pocetMin = pocetMin;

        this.polia = new Pole[this.sirka][this.vyska];
        boolean[][] miny = new boolean[this.sirka][this.vyska];
        for (int i = 0; i < this.pocetMin; i++) {
            int x = (int)(Math.random() * this.sirka);
            int y = (int)(Math.random() * this.vyska);
            miny[x][y] = true;
        }
        for (int x = 0; x < this.sirka; x++) {
            for (int y = 0; y < this.vyska; y++) {
                this.polia[x][y] = new Pole(this, x, y, miny[x][y]);
                contentPane.add(this.polia[x][y]);
            }
        }
    }

    public Color getFarbaPoctuMin(int pocet) {
        switch (pocet) {
            case 1:
                return Color.blue;
            case 2:
                return Color.green;
            case 3:
                return Color.red;
            case 4:
                return Color.magenta;
            case 5:
                return Color.orange;
            case 6:
                return Color.cyan;
            case 7:
                return Color.pink;
            case 8:
                return Color.yellow;
            default:
                return Color.black;
        }
    }

    public int getPocetMinOkolo(int x, int y) {
        int pocet = 0;
        for (int i = x-1; i <= x+1; i++) {
            for (int j = y-1; j <= y+1; j++) {
                if (i >= 0 && i < this.sirka && j >= 0 && j < this.vyska) {
                    if (this.polia[i][j].mina) {
                        pocet++;
                    }
                }
            }
        }
        return pocet;
    }

    public void odkryOkolie(int x, int y) {
        for (int i = x-1; i <= x+1; i++) {
            for (int j = y-1; j <= y+1; j++) {
                if (i >= 0 && i < this.sirka && j >= 0 && j < this.vyska) {
                    if (!this.polia[i][j].mina && !this.polia[i][j].vlajka && !this.polia[i][j].odkryte) {
                        this.polia[i][j].odkryPole();
                        if (this.getPocetMinOkolo(i, j) == 0) {
                            this.odkryOkolie(i, j);
                        }
                    }
                }
            }
        }
    }
}
