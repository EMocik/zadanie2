package sk.stuba.fei.uim.oop.gui.board;

public class PlayablePosition {
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int x;
    private int y;

    public PlayablePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
