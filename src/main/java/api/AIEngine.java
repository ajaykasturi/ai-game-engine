package api;

import boards.TicTacToeBoard;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

public class AIEngine {
    public Move suggestMove(Player computer, Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            Move sugesstion;
            int threshold = 3;
            if (countMoves(board1) < threshold) {
                sugesstion = getBasicMove(computer, board1);
            } else {
                sugesstion = getSmartMove(computer, board1);
            }
            if (sugesstion != null)
                return sugesstion;
            throw new IllegalArgumentException();
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Move getSmartMove(Player player, TicTacToeBoard board) {
        RuleEngine ruleEngine = new RuleEngine();

        // victory moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player);
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return move;
                    }
                }
            }
        }

        // defensive moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player.flip());
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return new Move(new Cell(i, j), player);
                    }
                }
            }
        }

        return getBasicMove(player, board);
    }

    private int countMoves(TicTacToeBoard board) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) != null) {
                    count++;
                }
            }
        }
        return count;
    }

    private Move getBasicMove(Player player, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    return new Move(new Cell(i, j), player);
                }
            }
        }
        return null;
    }
}
