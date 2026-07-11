
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

public class GamePlayerTest {
    GameEngine gameEngine;
    RuleEngine ruleEngine;
    Board board;

    @BeforeEach
    public void setUp() {
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
        board = gameEngine.start("TicTacToe");
    }

    @Test
    public void checkRowForHumanWin() {
        int firstPlayerMoves[][] = new int[][] { { 1, 0 }, { 1, 1 }, { 1, 2 } };
        int secondPlayerMoves[][] = new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 } };
        playGame(firstPlayerMoves, secondPlayerMoves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");

    }

    @Test
    public void checkColForHumanWin() {
        int firstPlayerMoves[][] = new int[][] { { 0, 0 }, { 1, 0 }, { 2, 0 } };
        int secondPlayerMoves[][] = new int[][] { { 0, 1 }, { 0, 2 }, { 1, 1 } };
        playGame(firstPlayerMoves, secondPlayerMoves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkDiagForHumanWin() {
        int firstPlayerMoves[][] = new int[][] { { 0, 0 }, { 1, 1 }, { 2, 2 } };
        int secondPlayerMoves[][] = new int[][] { { 0, 1 }, { 0, 2 }, { 1, 0 } };
        playGame(firstPlayerMoves, secondPlayerMoves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkRevDiagForHumanWin() {
        int firstPlayerMoves[][] = new int[][] { { 0, 2 }, { 1, 1 }, { 2, 0 } };
        int secondPlayerMoves[][] = new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 } };
        playGame(firstPlayerMoves, secondPlayerMoves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkRowForComputerWin() {
        int firstPlayerMoves[][] = new int[][] { { 1, 0 }, { 1, 1 }, { 2, 0 } };
        int secondPlayerMoves[][] = new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 } };
        playGame(firstPlayerMoves, secondPlayerMoves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "O");
    }

    private void playGame(int[][] firstPlayerMoves, int[][] secondPlayerMoves) {
        int next = 0;
        while (!ruleEngine.getState(board).isOver()) {

            Player firstPlayer = new Player("X"), secondPlayer = new Player("O");

            int row = firstPlayerMoves[next][0];
            int col = firstPlayerMoves[next][1];

            Move firstPlayerMove = new Move(new Cell(row, col), firstPlayer);
            gameEngine.move(board, firstPlayerMove);

            if (!ruleEngine.getState(board).isOver()) {
                int sRow = secondPlayerMoves[next][0];
                int sCol = secondPlayerMoves[next][1];
                Move secondPlayerMove = new Move(new Cell(sRow, sCol), secondPlayer);
                gameEngine.move(board, secondPlayerMove);
            }

            next++;
        }
    }
}
