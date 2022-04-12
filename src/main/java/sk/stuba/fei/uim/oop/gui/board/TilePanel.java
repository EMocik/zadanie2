package sk.stuba.fei.uim.oop.gui.board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;



public class TilePanel extends JPanel implements MouseListener {
    private Color prevColor;
    private BoardPanel boardPanel;
    private final int size;
    private int xCoord, yCoord;
    private String[] stringToInt;
    private boolean tileTaken;

    public TilePanel(int size){
        this.addMouseListener(this);
        this.size = size;
    }


    public void initialPaint(int owner) {
        BufferedImage pic = null;
        if(owner == 1){
            try {
                pic = ImageIO.read(Objects.requireNonNull(TilePanel.class.getResourceAsStream("/erikb.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(owner == 0){
            try {
                pic = ImageIO.read(Objects.requireNonNull(TilePanel.class.getResourceAsStream("/erikw.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        JLabel picLabel = new JLabel(new ImageIcon(pic));



//        switch (size){
//            case 6:
//                picLabel.setSize(this.getWidth(), this.getHeight());
//            case 8:
//                pic.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
//            case 10:
//                pic.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
//            case 12:
//                pic.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
//        }

        this.add(picLabel, BorderLayout.PAGE_END);
        this.repaint();
        this.revalidate();
    }

    public void paint(int owner, int x, int y){

    }

    public boolean isTileTaken() {
        return tileTaken;
    }

    public void setTileTaken(boolean tileTaken) {
        this.tileTaken = tileTaken;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        stringToInt = e.getComponent().getName().split(" ");
        xCoord = Integer.parseInt(stringToInt[0]);
        yCoord = Integer.parseInt(stringToInt[1]);
        System.out.println(xCoord + " " + yCoord);
        if(!isTileTaken()){
//            paint()
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
        prevColor = this.getBackground();
        this.setBackground(new Color (240,160,160));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(prevColor);
    }

}
