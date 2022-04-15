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

    }


    public void botTurn(){
        game.getCurrentPlayerLabel().setText("Turn: Bot(White)");
        this.deletePlayableStones();
        this.checkDirections(0, 1);

//        this.turnJumpedStonesComputer(this.placeStoneComputer(0), 0);
        this.placeStoneComputer();
        this.deletePlayableStones();
        this.humanTurn();
    }

    private void turnJumpedStonesComputer(PlayablePosition position) {
        if (position != null) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i == position.getX() && j == position.getY()) {
                        for (int k = 0; k < tilePanels[i][j].getPlayablePosition().size(); k++) {
                            PlayablePosition turnPanels = tilePanels[i][j].getPlayablePosition().get(k);
                            tilePanels[turnPanels.getX()][turnPanels.getY()].paintStone(0, 0);
                            tilePanels[turnPanels.getX()][turnPanels.getY()].setOwner(0);
                            tilePanels[turnPanels.getX()][turnPanels.getY()].setPlayable(false);
                        }
                    }
                }
            }
        }
    }

    public void humanTurn(){
        game.getCurrentPlayerLabel().setText("Turn: Human(Black)");
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

    public void placeStoneComputer() {
        int xPos = -1, yPos = -1;
        int sizeOfTurnableStones = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {


                if(tilePanels[i][j].isPlayable()){
                    if(tilePanels[i][j].getPlayablePosition().size() > sizeOfTurnableStones){
                        sizeOfTurnableStones = tilePanels[i][j].getPlayablePosition().size();
                        xPos = i;
                        yPos = j;
                    }
                }
            }
        }
        if (sizeOfTurnableStones > 0) {
            tilePanels[xPos][yPos].paintStone(0, 0);
            tilePanels[xPos][yPos].setPlayable(false);
            this.turnJumpedStonesComputer(new PlayablePosition(xPos, yPos));
        }


    }


    private void deletePlayableStones(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(tilePanels[i][j].isPlayable()) {
                    tilePanels[i][j].deletePlayableStone();
                    tilePanels[i][j].getPlayablePosition().clear();
                    tilePanels[i][j].setPlayable(false);
                    tilePanels[i][j].setOwner(3);
                }
            }
        }

    }

    private void checkDirections(int player, int oponent){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if(tilePanels[i][j].getOwner() == player){
//                    this.checkSouthDirection(i, j, player, oponent);
//                    this.checkNorthDirection(i, j, player, oponent);
//                    this.checkEastDirection(i, j, player, oponent);
//                    this.checkWestDirection(i, j, player, oponent);
//                    this.checkNorthWestDirection(i, j, player, oponent);
//                    this.checkSouthWestDirection(i, j, player, oponent);
//                    this.checkSouthEastDirection(i, j, player, oponent);
//                    this.checkNorthEastDirection(i, j, player, oponent);
                this.checkDirection(i, j, 1, 0, player, oponent);
                this.checkDirection(i, j, -1, 0, player, oponent);
                this.checkDirection(i, j, 0, -1, player, oponent);
                this.checkDirection(i, j, 0, 1, player, oponent);
                this.checkDirection(i, j, -1, -1, player, oponent);
                this.checkDirection(i, j, 1, 1, player, oponent);
                this.checkDirection(i, j, 1, -1, player, oponent);
                this.checkDirection(i, j, -1, 1, player, oponent);
                }

            }
        }
    }

//    private void checkDirections(int x, int y, int xOffset, int yOffset){
//        actualPos = tilePanels[x][y];
//        nextPos = tilePanels[x+xOffset][y+yOffset];
//
//        for (int i = 1; i < size; i++) {
//            if(nextPos.getOwner() == oponent){
//                nieco.add(nextPos);
//            }
//            else if(nextPos.getOwner() == empty){
//                nextPos.setPlayable(true);
//            }
//            nextPos = tilePanels[x+xOffset*i][y+yOffset*i];
//        }
//    }



    private void checkDirection(int x, int y, int xOffset, int yOffset, int player, int oponent) {
        ArrayList<PlayablePosition> tempPlayablePositions = new ArrayList<>();
        if((xOffset == -1 && x == 0) || (yOffset == -1 && y == 0) ||
                (xOffset == 1 && x == size-1) || (yOffset == 1 && y == size-1)){
            return;
        }

        else if(tilePanels[x+xOffset][y+yOffset].getOwner() == oponent){
            for (int j = 1; j < size; j++) {

                if((x + (j * xOffset) >= size) || (y + (j * yOffset) >= size) ||
                   (x + (j * xOffset) < 0) || (y + (j * yOffset) < 0)) {
                    return;
                }
                if (tilePanels[x + (j * xOffset)][y + (j * yOffset)].getOwner() == oponent) {
//                    tilePanels[x + j * xOffset][y + j * yOffset].getPlayablePosition().add(new PlayablePosition(x + j * xOffset, y + j * yOffset));
                    tempPlayablePositions.add(new PlayablePosition(x + (j * xOffset), y + (j * yOffset)));
                    continue;
                } else if ((tilePanels[x + (j * xOffset)][y + (j * yOffset)].getOwner() == 3 ||
                        tilePanels[x + (j * xOffset)][y + (j * yOffset)].getOwner() == 2) &&
                        tempPlayablePositions.size() > 0) {

                    tilePanels[x + (j * xOffset)][y + (j * yOffset)].setPlayable(true);
                    tilePanels[x + (j * xOffset)][y + (j * yOffset)].getPlayablePosition().addAll(tempPlayablePositions);
                    break;
                } else if (tilePanels[x + (j * xOffset)][y + (j * yOffset)].getOwner() == player) {
                    break;
                }
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
                        tilePanels[turnPanels.getX()][turnPanels.getY()].paintStone(player, 1);
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

//    private void checkNorthEastDirection(int x, int y, int player, int oponent) {
//        ArrayList<PlayablePosition> tempPlayablePositions = new ArrayList<>();
//        if(y+1 == size || x == 0){
//            return;
//        }
//
//        else if(tilePanels[x-1][y+1].getOwner() == oponent){
//                for (int j = y+1; j < size; j++) {
//                    if(tilePanels[size-1-j][j].getOwner() == 2){
//                        break;
//                    }else if (tilePanels[size-1-j][j].getOwner() == oponent) {
////                        tilePanels[size-1-j][j].getPlayablePosition().add(new PlayablePosition(size-1-j, j));
//                        tempPlayablePositions.add(new PlayablePosition(size-1-j, j));
//                    } else if (tilePanels[size-1-j][j].getOwner() == 3) {
//                        if(tempPlayablePositions.size() > 0){
//                            tilePanels[size-1-j][j].setPlayable(true);
//                            tilePanels[size-1-j][j].getPlayablePosition().addAll(tempPlayablePositions);
//                        }
//                        break;
//                    } else if (tilePanels[size-1-j][j].getOwner() == player) {
//                        break;
//                    }
//                }
//        }
//    }
//
//    private void checkSouthEastDirection(int x, int y, int player, int oponent) {
//        ArrayList<PlayablePosition> tempPlayablePositions = new ArrayList<>();
//        if(y+1 == size || x+1 == size){
//            return;
//        }
//
//        else if(tilePanels[x+1][y+1].getOwner() == oponent){
//            for(int i = y+1; i < size; i++){
//                if(tilePanels[i][i].getOwner() == 2){
//                    break;
//                }else if(tilePanels[i][i].getOwner() == oponent){
//                    tempPlayablePositions.add(new PlayablePosition(i, i));
//                }
//                else if(tilePanels[i][i].getOwner() == 3){
//                    tilePanels[i][i].setPlayable(true);
////                    tilePanels[i][i].getPlayablePosition().add(new PlayablePosition(i,i));
//                    tilePanels[i][i].getPlayablePosition().addAll(tempPlayablePositions);
//                    break;
//                }
//                else if(tilePanels[i][i].getOwner() == player){
//                    break;
//                }
//            }
//        }
//    }
//
//
//    private void checkSouthWestDirection(int x, int y, int player, int oponent) {
//        ArrayList<PlayablePosition> tempPlayablePositions = new ArrayList<>();
//        if(y == 0 || x+1 == size){
//            return;
//        }
//
//        else if(tilePanels[x+1][y-1].getOwner() == oponent){
////            for(int i = y-1; i > 0; i--){
//                for(int j = x+1; j < size; j++) {
//                    if(tilePanels[j][size-1-j].getOwner() == 2){
//                        break;
//                    }else if (tilePanels[j][size-1-j].getOwner() == oponent) {
//                        tempPlayablePositions.add(new PlayablePosition(j, size-1-j));
//                    } else if (tilePanels[j][size-1-j].getOwner() == 3) {
//                        tilePanels[j][size-1-j].setPlayable(true);
////                        tilePanels[j][size-1-j].getPlayablePosition().add(new PlayablePosition(j, size-1-j));
//                        tilePanels[j][size-1-j].getPlayablePosition().addAll(tempPlayablePositions);
//                        break;
//                    } else if (tilePanels[j][size-1-j].getOwner() == player) {
//                        break;
//                    }
//                }
////            }
//        }
//    }
//
//    private void checkNorthWestDirection(int x, int y, int player, int oponent) {
//        ArrayList<PlayablePosition> tempPlayablePositions = new ArrayList<>();
//        if(y == 0 || x == 0){
//            return;
//        }
//        else if(tilePanels[x-1][y-1].getOwner() == oponent){
//            for(int i = y-1; i > 0; i--){
//                if(tilePanels[i][i].getOwner() == 2){
//                    break;
//                }else if(tilePanels[i][i].getOwner() == oponent){
//                    tempPlayablePositions.add(new PlayablePosition(i, i));
//                }
//                else if(tilePanels[i][i].getOwner() == 3){
//                    tilePanels[i][i].setPlayable(true);
////                    tilePanels[i][i].getPlayablePosition().add(new PlayablePosition(i,i));
//                    tilePanels[i][i].getPlayablePosition().addAll(tempPlayablePositions);
//                    break;
//                }
//                else if(tilePanels[i][i].getOwner() == player){
//                    break;
//                }
//            }
//        }
//    }
//
//    private void checkEastDirection(int x, int y, int player, int oponent) {
//        ArrayList<PlayablePosition> tempPlayablePositions = new ArrayList<>();
//
//        if(y+1 == size){
//            return;
//        }
//        if(tilePanels[x][y+1].getOwner() == oponent){
//            for(int i = y+1; i < size; i++){
//                if(tilePanels[x][i].getOwner() == 2){
//                    break;
//                }
//                else if(tilePanels[x][i].getOwner() == oponent){
//                    tempPlayablePositions.add(new PlayablePosition(x, i));
//                }
//                else if(tilePanels[x][i].getOwner() == 3){
//                    if(tempPlayablePositions.size() > 0) {
//                        tilePanels[x][i].setPlayable(true);
////                        System.out.println(tempPlayablePositions.get(0) + " " + tilePanels[x][i].getPlayablePosition());
//                        tilePanels[x][i].getPlayablePosition().addAll(tempPlayablePositions);
//                        break;
//                    }
//                }
//                else if(tilePanels[x][i].getOwner() == player){
//                    break;
//                }
//            }
//        }
//    }
//
//    private void checkWestDirection(int x, int y, int player, int oponent) {
//        ArrayList<PlayablePosition> tempPlayablePositions = new ArrayList<>();
//        if(y == 0){
//            return;
//        }
//        if(tilePanels[x][y-1].getOwner() == oponent){
//            for(int i = y-1; i > 0; i--){
//                if(tilePanels[x][i].getOwner() == 2){
//                    break;
//                }
//                else if(tilePanels[x][i].getOwner() == oponent){
//                    tempPlayablePositions.add(new PlayablePosition(x, i));
//                }
//                else if(tilePanels[x][i].getOwner() == 3){
//                    tilePanels[x][i].setPlayable(true);
////                    tilePanels[x][i].getPlayablePosition().add(new PlayablePosition(x,i));
//                    tilePanels[x][i].getPlayablePosition().addAll(tempPlayablePositions);
//                    break;
//                }
//                else if(tilePanels[x][i].getOwner() == player){
//                    break;
//                }
//            }
//        }
//    }
//
//    public void checkSouthDirection(int x, int y, int player, int oponent){
//        ArrayList<PlayablePosition> tempPlayablePositions = new ArrayList<>();
//        if(x+1 == size){
//            return;
//        }
//        if(tilePanels[x+1][y].getOwner() == oponent){
//            for(int i = x+1; i < size; i++){
//                if(tilePanels[i][y].getOwner() == 2){
//                    break;
//                }
//                else if(tilePanels[i][y].getOwner() == oponent){
//                    tempPlayablePositions.add(new PlayablePosition(i, y));
//                }
//                else if(tilePanels[i][y].getOwner() == 3){
//                    playableTiles.add(new PlayablePosition(i, y));
//                    tilePanels[i][y].setPlayable(true);
////                    tilePanels[i][y].getPlayablePosition().add(new PlayablePosition(i,y));
//                    tilePanels[i][y].getPlayablePosition().addAll(tempPlayablePositions);
//                    break;
//                }
//                else if(tilePanels[i][y].getOwner() == player){
//                    break;
//                }
//            }
//        }
//    }
//
//    public void checkNorthDirection(int x, int y, int player, int oponent){
//        ArrayList<PlayablePosition> tempPlayablePositions = new ArrayList<>();
//        if(x == 0){
//            return;
//        }
//        if(tilePanels[x-1][y].getOwner() == oponent){
//            for(int i = x-1; i >= 0; i--){
//                if(tilePanels[i][y].getOwner() == 2){
//                    break;
//                }
//                else if(tilePanels[i][y].getOwner() == oponent){
//                    tempPlayablePositions.add(new PlayablePosition(i, y));
//                }
//                else if(tilePanels[i][y].getOwner() == 3){
//                    playableTiles.add(new PlayablePosition(i, y));
//                    tilePanels[i][y].setPlayable(true);
////                    tilePanels[i][y].getPlayablePosition().add(new PlayablePosition(i,y));
//                    tilePanels[i][y].getPlayablePosition().addAll(tempPlayablePositions);
//                    break;
//                }
//                else if(tilePanels[i][y].getOwner() == player){
//                    break;
//                }
//            }
//        }
//    }





}
