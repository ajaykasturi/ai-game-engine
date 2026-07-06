import java.util.Scanner;

import api.GameEngine;
import game.Board;
import game.Player;
import game.Move;
import game.Cell;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        Board board = gameEngine.start("TicTacToe");
        // make moves

        Scanner scanner = new Scanner(System.in);

        while (!gameEngine.isComplete(board).isOver()) {

            Player computer = new Player("O"), opponent = new Player("X");

            System.out.println("Make your move!");
            System.out.println(board);
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            Move opponentMove = new Move(new Cell(row, col));
            gameEngine.move(board, opponent, opponentMove);

            System.out.println(board);

            if (!gameEngine.isComplete(board).isOver()) {
                Move computerMove = gameEngine.suggestMove(computer, board);
                gameEngine.move(board, computer, computerMove);
            }

        }
        scanner.close();

        System.out.printf("Game Result: {isOver: %s, Winner: %s}\n", gameEngine.isComplete(board).isOver(), gameEngine.isComplete(board).getWinner());
        System.out.println(board);
    }
}
