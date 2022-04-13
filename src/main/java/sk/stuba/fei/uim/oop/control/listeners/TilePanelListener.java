package sk.stuba.fei.uim.oop.control.listeners;

import sk.stuba.fei.uim.oop.gui.board.TilePanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TilePanelListener implements MouseListener {
    private final TilePanel tilePanel;
    private Color prevColor;


    public TilePanelListener(TilePanel tilePanel) {
        this.tilePanel = tilePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xCoord, yCoord;
        String[] stringToInt;
        stringToInt = e.getComponent().getName().split(" ");
        xCoord = Integer.parseInt(stringToInt[0]);
        yCoord = Integer.parseInt(stringToInt[1]);
        if(!tilePanel.isTileTaken()){
            System.out.println(xCoord + " " + yCoord);
            tilePanel.setOwner(1);
            tilePanel.paintStone();
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
        prevColor = tilePanel.getBackground();
        tilePanel.setBackground(new Color(240,160,160));
    }

    @Override
    public void mouseExited(MouseEvent e) {tilePanel.setBackground(prevColor);
    }
}
