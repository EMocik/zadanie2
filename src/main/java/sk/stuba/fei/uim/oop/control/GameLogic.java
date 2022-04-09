package sk.stuba.fei.uim.oop.control;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.gui.Render;

import javax.swing.*;
import java.awt.event.*;

public class GameLogic extends UniversalAdapter{
    @Getter
    private Render render;

    public GameLogic(){
        this.render = new Render();
        this.render.addMouseListener(this);
        this.render.addMouseMotionListener(this);
    }

}
