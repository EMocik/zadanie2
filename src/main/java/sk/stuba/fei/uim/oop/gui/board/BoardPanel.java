package sk.stuba.fei.uim.oop.gui.board;


import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    private TilePanel tilePanel;
    private final TilePanel[][] tilePanels;
    private int size;
    public boolean roundEnd;

    public TilePanel getPlayTile() {
        return playTile;
    }

    public void setPlayTile(TilePanel playTile) {
        this.playTile = playTile;
    }

    private TilePanel playTile;

    public BoardPanel(int size){
        super();
        this.setSize(720, 720);
        this.setLayout(new GridLayout(size,size-1, 2, 2));
        this.size = size;
//        this.tilePanel = new TilePanel(size, this);
        this.tilePanels = new TilePanel[size][size];
        fillPanel(size);
    }

    public boolean isRoundEnd() {
        return roundEnd;
    }

    public void setRoundEnd(boolean roundEnd) {
        this.roundEnd = roundEnd;
    }

    public TilePanel[][] getTilePanels() {
        return tilePanels;
    }

    public void setAllUnplayable(){
        for(int i = 0; i < this.size; i++) {
            for (int j = 0; j < size; j++) {
                tilePanels[i][j].setPlayable(false);
            }
        }
    }





    public void fillPanel(int size){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++) {
                tilePanels[i][j] = tilePanel = new TilePanel(size, this);
                tilePanels[i][j].setOwner(3);
                if ((i + j) % 2 == 0){
                    tilePanel.setBackground(new Color(3, 218, 1));
                } else {
                    tilePanel.setBackground(new Color(3, 149, 1));
                }
                tilePanel.setName(i + " " + j);
                this.add(tilePanel);
            }
        }
    }

    public void fillRestartedPanel(int size){
//        this.setAllUnplayable();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++) {
                if (i == ((size/2)-1) && (j == (size/2)-1)){
                    playTile = tilePanels[i][j];
                    tilePanels[i][j].setOwner(1);
                    tilePanels[i][j].paintStone(1);
                    tilePanels[i][j].setTileTaken(true);
                }
                else if((i == (size/2) && (j == (size/2)-1))){
                    tilePanels[i][j].setOwner(0);
                    tilePanels[i][j].paintStone(0);
                    tilePanels[i][j].setTileTaken(true);
                }
                else if (i == ((size/2)-1) && j == (size/2)){
                    tilePanels[i][j].setOwner(0);
                    tilePanels[i][j].paintStone(0);
                    tilePanels[i][j].setTileTaken(true);
                }
                else if((i == size/2) && (j == size/2)){
                    tilePanels[i][j].setOwner(1);
                    tilePanels[i][j].paintStone(1);
                    tilePanels[i][j].setTileTaken(true);
                }
            }
        }
    }




}
