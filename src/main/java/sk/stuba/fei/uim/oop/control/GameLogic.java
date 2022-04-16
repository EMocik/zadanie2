package sk.stuba.fei.uim.oop.control;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.gui.Game;
import sk.stuba.fei.uim.oop.gui.board.BoardPanel;
import sk.stuba.fei.uim.oop.gui.board.PlayablePosition;
import sk.stuba.fei.uim.oop.gui.board.TilePanel;
import sk.stuba.fei.uim.oop.gui.menu.labels.CurrentScoreLabel;
import java.util.*;



public class GameLogic {
    private final BoardPanel boardPanel;
    @Getter @Setter
    private TilePanel[][] tilePanels;
    private final Game game;
    private boolean canBotPlay, canHumanPlay;
    private final int size;
    private final CurrentScoreLabel currentScoreLabel;

    public GameLogic(Game game, BoardPanel boardPanel, CurrentScoreLabel currentScoreLabel, int size){
        this.game = game;
        this.size = size;
        this.boardPanel = boardPanel;
        this.currentScoreLabel = currentScoreLabel;
    }


    public void humanTurn(){
        game.getCurrentPlayerLabel().setText("Turn: Human(Black)");
        this.canHumanPlay = false;

        this.checkDirections(1, 0);
        this.checkForPlayableTiles(1);
        this.countStones();
    }

    public void botTurn(){
        game.getCurrentPlayerLabel().setText("Turn: Bot(White)");
        this.canBotPlay = false;

        this.deletePlayableStones();
        this.checkDirections(0, 1);
        this.checkForPlayableTiles(0);
        this.placeStoneComputer();
        this.deletePlayableStones();
        this.humanTurn();
    }

    private void checkDirections(int player, int oponent){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if(tilePanels[i][j].getOwner() == player){

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

    private void turnJumpedStonesComputer(PlayablePosition position) {
        if (position != null) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i == position.getX() && j == position.getY()) {
                        for (int k = 0; k < tilePanels[i][j].getPlayablePosition().size(); k++) {
                            PlayablePosition turnPanels = tilePanels[i][j].getPlayablePosition().get(k);
                            tilePanels[turnPanels.getX()][turnPanels.getY()].paintStone(0);
                            tilePanels[turnPanels.getX()][turnPanels.getY()].setOwner(0);
                            tilePanels[turnPanels.getX()][turnPanels.getY()].setPlayable(false);
                        }
                    }
                }
            }
        }
    }

    private void placeStoneComputer() {
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
            tilePanels[xPos][yPos].paintStone(0);
            tilePanels[xPos][yPos].setPlayable(false);
            this.turnJumpedStonesComputer(new PlayablePosition(xPos, yPos));
        }
        else
            System.out.println("Bot can't play");
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

    public void turnJumpedStones(int player) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(i == boardPanel.getXCoord() && j == boardPanel.getYCoord()) {
                    for(int k = 0; k < tilePanels[i][j].getPlayablePosition().size(); k++){
                        PlayablePosition turnPanels = tilePanels[i][j].getPlayablePosition().get(k);
                        tilePanels[turnPanels.getX()][turnPanels.getY()].paintStone(player);
                    }
                }
            }
        }
    }

    private void checkForPlayableTiles(int player) {
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if(tilePanels[i][j].isPlayable()){
                    if(player == 1) {
                        tilePanels[i][j].paintPlayableStone();
                        this.canHumanPlay = true;
                    }
                    else{
                        this.canBotPlay = true;
                    }
                }
            }
        }

    }

    private void countStones(){
        int humanStones = 0;
        int botStones = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(tilePanels[i][j].getOwner() == 1){
                    humanStones++;
                }
                else if(tilePanels[i][j].getOwner() == 0){
                    botStones++;
                }
            }
        }
        if(!canHumanPlay){
            System.out.println("Human can't play");
            this.checkForPlayableTiles(0);
            if(!canBotPlay) {
                if (humanStones > botStones) {
                    game.getCurrentPlayerLabel().setText("You have won!");
                } else if (humanStones < botStones) {
                    game.getCurrentPlayerLabel().setText("The AI has won!");
                } else {
                    game.getCurrentPlayerLabel().setText("It's a tie!");
                }
            }
            else{
                this.botTurn();
            }
        }

        currentScoreLabel.setText("Black: " + humanStones + "/White: " + botStones);
    }

}
