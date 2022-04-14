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
        if(tilePanel.isPlayable()){

            int xCoord, yCoord;
            String[] stringToInt;
            stringToInt = e.getComponent().getName().split(" ");
            xCoord = Integer.parseInt(stringToInt[0]);
            yCoord = Integer.parseInt(stringToInt[1]);
            System.out.println(xCoord + " " + yCoord);
//            tilePanel.setOwner(1);
            tilePanel.paintStone(tilePanel.getPlayer());
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
        if(tilePanel.isPlayable()){
            tilePanel.paintStone(tilePanel.getPlayer());
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(tilePanel.isPlayable()) {
            tilePanel.paintPlayableStone();
        }
    }
}
