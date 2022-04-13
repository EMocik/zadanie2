package sk.stuba.fei.uim.oop.control;

import sk.stuba.fei.uim.oop.gui.Game;
import sk.stuba.fei.uim.oop.gui.board.BoardPanel;
import sk.stuba.fei.uim.oop.gui.board.TilePanel;

import javax.swing.text.Position;
import java.awt.*;
import java.util.*;
import java.util.List;


public class GameLogic {
    private BoardPanel boardPanel;
    private final TilePanel[][] tilePanels;
    private Game game;
    private int player;
    private int oponent;
    private TilePanel tilePanel;
    private int stoneCount;
    private int size;

    public GameLogic(Game game, BoardPanel boardPanel, TilePanel tilePanel, int size){
        this.game = game;
        this.size = size;
        this.boardPanel = boardPanel;
        this.tilePanel = tilePanel;
        this.tilePanels = boardPanel.getTilePanels();
//        tilePanels[0][0].paintPlayableStone();
//        this.checkHumanSouthDirection(2,2, 1, 0);
        this.gameStart();

    }

    private void gameStart() {
        int roundCounter = 1;
        while(true) {
            if(roundCounter % 2 == 0){
                this.player = 0;
                this.oponent = 1;
            }
            else if(roundCounter % 2 == 1){
                this.player = 1;
                this.oponent = 0;
            }
            this.checkForPlayableTiles(player, oponent);


            roundCounter++;
            break;
        }
    }


    private void checkForPlayableTiles(int player, int oponent) {
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                checkSouthDirection(i, j, player, oponent);
                checkNorthDirection(i, j, player, oponent);
                if(tilePanels[i][j].isPlayable()){
                    tilePanels[i][j].paintPlayableStone();
                }
            }
        }

    }

    public void checkSouthDirection(int x, int y, int player, int oponent){
        if(x+1 == size){
            return;
        }
        if(tilePanels[x+1][y].getOwner() == oponent){
            for(int i = x+1; i < size; i++){
                if(tilePanels[i][y].getOwner() == 2){
                    break;
                }
                else if(tilePanels[i][y].getOwner() == oponent){
                    continue;
                }
                else if(tilePanels[i][y].getOwner() == 3){
                    tilePanels[i][y].setPlayable(true);
                    break;
                }
                else if(tilePanels[i][y].getOwner() == player){
                    break;
                }
            }
        }
    }

    public void checkNorthDirection(int x, int y, int player, int oponent){
        if(x == 0){
            return;
        }
        if(tilePanels[x-1][y].getOwner() == oponent){
            for(int i = x-1; i < size; i++){
                if(tilePanels[i][y].getOwner() == 2){
                    break;
                }
                else if(tilePanels[i][y].getOwner() == oponent){
                    continue;
                }
                else if(tilePanels[i][y].getOwner() == 3){
                    tilePanels[i][y].setPlayable(true);
                    break;
                }
                else if(tilePanels[i][y].getOwner() == player){
                    break;
                }
            }
        }
    }





}
