package sk.stuba.fei.uim.oop.gui.board;

import sk.stuba.fei.uim.oop.control.listeners.TilePanelListener;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;


public class TilePanel extends JPanel {
    private boolean tileTaken;
    private int owner;
    private final JLabel picLabel;
    private final int heightSize;
    private final int widthSize;
    private boolean playable;


    public TilePanel(int size){
        this.addMouseListener(new TilePanelListener(this));
        this.setLayout(new BorderLayout());
        this.tileTaken = false;
        this.heightSize = 720/size;
        this.widthSize = 720/size;
        this.picLabel = new JLabel("", SwingConstants.CENTER);
        this.add(picLabel, BorderLayout.CENTER);
    }


    public boolean isPlayable() {return playable;}

    public void setPlayable(boolean playable) {
        this.playable = playable;
        this.setOwner(2);}

    public int getOwner() {return owner;}

    public void setOwner(int owner) {this.owner = owner;}

    public boolean isTileTaken() {return tileTaken;}

    public void setTileTaken(boolean tileTaken) {this.tileTaken = tileTaken;}




    public void paintStone(){
        if(this.getOwner() == 1) {
            picLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/erikb.png"))).getImage().getScaledInstance(widthSize-15, heightSize-15, Image.SCALE_SMOOTH)));
        }
        else if(this.getOwner() == 0) {
            picLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/erikw.png"))).getImage().getScaledInstance(widthSize-15, heightSize-15, Image.SCALE_SMOOTH)));
        }
    }

    public void paintPlayableStone(){
        picLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/erikblank.png"))).getImage().getScaledInstance(widthSize-15, heightSize-15, Image.SCALE_SMOOTH)));
    }

    public void deletePlayableStone(){
        picLabel.setIcon(new ImageIcon());
    }

}
