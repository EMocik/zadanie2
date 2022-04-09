package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.control.GameLogic;
import sk.stuba.fei.uim.oop.control.UniversalAdapter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


public class Board {

    Disc[][] discs;
    UniversalAdapter adapter = new UniversalAdapter();
    final String[] sizes ={"6x6","8x8","10x10","12x12"};

    public final int width = 720, height = 720;
    JComboBox comboBox = new JComboBox(sizes);
    String selected = "";

    public Board(){
        boardInit();
    }

    private void boardInit(){

        JFrame frame = new JFrame("Reversi");
//        frame.setVisible(true);
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(7,5, 2, 2));
//        frame.addMouseListener(adapter);
        frame.setResizable(true);


        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(6, 6));


        panel.setBackground(Color.BLUE);
        frame.getContentPane().setBackground(Color.black);

//        frame.setLayout(new BorderLayout());
//        GameLogic logic = new GameLogic();
//        frame.addKeyListener(logic);
//        frame.add(logic.getRender());

        discs = new Disc[6][6];
//
        JToolBar sideMenu = new JToolBar();
        sideMenu.setBackground(Color.lightGray);
        sideMenu.addMouseListener(adapter);
        JButton buttonRestart = new JButton("RESTART");
//
//        buttonRestart.addActionListener(logic);
//
//
        buttonRestart.setFocusable(false);
        sideMenu.setLayout(new GridLayout(0, 3));
        sideMenu.setBorder(new LineBorder(Color.lightGray));
        sideMenu.add(comboBox);
//        sideMenu.setPreferredSize(new Dimension(width, width/6));


        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++) {
                if ((i + j) % 2 == 0){

                    JPanel newPanel = new JPanel();
                    newPanel.setName(i + " " + j);
                    newPanel.setBackground(Color.GREEN);
                    newPanel.addMouseListener(adapter);
                    frame.add(newPanel);
                    continue;
                } else {
                    JPanel newPanel = new JPanel();
                    newPanel.addMouseListener(adapter);
                    newPanel.setName(i + " " + j);
                    newPanel.setBackground(Color.BLUE);
                    frame.add(newPanel);
                }
            }
        }

        sideMenu.add(buttonRestart);
        frame.add(sideMenu);
//        frame.add(panel, BorderLayout.CENTER);
//        render.paintComponent(frame.getGraphics());
//        panel.setVisible(true);
        frame.setVisible(true);
    }

    public String getSelectedSize(){
//        for(int i = 0; i < 4; i++) {
//            comboBox.addItem(sizes[i]);
//        }
//        comboBox.add(sizes);
        comboBox.addActionListener(event -> {
            JComboBox comboBox1 = (JComboBox) event.getSource();

            // Print the selected items and the action command.
            selected = (String)comboBox1.getSelectedItem();
            System.out.println("Selected Item  = " + selected);

        });
        return selected;
    }
}
