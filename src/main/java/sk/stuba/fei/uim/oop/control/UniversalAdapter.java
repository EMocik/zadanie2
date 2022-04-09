package sk.stuba.fei.uim.oop.control;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class UniversalAdapter extends JFrame implements MouseListener, MouseMotionListener, KeyListener, ChangeListener, ActionListener {
    public UniversalAdapter() throws HeadlessException{
        super();


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
        System.out.println("klik " + e.getX() + " " + e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(e.getComponent());
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
    public void stateChanged(ChangeEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
