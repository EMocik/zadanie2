package sk.stuba.fei.uim.oop.control.listeners;

import sk.stuba.fei.uim.oop.gui.board.BoardPanel;
import sk.stuba.fei.uim.oop.gui.board.TilePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TilePanelListener implements MouseListener {
    private final TilePanel tilePanel;
    private final BoardPanel boardPanel;


    public TilePanelListener(TilePanel tilePanel, BoardPanel boardPanel) {
        this.tilePanel = tilePanel;
        this.boardPanel = boardPanel;
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        if(tilePanel.isPlayable()){

            int xCoord, yCoord;
            String[] stringToInt;
            stringToInt = e.getComponent().getName().split(" ");
            xCoord = Integer.parseInt(stringToInt[0]);
            yCoord = Integer.parseInt(stringToInt[1]);
            tilePanel.paintStone(1);
            tilePanel.setOwner(1);
            tilePanel.setPlayable(false);


            boardPanel.setPlayedTile(xCoord, yCoord);
            boardPanel.endHumanTurn();
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
            tilePanel.paintStone(1);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(tilePanel.isPlayable()) {
            tilePanel.paintPlayableStone();
        }
    }
}
