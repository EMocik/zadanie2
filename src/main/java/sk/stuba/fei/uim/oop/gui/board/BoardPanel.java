package sk.stuba.fei.uim.oop.gui.board;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    private TilePanel tilePanel;
    private TilePanel[][] tilePanels;
    private Stone stone;

    public BoardPanel(int size){
        super();
        this.setSize(720, 720);
        this.setLayout(new GridLayout(size,size-1, 2, 2));
        this.tilePanel = new TilePanel();
        this.tilePanels = new TilePanel[size][size];
        fillPanel(size);
        this.stone = new Stone();
    }

    public void fillPanel(int size){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++) {
                tilePanels[i][j] = tilePanel = new TilePanel();
                if ((i + j) % 2 == 0){
                    tilePanel.setBackground(new Color(3, 218, 1));
                } else {
                    tilePanel.setBackground(new Color(3, 149, 1));
                }
                tilePanel.setName(i + " " + j);
//                tilePanel.addMouseListener(adapter);
                this.add(tilePanel);
            }
        }
    }

    public void fillRestartedPanel(int size){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++) {
                if (i == ((size/2)-1) && (j == (size/2)-1)){
                    System.out.println(i + " " + j);
                    tilePanels[i][j].paint(getGraphics());
                }
//                tilePanel.setName(i + " " + j);
//                tilePanel.addMouseListener(adapter);
//                this.add(tilePanel);
            }
        }
    }

}
