package sk.stuba.fei.uim.oop.control;

import sk.stuba.fei.uim.oop.gui.Game;
import sk.stuba.fei.uim.oop.gui.board.BoardPanel;
import sk.stuba.fei.uim.oop.gui.board.TilePanel;

import javax.swing.text.Position;
import java.awt.*;
import java.util.*;
import java.util.List;

import static java.lang.Math.abs;


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
        int roundCounter = 0;
        while(true) {
            if(roundCounter % 2 == 0){
                tilePanel.setPlayer(0);
                tilePanel.setOponent(1);
            }
            else if(roundCounter % 2 == 1){
                tilePanel.setPlayer(1);
                tilePanel.setOponent(0);
            }
            this.isTurn(tilePanel.getPlayer());
            this.checkDirections(tilePanel.getPlayer(), tilePanel.getOponent());
            this.checkForPlayableTiles();



            roundCounter++;
                break;
        }
    }

    private int isTurn(int player) {
        return player;
    }

    private void checkDirections(int player, int oponent){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.checkSouthDirection(i, j, player, oponent);
                this.checkNorthDirection(i, j, player, oponent);
                this.checkEastDirection(i, j, player, oponent);
                this.checkWestDirection(i, j, player, oponent);
                this.checkNorthWestDirection(i, j, player, oponent);
                this.checkSouthWestDirection(i, j, player, oponent);
                this.checkSouthEastDirection(i, j, player, oponent);
                this.checkNorthEastDirection(i, j, player, oponent);
            }
        }
    }




    private void checkForPlayableTiles() {
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if(tilePanels[i][j].isPlayable()){
                    tilePanels[i][j].paintPlayableStone();
                }
            }
        }

    }

    private void checkNorthEastDirection(int x, int y, int player, int oponent) {
        if(y+1 == size || x == 0){
            return;
        }
        if(tilePanels[x][y].getOwner() != player){
            return;
        }
        else if(tilePanels[x-1][y+1].getOwner() == oponent){
                for (int j = y+1; j < size; j++) {
                    if (tilePanels[size-1-j][j].getOwner() == oponent) {
                        continue;
                    } else if (tilePanels[size-1-j][j].getOwner() == 3) {
                        tilePanels[size-1-j][j].setPlayable(true);
                        break;
                    } else if (tilePanels[size-1-j][j].getOwner() == player) {
                        break;
                    }
                }
        }
    }

    private void checkSouthEastDirection(int x, int y, int player, int oponent) {
        if(y+1 == size || x+1 == size){
            return;
        }
        if(tilePanels[x][y].getOwner() != player){
            return;
        }
        else if(tilePanels[x+1][y+1].getOwner() == oponent){
            for(int i = y+1; i < size; i++){
                if(tilePanels[i][i].getOwner() == oponent){
                    continue;
                }
                else if(tilePanels[i][i].getOwner() == 3){
                    tilePanels[i][i].setPlayable(true);
                    break;
                }
                else if(tilePanels[i][i].getOwner() == player){
                    break;
                }
            }
        }
    }


    private void checkSouthWestDirection(int x, int y, int player, int oponent) {
        if(y == 0 || x+1 == size){
            return;
        }
        if(tilePanels[x][y].getOwner() != player){
            return;
        }
        else if(tilePanels[x+1][y-1].getOwner() == oponent){
//            for(int i = y-1; i > 0; i--){
                for(int j = x+1; j < size; j++) {
                    if (tilePanels[j][size-1-j].getOwner() == oponent) {
                        continue;
                    } else if (tilePanels[j][size-1-j].getOwner() == 3) {
                        tilePanels[j][size-1-j].setPlayable(true);
                        break;
                    } else if (tilePanels[j][size-1-j].getOwner() == player) {
                        break;
                    }
                }
//            }
        }
    }

    private void checkNorthWestDirection(int x, int y, int player, int oponent) {
        if(y == 0 || x == 0){
            return;
        }
        if(tilePanels[x][y].getOwner() != player){
            return;
        }
        else if(tilePanels[x-1][y-1].getOwner() == oponent){
            for(int i = y-1; i > 0; i--){
                if(tilePanels[i][i].getOwner() == oponent){
                    continue;
                }
                else if(tilePanels[i][i].getOwner() == 3){
                    tilePanels[i][i].setPlayable(true);
                    break;
                }
                else if(tilePanels[i][i].getOwner() == player){
                    break;
                }
            }
        }
    }

    private void checkEastDirection(int x, int y, int player, int oponent) {
        if(y+1 == size){
            return;
        }
        if(tilePanels[x][y+1].getOwner() == oponent){
            for(int i = y+1; i < size; i++){
                if(tilePanels[x][i].getOwner() == oponent){
                    continue;
                }
                else if(tilePanels[x][i].getOwner() == 3){
                    tilePanels[x][i].setPlayable(true);
                    break;
                }
                else if(tilePanels[x][i].getOwner() == player){
                    break;
                }
            }
        }
    }

    private void checkWestDirection(int x, int y, int player, int oponent) {
        if(y == 0){
            return;
        }
        if(tilePanels[x][y-1].getOwner() == oponent){
            for(int i = y-1; i > 0; i--){
                if(tilePanels[x][i].getOwner() == oponent){
                    continue;
                }
                else if(tilePanels[x][i].getOwner() == 3){
                    tilePanels[x][i].setPlayable(true);
                    break;
                }
                else if(tilePanels[x][i].getOwner() == player){
                    break;
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
                if(tilePanels[i][y].getOwner() == oponent){
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
            for(int i = x-1; i >= 0; i--){
                if(tilePanels[i][y].getOwner() == oponent){
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
