package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;
import sk.stuba.fei.uim.oop.control.GameLogic;
import sk.stuba.fei.uim.oop.gui.board.*;
import sk.stuba.fei.uim.oop.control.listeners.GameFrameListener;
import sk.stuba.fei.uim.oop.gui.menu.*;
import sk.stuba.fei.uim.oop.gui.menu.labels.BoardSizeLabel;
import sk.stuba.fei.uim.oop.gui.menu.labels.CurrentPlayerLabel;
import sk.stuba.fei.uim.oop.gui.menu.labels.CurrentScoreLabel;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    private int size;
    private ComboBox comboBox;
    private RestartButton buttonRestart;
    private SideMenu sideMenu;
    private BoardPanel boardPanel;
    @Getter
    private CurrentScoreLabel currentScoreLabel;
    private BoardSizeLabel boardSizeLabel;
    @Getter
    private CurrentPlayerLabel currentPlayerLabel;
    @Getter
    private GameLogic gameLogic;


    public Game(){
        super("Reversi");
        this.size = 6;
        this.initComponents(size);
        this.setFrameParams();
        this.addListeners();

        this.addToSideMenu();

        this.add(boardPanel, BorderLayout.CENTER);
        this.add(sideMenu, BorderLayout.SOUTH);
        this.setVisible(true);


    }



    public ComboBox getComboBox() {
        return comboBox;
    }

    public RestartButton getButtonRestart() {
        return buttonRestart;
    }

    public void setBoardSize(int size){
        this.size = size;
    }




    private void initComponents(int size){
        this.sideMenu = new SideMenu();
        this.currentPlayerLabel = new CurrentPlayerLabel();
        this.currentScoreLabel = new CurrentScoreLabel();
        this.boardSizeLabel = new BoardSizeLabel(size);
        this.buttonRestart = new RestartButton();
        this.comboBox = new ComboBox();
        this.boardPanel = new BoardPanel(size, this);
        this.reinitializeFrame(size);
        this.getGameLogic().humanTurn();
    }


    private void addListeners(){
        this.comboBox.addActionListener(new GameFrameListener(this));
        this.buttonRestart.addActionListener(new GameFrameListener(this));
        this.setFocusable(true);
        this.addKeyListener(new GameFrameListener(this));
    }

    private void addToSideMenu(){
        sideMenu.add(boardSizeLabel, BorderLayout.LINE_START);
        sideMenu.add(comboBox);
        sideMenu.add(currentPlayerLabel);
        sideMenu.add(currentScoreLabel);
        sideMenu.add(buttonRestart, BorderLayout.LINE_END);
    }

    private void setFrameParams(){
        this.setSize(720, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
    }


    public void reinitializeFrame(int size){
        this.remove(boardPanel);
        this.add(boardPanel = new BoardPanel(size, this));
        this.gameLogic = new GameLogic(this, boardPanel, size);
        boardSizeLabel.setText("Board size : " + size + "x" + size);
        this.setBoardSize(size);
        this.revalidate();
        this.repaint();
        this.fillRestartedBoard();
    }

    private void fillRestartedBoard() {
        boardPanel.fillRestartedPanel(size);
    }

}
