import java.util.Arrays;
import java.util.Objects;

public class Board {
    private final int MAX_ROWS;
    private final int MAX_COLS;
    private Pieces[][] gameboard;
    public Board(int max_rows, int max_cols){
        this.MAX_ROWS = max_rows;
        this.MAX_COLS = max_cols;
        gameboard = new Pieces[MAX_ROWS][MAX_COLS];
    }
    public int getMAX_ROWS() {
        return MAX_ROWS;
    }

    public int getMAX_COLS() {
        return MAX_COLS;
    }
    public void printBoard(){
        String head = "T  1 | 2 | 3 ";
        System.out.println(head);
        System.out.println("-".repeat(head.length()));
        for (int i = 0; i < MAX_ROWS; i++) {
            StringBuilder print = new StringBuilder();
            for (int j = 0; j < MAX_COLS; j++) {
                char name;
                if(gameboard[i][j] == null){
                    name = ' ';
                }
                else {
                    name = gameboard[i][j].getName();
                }
                print.append("|").append(" ").append(name).append(" ");
            }
            System.out.println((i + 1) + " "+ print.substring(1));
            if(i < (MAX_ROWS -1)){
                System.out.println("-".repeat(print.length()+1));
            }
        }
    }
    public boolean play(char player, int row, int col){
        if(free(row,col)){
            gameboard[row][col] = new Pieces(player, row, col);
            return true;
        }
        return false;
    }
    private boolean free(int row, int col){
        if ((row >= MAX_ROWS)||(col >= MAX_COLS)){
            return false;
        }
        return gameboard[row][col] == null;
    }

    public boolean CheckForWin(){
        boolean winner = false;
        for (int i = 0; i < MAX_ROWS; i++) {
            if(Arrays.stream(gameboard[i]).noneMatch(Objects::isNull)){
                winner = Arrays.stream(gameboard[i]).map(Player::getName).distinct().count() <= 1;
            }
        }

        if (!winner){
            a:
            for (int i = 0; i < MAX_COLS; i++) {
                Pieces[] column = new Pieces[MAX_ROWS];
                for (int j = 0; j < MAX_ROWS; j++) {
                    column[j] = gameboard[j][i];
                }
                if(Arrays.stream(column).noneMatch(Objects::isNull)){
                    winner = Arrays.stream(column).map(Player::getName).distinct().count() <= 1;
                    if (winner){
                        break a;
                    }
                }

            }
        }
        if (!winner) {
            Pieces[] diagonals = new Pieces[MAX_ROWS];
            for (int i = 0; i < MAX_ROWS; i++) {
                diagonals[i] = gameboard[i][i];
            }
            if (Arrays.stream(diagonals).noneMatch(Objects::isNull)) {
                winner = Arrays.stream(diagonals).map(Player::getName).distinct().count() <= 1;
            }
        }
        if (!winner) {
            Pieces[] diagonals = new Pieces[MAX_ROWS];
            for (int i = 0; i < MAX_ROWS; i++) {
                diagonals[i] = gameboard[i][(MAX_ROWS-1)-i];
            }
            if (Arrays.stream(diagonals).noneMatch(Objects::isNull)) {
                winner = Arrays.stream(diagonals).map(Player::getName).distinct().count() <= 1;
            }
        }
        return winner;
    }
    public boolean canPlay(){
        return Arrays.stream(gameboard).flatMap(Arrays::stream).anyMatch(Objects::isNull);
    }
}
