import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClickListener implements MouseListener {
    /**
     * Constructor for objects of class ClickListener
     */
    public ClickListener() {
        // initialise instance variables
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked");
        Object source = e.getSource();
        if (source instanceof Pole) {
            Pole p = (Pole)source;
            p.onClick(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
