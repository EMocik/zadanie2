package sk.stuba.fei.uim.oop.gui.board;

import sk.stuba.fei.uim.oop.control.listeners.TilePanelListener;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;


public class TilePanel extends JPanel {
    private boolean tileTaken;
    private int owner;
    public int player;
    public int oponent;
    private final int size;
    private final JLabel picLabel;
    private final int heightSize;
    private final int widthSize;
    private boolean playable;
    private BoardPanel boardPanel;
    private ArrayList<PlayablePosition> playablePosition;
    private int numberOfTurnableStones;



    public TilePanel(int size, BoardPanel boardPanel){
        this.addMouseListener(new TilePanelListener(this, boardPanel));
        this.setLayout(new BorderLayout());
        this.tileTaken = false;
        this.size = size;
        this.boardPanel = boardPanel;
        this.playablePosition = new ArrayList<>();
        this.heightSize = 720/size;
        this.widthSize = 720/size;
        this.picLabel = new JLabel("", SwingConstants.CENTER);
        this.add(picLabel, BorderLayout.CENTER);
    }


    public ArrayList<PlayablePosition> getPlayablePosition() {
        return playablePosition;
    }

    public void setPlayablePosition(ArrayList<PlayablePosition> playablePosition) {
        this.playablePosition = playablePosition;
    }

    public int getNumberOfTurnableStones() {
        return numberOfTurnableStones;
    }

    public void setNumberOfTurnableStones(int numberOfTurnableStones) {
        this.numberOfTurnableStones = numberOfTurnableStones;
    }
    public void addNumberOfTurnableStones(){this.numberOfTurnableStones++;}

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getOponent() {
        return oponent;
    }

    public void setOponent(int oponent) {
        this.oponent = oponent;
    }

    public boolean isPlayable() {return playable;}

    public void setPlayable(boolean playable) {
        this.playable = playable;
        if(playable){
            this.paintPlayableStone();
            this.setOwner(2);
        }
    }

    public int getOwner() {return owner;}

    public void setOwner(int owner) {this.owner = owner;}

    public boolean isTileTaken() {return tileTaken;}

    public void setTileTaken(boolean tileTaken) {this.tileTaken = tileTaken;}




    public void paintStone(int player){
        if(player == 1) {
            picLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/erikb.png"))).getImage().getScaledInstance(widthSize-15, heightSize-15, Image.SCALE_SMOOTH)));
            this.setOwner(player);
            this.setTileTaken(true);
//            boardPanel.getGame().
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
