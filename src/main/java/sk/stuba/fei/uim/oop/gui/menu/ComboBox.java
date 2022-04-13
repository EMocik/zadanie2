package sk.stuba.fei.uim.oop.gui.menu;

import javax.swing.*;

public class ComboBox extends JComboBox {
    final static private Integer[] sizes ={6,8,10,12};

    public ComboBox(){
        super(sizes);
        this.setVisible(true);
    }

}
