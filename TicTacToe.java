// GAME 1 --> Tic-Tac-Toe.

import java.util.Random;
import java.util.Scanner;

class TicTacToe {
    static char[][] board;
    static char playerSymbol = 'X';
    static char computerSymbol = 'O';
    static GameZone gz;

    TicTacToe(){
        board = new char[][] {
                    {'1', '2', '3'},
                    {'4', '5', '6'},
                    {'7', '8', '9'}
        };
    }
    
    void main(String[] args) {
        printBoard();

        while (!isGameOver()) {
            playerMove();
            printBoard();

            if (isGameOver()) {
                break;
            }

            computerMove();
            printBoard();
        }
    }

    static void printBoard() {
        System.out.println(gz.ANSI_BLUE+"-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print(gz.ANSI_BLUE+ "| " +gz.ANSI_RESET);
            for (int j = 0; j < 3; j++) {
                System.out.print(gz.ANSI_RED+ board[i][j] +gz.ANSI_RESET +gz.ANSI_BLUE+ " | ");
            }
            System.out.println();
            System.out.println("-------------"+gz.ANSI_RESET);
        }
        System.out.println();
    }

    static void playerMove() {
        Scanner sc = new Scanner(System.in);
        int index;
        do {
            System.out.print(gz.ANSI_YELLOW+"Enter the index to place 'X' (1-9): "+gz.ANSI_RESET);
            index = sc.nextInt();
        } while (!isValidMove(index));

        placeMove(index, playerSymbol);
    }

    static boolean isValidMove(int index) {
        if (index < 1 || index > 9) {
            System.out.println(gz.ANSI_RED+"Invalid index. Try again."+gz.ANSI_RESET);
            return false;
        }

        int row = (index - 1) / 3;
        int col = (index - 1) % 3;

        if (board[row][col] != 'X' && board[row][col] != 'O') {
            return true;
        }
        else {
            System.out.println(gz.ANSI_RED+"Index already taken. Try again."+gz.ANSI_RESET);
            return false;
        }
    }

    static void computerMove() {
        System.out.println(gz.ANSI_YELLOW+"Computer's turn:"+gz.ANSI_RESET);

        if (makeWinningMove()) {
            return;
        }

        if (blockPlayerWinningMove()) {
            return;
        }

        makeRandomMove();
    }

    static boolean makeWinningMove() {
        return makeMoveForSymbol(computerSymbol);
    }

    static boolean blockPlayerWinningMove() {
        return makeMoveForSymbol(playerSymbol);
    }

    static boolean makeMoveForSymbol(char symbol) {
        for (int i = 0; i < 3; i++) {
            if (countSymbolInRow(i, symbol) == 2) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != 'X' && board[i][j] != 'O') {
                        placeMove((i * 3) + j + 1, computerSymbol);
                        return true;
                    }
                }
            }

            if (countSymbolInColumn(i, symbol) == 2) {
                for (int j = 0; j < 3; j++) {
                    if (board[j][i] != 'X' && board[j][i] != 'O') {
                        placeMove((j * 3) + i + 1, computerSymbol);
                        return true;
                    }
                }
            }
        }

        if (countSymbolInDiagonal(0, 0, 1, 1, symbol) == 2) {
            for (int i = 0; i < 3; i++) {
                if (board[i][i] != 'X' && board[i][i] != 'O') {
                    placeMove((i * 3) + i + 1, computerSymbol);
                    return true;
                }
            }
        }

        if (countSymbolInDiagonal(0, 2, 1, -1, symbol) == 2) {
            for (int i = 0; i < 3; i++) {
                if (board[i][2 - i] != 'X' && board[i][2 - i] != 'O') {
                    placeMove((i * 3) + (2 - i) + 1, computerSymbol);
                    return true;
                }
            }
        }

        return false;
    }

    static int countSymbolInRow(int row, char symbol) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (board[row][i] == symbol) {
                count++;
            }
        }
        return count;
    }

    static int countSymbolInColumn(int col, char symbol) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (board[i][col] == symbol) {
                count++;
            }
        }
        return count;
    }

    static int countSymbolInDiagonal(int startRow, int startCol, int rowIncrement, int colIncrement, char symbol) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (board[startRow][startCol] == symbol) {
                count++;
            }
            startRow += rowIncrement;
            startCol += colIncrement;
        }
        return count;
    }

    static void makeRandomMove() {
        Random random = new Random();

        int index;
        do {
            index = random.nextInt(9) + 1;
        } while (!isValidMove(index));

        placeMove(index, computerSymbol);
    }

    static void placeMove(int index, char symbol) {
        int row = (index - 1) / 3;
        int col = (index - 1) % 3;
        board[row][col] = symbol;
    }

    static boolean isGameOver() {

        if (checkWinner(playerSymbol)) {
            gz.tokensEarned = 10;
            System.out.println(gz.ANSI_GREEN+"You win!"+gz.ANSI_RESET);
            return true;
        }
        if (checkWinner(computerSymbol)) {
            gz.tokensEarned = 0;
            System.out.println(gz.ANSI_RED+"Computer wins!"+gz.ANSI_RESET);
            return true;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    return false;
                }
            }
        }

        gz.tokensEarned = 5;
        System.out.println(gz.ANSI_GREEN+"It's a tie!"+gz.ANSI_RESET);
        return true;
    }

    static boolean checkWinner(char symbol) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                    (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)) {
                return true;
            }
        }

        if ((board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)) {
            return true;
        }

        return false;
    }
}
