package sk.stuba.fei.uim.oop.control.listeners;

import sk.stuba.fei.uim.oop.gui.Game;
import sk.stuba.fei.uim.oop.gui.menu.ComboBox;
import sk.stuba.fei.uim.oop.gui.menu.RestartButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GameFrameListener implements ActionListener {
    private final ComboBox comboBox;
    private final RestartButton buttonRestart;
    private final Game game;

    public GameFrameListener(Game game) {
        this.game = game;
        this.comboBox = game.getComboBox();
        this.buttonRestart = game.getButtonRestart();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == comboBox){
            game.reinitializeFrame((int) Objects.requireNonNull(comboBox.getSelectedItem()));
        }
        if(e.getSource() == buttonRestart){
            game.reinitializeFrame((int)Objects.requireNonNull(comboBox.getSelectedItem()));
        }
    }




}
