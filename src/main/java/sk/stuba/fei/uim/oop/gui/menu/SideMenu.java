package sk.stuba.fei.uim.oop.gui.menu;

import javax.swing.*;
import java.awt.*;

public class SideMenu extends JPanel {

    public SideMenu() {
        super();
        this.setBackground(Color.lightGray);
//        this.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.setLayout(new GridLayout());
        this.setFocusable(false);
    }
}
