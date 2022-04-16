package sk.stuba.fei.uim.oop.gui.board;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.control.listeners.TilePanelListener;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;


public class TilePanel extends JPanel {
    @Getter @Setter
    private boolean tileTaken;
    @Getter @Setter
    private int owner;
    private final JLabel picLabel;
    private final int heightSize;
    private final int widthSize;
    private boolean playable;
    private final ArrayList<PlayablePosition> playablePosition;



    public TilePanel(int size, BoardPanel boardPanel){
        this.addMouseListener(new TilePanelListener(this, boardPanel));
        this.setLayout(new BorderLayout());
        this.tileTaken = false;
        this.playablePosition = new ArrayList<>();
        this.heightSize = 720/size;
        this.widthSize = 720/size;
        this.picLabel = new JLabel("", SwingConstants.CENTER);
        this.add(picLabel, BorderLayout.CENTER);
    }


    public ArrayList<PlayablePosition> getPlayablePosition() {
        return playablePosition;
    }


    public boolean isPlayable() {return playable;}

    public void setPlayable(boolean playable) {
        this.playable = playable;
        if(playable){
            this.paintPlayableStone();
            this.setOwner(2);
        }
    }



    public void paintStone(int player){
        if(player == 1) {
            picLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/erikb.png"))).getImage().getScaledInstance(widthSize-15, heightSize-15, Image.SCALE_SMOOTH)));
            this.setOwner(player);
            this.setTileTaken(true);
        }
        else if(player == 0) {
            picLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/erikw.png"))).getImage().getScaledInstance(widthSize-15, heightSize-15, Image.SCALE_SMOOTH)));
            this.setOwner(player);
            this.setTileTaken(true);
        }
    }

    public void paintPlayableStone(){
        picLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/erikblank.png"))).getImage().getScaledInstance(widthSize-15, heightSize-15, Image.SCALE_SMOOTH)));
    }

    public void deletePlayableStone(){
        if(this.isPlayable()) {
            picLabel.setIcon(new ImageIcon());
            this.setTileTaken(false);
        }
    }

}
