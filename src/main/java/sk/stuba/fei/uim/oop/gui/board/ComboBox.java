package sk.stuba.fei.uim.oop.gui.board;

import sk.stuba.fei.uim.oop.control.GameLogic;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class ComboBox extends JComboBox {
    final static private Integer[] sizes ={6,8,10,12};

    public ComboBox(){
        super(sizes);
        this.setVisible(true);
//        add(comboBox);

    }

}
