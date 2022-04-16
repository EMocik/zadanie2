package sk.stuba.fei.uim.oop.control.listeners;

import sk.stuba.fei.uim.oop.gui.Game;
import sk.stuba.fei.uim.oop.gui.menu.ComboBox;
import sk.stuba.fei.uim.oop.gui.menu.RestartButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class GameFrameListener implements ActionListener, KeyListener {
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
            game.getGameLogic().humanTurn();

        }
        if(e.getSource() == buttonRestart){
            game.reinitializeFrame((int)Objects.requireNonNull(comboBox.getSelectedItem()));
            game.getGameLogic().humanTurn();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        if(e.getKeyChar() == 'r') {
           game.reinitializeFrame((int) Objects.requireNonNull(comboBox.getSelectedItem()));
           game.getGameLogic().humanTurn();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
