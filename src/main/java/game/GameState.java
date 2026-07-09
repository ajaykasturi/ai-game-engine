package game;

public class GameState {
    private boolean isOver;
    private String winner;

    public GameState(boolean isOver, String winner) {
        this.isOver = isOver;
        this.winner = winner;
    }

    public boolean isOver() {
        return this.isOver;
    }

    public String getWinner() {
        return this.winner;
    }
}
