package sk.stuba.fei.uim.oop.control;

import lombok.Getter;

import java.awt.event.*;

public class GameLogic extends UniversalAdapter{
    public GameLogic(){

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("klik " + e.getX() + " " + e.getY());
    }



}
