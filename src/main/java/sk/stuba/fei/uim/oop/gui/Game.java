package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.control.GameLogic;
import sk.stuba.fei.uim.oop.control.UniversalAdapter;
import sk.stuba.fei.uim.oop.gui.board.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JFrame implements ActionListener, ItemListener, KeyListener, MouseListener, MouseMotionListener {
    public static final int DEFAULT_WIDTH = 720;
    public static final int DEFAULT_HEIGHT = 720;
    public int size = 6;


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
    }

    public void initComponents(int size){
        this.sideMenu = new SideMenu();
        this.buttonRestart = new RestartButton();
        this.comboBox = new ComboBox();
        this.boardPanel = new BoardPanel(size);
        this.tilePanel = new TilePanel();
    }


    public void addListeners(){
        this.comboBox.addItemListener(this);
        this.comboBox.addActionListener(this);
        this.buttonRestart.addActionListener(this);
        this.sideMenu.addMouseListener(this);
        this.boardPanel.addMouseMotionListener(this);
        this.boardPanel.addMouseListener(this);
        this.boardPanel.addKeyListener(this);
        this.tilePanel.addMouseListener(this);
    }

    public void setFrameParams(){
        this.setSize(720, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new BorderLayout());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == comboBox){
            reinitializeFrame((int)comboBox.getSelectedItem());
        }
        if(e.getSource() == buttonRestart){
            reinitializeFrame((int)comboBox.getSelectedItem());
            fillRestartedBoard();
        }
    }

    private void fillRestartedBoard() {
        boardPanel.fillRestartedPanel(size);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
//        reinitializeFrame((int)e.getItem());
//        System.out.println(e.getItem());
    }

    public void reinitializeFrame(int size){
        this.remove(boardPanel);
        this.add(boardPanel = new BoardPanel(size));
        this.revalidate();
        this.repaint();

    }
}
