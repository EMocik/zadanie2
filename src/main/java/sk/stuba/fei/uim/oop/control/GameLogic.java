package sk.stuba.fei.uim.oop.control;

import lombok.Setter;
import sk.stuba.fei.uim.oop.gui.Game;
import sk.stuba.fei.uim.oop.gui.board.BoardPanel;
import sk.stuba.fei.uim.oop.gui.board.PlayablePosition;
import sk.stuba.fei.uim.oop.gui.board.TilePanel;

import javax.swing.text.Position;
import java.awt.*;
import java.util.*;
import java.util.List;

import static java.lang.Math.abs;


public class GameLogic {
    private BoardPanel boardPanel;
    @Setter
    private TilePanel[][] tilePanels;
    private Game game;
    private TilePanel tilePanel;
    private int stoneCount;
    private int size;
    private ArrayList<PlayablePosition> playableTiles;

    public GameLogic(Game game, BoardPanel boardPanel, int size){
        this.game = game;
        this.size = size;
        this.boardPanel = boardPanel;
        this.playableTiles = new ArrayList<>();
//        this.tilePanel = boardPanel.getPlayTile();
//        this.tilePanels = boardPanel.getTilePanels();
//        tilePanels[0][0].paintPlayableStone();
//        this.checkHumanSouthDirection(2,2, 1, 0);

    }


    public void botTurn(){

        this.deletePlayableStones();
        this.checkDirections(0, 1);
        this.placeStoneComputer(0);
        this.turnJumpedStones(0);
        this.humanTurn();
    }

    public void humanTurn(){

            this.checkDirections(1, 0);
            this.checkForPlayableTiles();

    }

    private boolean isWinner() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!tilePanels[i][j].isTileTaken()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void placeStoneComputer(int player) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(tilePanels[i][j].isPlayable()){
                    tilePanels[i][j].paintStone(player);
                    break;
                }
            }
        }
    }


    private void deletePlayableStones(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tilePanels[i][j].deletePlayableStone();
                tilePanels[i][j].setPlayable(false);
            }
        }

    }

    private void checkDirections(int player, int oponent){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
//                this.checkDirection(i, j, 1, 0, player, oponent);
//                this.checkDirection(i, j, -1, 0, player, oponent);
//                this.checkDirection(i, j, 0, -1, player, oponent);
//                this.checkDirection(i, j, 0, 1, player, oponent);
//                this.checkDirection(i, j, -1, -1, player, oponent);
//                this.checkDirection(i, j, 1, 1, player, oponent);
//                this.checkDirection(i, j, 1, -1, player, oponent);
//                this.checkDirection(i, j, -1, 1, player, oponent);
                if(tilePanels[i][j].getOwner() == player){
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
    }

    private void checkDirection(int x, int y, int xOffset, int yOffset, int player, int oponent) {
        if((xOffset == -1 && x == 0) || (yOffset == -1 && y == 0) ||
                (xOffset == 1 && x == size-1) || (yOffset == 1 && y == size-1)){
            return;
        }
        if(tilePanels[x][y].getOwner() != player){
            return;
        }
        else if(tilePanels[x+xOffset][y+yOffset].getOwner() == oponent){
            for (int j = x+1; j < size; j++) {
                if(xOffset == -1 && yOffset != -1){
                    if (tilePanels[size-1-j][j+yOffset].getOwner() == oponent) {
                        continue;
                    } else if (tilePanels[size-1-j][j+yOffset].getOwner() == 3) {
                        tilePanels[size-1-j][j+yOffset].setPlayable(true);
                        break;
                    } else if (tilePanels[size-1-j][j+yOffset].getOwner() == player) {
                        break;
                    }
                }

//                if(xOffset == -1 && yOffset != -1){
//                    if (tilePanels[size-1-j][j+yOffset].getOwner() == oponent) {
//                        continue;
//                    } else if (tilePanels[size-1-j][j+yOffset].getOwner() == 3) {
//                        tilePanels[size-1-j][j+yOffset].setPlayable(true);
//                        break;
//                    } else if (tilePanels[size-1-j][j+yOffset].getOwner() == player) {
//                        break;
//                    }
//                }
//                else if(xOffset != -1 && yOffset == -1){
//                    if (tilePanels[j][size-1-j].getOwner() == oponent) {
//                        continue;
//                    } else if (tilePanels[j+xOffset][size-1-j].getOwner() == 3) {
//                        tilePanels[j+xOffset][size-1-j].setPlayable(true);
//                        break;
//                    } else if (tilePanels[j+xOffset][size-1-j].getOwner() == player) {
//                        break;
//                    }
//                }
//                else if(xOffset == -1 && yOffset == -1){
//                    if (tilePanels[size-1-j][size-1-j].getOwner() == oponent) {
//                        continue;
//                    } else if (tilePanels[size-1-j][size-1-j].getOwner() == 3) {
//                        tilePanels[size-1-j][size-1-j].setPlayable(true);
//                        break;
//                    } else if (tilePanels[size-1-j][size-1-j].getOwner() == player) {
//                        break;
//                    }
//                }
//                else{
//                    if (tilePanels[j][size-1-j].getOwner() == oponent) {
//                        continue;
//                    } else if (tilePanels[j+xOffset][j+yOffset].getOwner() == 3) {
//                        tilePanels[j+xOffset][j+yOffset].setPlayable(true);
//                        break;
//                    } else if (tilePanels[j+xOffset][j+yOffset].getOwner() == player) {
//                        break;
//                    }
//                }
            }
        }

    }

    public void turnJumpedStones(int player) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(i == boardPanel.getPlayedX() && j == boardPanel.getPlayedY()) {
//                    PlayablePosition> turnPanels = tilePanels[i][j].getPlayablePosition();
                    for(int k = 0; k < tilePanels[i][j].getPlayablePosition().size(); k++){
                        PlayablePosition turnPanels = tilePanels[i][j].getPlayablePosition().get(k);
                        tilePanels[turnPanels.getX()][turnPanels.getY()].paintStone(player);
                    }
                }
            }
        }
    }

    private void checkForPlayableTiles() {
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if(tilePanels[i][j].isPlayable()){
                    tilePanels[i][j].paintPlayableStone();
                    System.out.println(i + " " + j);
                }
            }
        }

    }

    private void checkNorthEastDirection(int x, int y, int player, int oponent) {
        ArrayList<PlayablePosition> tempPlayablePositions = new ArrayList<>();
        if(y+1 == size || x == 0){
            return;
        }

        else if(tilePanels[x-1][y+1].getOwner() == oponent){
                for (int j = y+1; j < size; j++) {
                    if(tilePanels[size-1-j][j].getOwner() == 2){
                        break;
                    }else if (tilePanels[size-1-j][j].getOwner() == oponent) {
//                        tilePanels[size-1-j][j].getPlayablePosition().add(new PlayablePosition(size-1-j, j));
                        tempPlayablePositions.add(new PlayablePosition(size-1-j, j));
                    } else if (tilePanels[size-1-j][j].getOwner() == 3) {
                        if(tempPlayablePositions.size() > 0){
                            tilePanels[size-1-j][j].setPlayable(true);
                            tilePanels[size-1-j][j].getPlayablePosition().addAll(tempPlayablePositions);
                        }
                        break;
                    } else if (tilePanels[size-1-j][j].getOwner() == player) {
                        break;
                    }
                }
        }
    }

    private void checkSouthEastDirection(int x, int y, int player, int oponent) {
        ArrayList<PlayablePosition> tempPlayablePositions = new ArrayList<>();
        if(y+1 == size || x+1 == size){
            return;
        }

        else if(tilePanels[x+1][y+1].getOwner() == oponent){
            for(int i = y+1; i < size; i++){
                if(tilePanels[i][i].getOwner() == 2){
                    break;
                }else if(tilePanels[i][i].getOwner() == oponent){
                    tempPlayablePositions.add(new PlayablePosition(i, i));
                }
                else if(tilePanels[i][i].getOwner() == 3){
                    tilePanels[i][i].setPlayable(true);
//                    tilePanels[i][i].getPlayablePosition().add(new PlayablePosition(i,i));
                    tilePanels[i][i].getPlayablePosition().addAll(tempPlayablePositions);
                    break;
                }
                else if(tilePanels[i][i].getOwner() == player){
                    break;
                }
            }
        }
    }


    private void checkSouthWestDirection(int x, int y, int player, int oponent) {
        ArrayList<PlayablePosition> tempPlayablePositions = new ArrayList<>();
        if(y == 0 || x+1 == size){
            return;
        }

        else if(tilePanels[x+1][y-1].getOwner() == oponent){
//            for(int i = y-1; i > 0; i--){
                for(int j = x+1; j < size; j++) {
                    if(tilePanels[j][size-1-j].getOwner() == 2){
                        break;
                    }else if (tilePanels[j][size-1-j].getOwner() == oponent) {
                        tempPlayablePositions.add(new PlayablePosition(j, size-1-j));
                    } else if (tilePanels[j][size-1-j].getOwner() == 3) {
                        tilePanels[j][size-1-j].setPlayable(true);
//                        tilePanels[j][size-1-j].getPlayablePosition().add(new PlayablePosition(j, size-1-j));
                        tilePanels[j][size-1-j].getPlayablePosition().addAll(tempPlayablePositions);
                        break;
                    } else if (tilePanels[j][size-1-j].getOwner() == player) {
                        break;
                    }
                }
//            }
        }
    }

    private void checkNorthWestDirection(int x, int y, int player, int oponent) {
        ArrayList<PlayablePosition> tempPlayablePositions = new ArrayList<>();
        if(y == 0 || x == 0){
            return;
        }
        else if(tilePanels[x-1][y-1].getOwner() == oponent){
            for(int i = y-1; i > 0; i--){
                if(tilePanels[i][i].getOwner() == 2){
                    break;
                }else if(tilePanels[i][i].getOwner() == oponent){
                    tempPlayablePositions.add(new PlayablePosition(i, i));
                }
                else if(tilePanels[i][i].getOwner() == 3){
                    tilePanels[i][i].setPlayable(true);
//                    tilePanels[i][i].getPlayablePosition().add(new PlayablePosition(i,i));
                    tilePanels[i][i].getPlayablePosition().addAll(tempPlayablePositions);
                    break;
                }
                else if(tilePanels[i][i].getOwner() == player){
                    break;
                }
            }
        }
    }

    private void checkEastDirection(int x, int y, int player, int oponent) {
        ArrayList<PlayablePosition> tempPlayablePositions = new ArrayList<>();

        if(y+1 == size){
            return;
        }
        if(tilePanels[x][y+1].getOwner() == oponent){
            for(int i = y+1; i < size; i++){
                if(tilePanels[x][i].getOwner() == 2){
                    break;
                }
                else if(tilePanels[x][i].getOwner() == oponent){
                    tempPlayablePositions.add(new PlayablePosition(x, i));
                }
                else if(tilePanels[x][i].getOwner() == 3){
                    if(tempPlayablePositions.size() > 0) {
                        tilePanels[x][i].setPlayable(true);
//                        System.out.println(tempPlayablePositions.get(0) + " " + tilePanels[x][i].getPlayablePosition());
                        tilePanels[x][i].getPlayablePosition().addAll(tempPlayablePositions);
                        break;
                    }
                }
                else if(tilePanels[x][i].getOwner() == player){
                    break;
                }
            }
        }
    }

    private void checkWestDirection(int x, int y, int player, int oponent) {
        ArrayList<PlayablePosition> tempPlayablePositions = new ArrayList<>();
        if(y == 0){
            return;
        }
        if(tilePanels[x][y-1].getOwner() == oponent){
            for(int i = y-1; i > 0; i--){
                if(tilePanels[x][i].getOwner() == 2){
                    break;
                }
                else if(tilePanels[x][i].getOwner() == oponent){
                    tempPlayablePositions.add(new PlayablePosition(x, i));
                }
                else if(tilePanels[x][i].getOwner() == 3){
                    tilePanels[x][i].setPlayable(true);
//                    tilePanels[x][i].getPlayablePosition().add(new PlayablePosition(x,i));
                    tilePanels[x][i].getPlayablePosition().addAll(tempPlayablePositions);
                    break;
                }
                else if(tilePanels[x][i].getOwner() == player){
                    break;
                }
            }
        }
    }

    public void checkSouthDirection(int x, int y, int player, int oponent){
        ArrayList<PlayablePosition> tempPlayablePositions = new ArrayList<>();
        if(x+1 == size){
            return;
        }
        if(tilePanels[x+1][y].getOwner() == oponent){
            for(int i = x+1; i < size; i++){
                if(tilePanels[i][y].getOwner() == 2){
                    break;
                }
                else if(tilePanels[i][y].getOwner() == oponent){
                    tempPlayablePositions.add(new PlayablePosition(i, y));
                }
                else if(tilePanels[i][y].getOwner() == 3){
                    playableTiles.add(new PlayablePosition(i, y));
                    tilePanels[i][y].setPlayable(true);
//                    tilePanels[i][y].getPlayablePosition().add(new PlayablePosition(i,y));
                    tilePanels[i][y].getPlayablePosition().addAll(tempPlayablePositions);
                    break;
                }
                else if(tilePanels[i][y].getOwner() == player){
                    break;
                }
            }
        }
    }

    public void checkNorthDirection(int x, int y, int player, int oponent){
        ArrayList<PlayablePosition> tempPlayablePositions = new ArrayList<>();
        if(x == 0){
            return;
        }
        if(tilePanels[x-1][y].getOwner() == oponent){
            for(int i = x-1; i >= 0; i--){
                if(tilePanels[i][y].getOwner() == 2){
                    break;
                }
                else if(tilePanels[i][y].getOwner() == oponent){
                    tilePanels[i][y].addNumberOfTurnableStones();
                }
                else if(tilePanels[i][y].getOwner() == 3){
                    tilePanels[i][y].setPlayable(true);
//                    tilePanels[i][y].getPlayablePosition().add(new PlayablePosition(i,y));
                    break;
                }
                else if(tilePanels[i][y].getOwner() == player){
                    break;
                }
            }
        }
    }





}
