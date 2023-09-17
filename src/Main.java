import java.util.Scanner;
import static java.io.FileDescriptor.in;
public class Main {
    public static void main(String[] args) {
        char player1 = 'X';
        char player2 = 'O';
        char turn = player1;
        boolean stop = false;
        StringBuilder winner = new StringBuilder();
        Board gameBoard = new Board(3,3);
        gameBoard.printBoard();
        Scanner sc = new Scanner(System.in);
        while(!stop){
            System.out.println(turn+" to play:");
            if(gameBoard.canPlay()) {
                System.out.println("Please enter row and column: ");
                System.out.print("Row(1-3): ");
                int row = sc.nextInt();
                System.out.print("Column(1-3): ");
                int col = sc.nextInt();
                if (gameBoard.play(turn, row - 1, col - 1)) {
                    if (gameBoard.CheckForWin()) {
                        winner.append("The winner is: ").append(turn);
                        stop = true;
                    }
                    if (turn == player1) {
                        turn = player2;
                    } else {
                        turn = player1;
                    }
                }
                else{
                    System.out.println("Cant play that try again please");
                }
            }
            else {
                winner.append("There is no winner the game is a draw");
                stop = true;
            }
            gameBoard.printBoard();
            System.out.println();
        }
        System.out.println(winner);
    }
}