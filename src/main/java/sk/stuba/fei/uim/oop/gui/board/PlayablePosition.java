package sk.stuba.fei.uim.oop.gui.board;

import lombok.Getter;

public class PlayablePosition {
    @Getter
    private final int x;
    @Getter
    private final int y;

    public PlayablePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
