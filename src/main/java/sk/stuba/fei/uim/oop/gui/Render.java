package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.gui.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Render extends JPanel{
    private BufferedImage pic1;
    GridLayout gridLayout = new GridLayout(6, 6);
    private int xStart = 0, yStart = 0;
    public Render() {
        try {
            pic1 = ImageIO.read(Objects.requireNonNull(Render.class.getResourceAsStream("/Green_square.svg.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        JLabel pic1Label = new JLabel(new ImageIcon(pic1));
//        this.add(pic1Label);
//        this.setBackground(Color.GREEN);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//
    }


}
