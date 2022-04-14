package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.control.GameLogic;
import sk.stuba.fei.uim.oop.gui.board.*;
import sk.stuba.fei.uim.oop.control.listeners.GameFrameListener;
import sk.stuba.fei.uim.oop.gui.menu.ComboBox;
import sk.stuba.fei.uim.oop.gui.menu.RestartButton;
import sk.stuba.fei.uim.oop.gui.menu.SideMenu;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    private int size = 6;
    private ComboBox comboBox;
    private RestartButton buttonRestart;
    private SideMenu sideMenu;
    private BoardPanel boardPanel;
    private TilePanel tilePanel;


    public Game(){
        super("Reversi");
        initComponents(size);
        setFrameParams();
        addListeners();

        sideMenu.add(comboBox);
        sideMenu.add(buttonRestart);

        this.add(boardPanel, BorderLayout.CENTER);
        this.add(sideMenu, BorderLayout.SOUTH);
        this.setVisible(true);
        new GameLogic(this, boardPanel, tilePanel, size);
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
        this.buttonRestart = new RestartButton();
        this.comboBox = new ComboBox();
        this.boardPanel = new BoardPanel(size);
        this.tilePanel = new TilePanel(size);
        fillRestartedBoard();
    }


    private void addListeners(){
        this.comboBox.addActionListener(new GameFrameListener(this));
        this.buttonRestart.addActionListener(new GameFrameListener(this));
        this.setFocusable(true);
        this.addKeyListener(new GameFrameListener(this));
    }

    private void setFrameParams(){
        this.setSize(720, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
    }


    public void reinitializeFrame(int size){
        this.remove(boardPanel);
        this.add(boardPanel = new BoardPanel(size));
        this.setBoardSize(size);
        this.revalidate();
        this.repaint();
        this.fillRestartedBoard();
    }

    private void fillRestartedBoard() {
        boardPanel.fillRestartedPanel(size);
    }

}
