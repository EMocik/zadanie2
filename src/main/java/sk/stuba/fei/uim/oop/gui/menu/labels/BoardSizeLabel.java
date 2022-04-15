package sk.stuba.fei.uim.oop.gui.menu.labels;

import javax.swing.*;
import java.awt.*;

public class BoardSizeLabel extends JLabel {
    public BoardSizeLabel(int size) {
        super();
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.setText("Board size : " + size + "x" + size);
    }
}
