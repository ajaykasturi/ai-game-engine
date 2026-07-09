package game;

public class Move {
    private Cell cell;
    private Player player;

    public Move(Cell cell, Player player) {
        setCell(cell);
        setPlayer(player);
    }

    public Cell getCell() {
        return this.cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }
}
