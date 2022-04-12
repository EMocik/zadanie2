package sk.stuba.fei.uim.oop.gui.board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TilePanel extends JPanel implements MouseListener {
    private Color prevColor;

    public TilePanel(){
        this.addMouseListener(this);
    }

    public Dimension getSize(){
        return this.getSize();
    }

    public void paint(Graphics g) {
        g.drawImage()
        g.setColor(Color.BLACK);
        g.drawOval(10, 10 , 30, 30);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        prevColor = this.getBackground();
        this.setBackground(new Color (240,160,160));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(prevColor);
    }
}
