import java.util.Scanner;

import api.GameEngine;
import api.AIEngine;
import api.RuleEngine;
import game.Board;
import game.Player;
import game.Move;
import game.Cell;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        AIEngine aiEngine = new AIEngine();
        RuleEngine ruleEngine = new RuleEngine();
        Board board = gameEngine.start("TicTacToe");
        // make moves

        Scanner scanner = new Scanner(System.in);

        while (!ruleEngine.getState(board).isOver()) {

            Player computer = new Player("O"), human = new Player("X");

            System.out.println("Make your move!");
            System.out.println(board);
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            Move humanMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, humanMove);

            System.out.println(board);

            if (!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }

        }
        scanner.close();

        System.out.printf("Game Result: {isOver: %s, Winner: %s}\n", ruleEngine.getState(board).isOver(),
                ruleEngine.getState(board).getWinner());
        System.out.println(board);
    }
}
