
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

public class GamePlayerTest {
    GameEngine gameEngine;
    AIEngine aiEngine;
    RuleEngine ruleEngine;
    Board board;

    @BeforeEach
    public void setUp() {
        gameEngine = new GameEngine();
        aiEngine = new AIEngine();
        ruleEngine = new RuleEngine();
        board = gameEngine.start("TicTacToe");
    }

    @Test
    public void checkRowForHumanWin() {
        int moves[][] = new int[][] { { 1, 0 }, { 1, 1 }, { 1, 2 } };
        playGame(moves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");

    }

    @Test
    public void checkColForHumanWin() {
        int moves[][] = new int[][] { { 0, 0 }, { 1, 0 }, { 2, 0 } };
        playGame(moves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkDiagForHumanWin() {
        int moves[][] = new int[][] { { 0, 0 }, { 1, 1 }, { 2, 2 } };
        playGame(moves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkRevDiagForHumanWin() {
        int moves[][] = new int[][] { { 0, 2 }, { 1, 1 }, { 2, 0 } };
        playGame(moves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkRowForComputerWin() {
        int moves[][] = new int[][] { { 1, 0 }, { 1, 1 }, { 2, 0 } };
        playGame(moves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "O");
    }

    private void playGame(int[][] moves) {
        int next = 0;
        while (!ruleEngine.getState(board).isOver()) {

            Player computer = new Player("O"), human = new Player("X");

            int row = moves[next][0];
            int col = moves[next][1];
            next++;
            Move humanMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, humanMove);

            if (!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }
        }
    }
}
