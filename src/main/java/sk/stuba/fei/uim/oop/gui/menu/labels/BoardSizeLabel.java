package sk.stuba.fei.uim.oop.gui.menu.labels;

import javax.swing.*;

public class BoardSizeLabel extends JLabel {
    public BoardSizeLabel(int size) {
        super();
        this.setText("Board size : " + size + "x" + size);
    }
}
