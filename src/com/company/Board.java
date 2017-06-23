package com.company;

/**
 * Created by allenlai on 6/22/17.
 */
public class Board {

    private char[][] board;


    public Board() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }


    public boolean addToken(char type, int row, int column) {
        if (board[row][column] == '-'){
            if (row <= 2 && column <= 2) {
                if (row >= 0 && column >= 0) {
                    board[row][column] = type;
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result.append(board[i][j]);
                result.append('|');
            }
            result.deleteCharAt(result.length()-1);
            result.append('\n');
        }
        return result.toString();
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean aiMakeMove() {
        if (isBoardFull()) {
            return false;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    board[i][j] = 'O';
                    return true;
                }
            }
        }
        return true;

    }

    public char didPlayerWin() {      // return the player that won
        // there are only 8 different ways (3 vert, 3 horiz, 2 diag) to make 3 in a row, brute force check all
        // vert
        for (int i = 0;  i < 3; i++) { // go through all columns
            if (board[0][i] == 'X' &&
                    board[1][i] == 'X' &&
                    board[2][i] == 'X'
                    ) {
                return 'X';
            }
            if (board[0][i] == 'O' &&
                    board[1][i] == 'O' &&
                    board[2][i] == 'O') {

                return 'O';
            }
        }
        // horizontal
        for (int i = 0;  i < 3; i++) { // go through all columns
            if (board[i][0] == 'X' &&
                    board[i][1] == 'X' &&
                    board[i][2] == 'X'
                    ) {
                return 'X';
            }
            if (board[i][0] == 'O' &&
                    board[i][1] == 'O' &&
                    board[i][2] == 'O'
                    ) {
                return 'O';
            }
        }
        // diag
        int countO = 0;
        int countX = 0;
        for (int i = 0; i < 3; i++) {
            if (board[i][i] == 'X') {
                countX++;
            }
            if (board[i][i] == 'O') {
                countO++;
            }
        }
        if (countO == 3) {
            return 'O';
        }
        if (countX == 3) {
            return 'X';
        }

        // TODO: other diag

        return '_';
    }

}
