package com.company;

/**
 * Created by allenlai on 6/22/17.
 */
public class Board {

    private char[][] board;

    // counts to keep track if there is a winner
    private int[] rowsCount;    // X is +1 , O is -1
    private int[] columnCount;    // X is +1, O is -1
    private int diagnalCount;
    private int antiDiagnalCount;

    public Board() {
        rowsCount = new int[3];
        columnCount = new int[3];
        diagnalCount = 0;
        antiDiagnalCount = 0;
        board = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // return false for invalid input
    public boolean addToken(char type, int row, int column) {
        if (board[row][column] == '-'){
            if (row <= 2 && column <= 2) {
                if (row >= 0 && column >= 0) {
                    board[row][column] = type;
                    keepScore(type, row, column);
                    if (didAnyPlayerWin(type,row,column) != '-') {
                        System.exit(0);
                    }
                    return true;
                }
            }
        }
        return false;
    }
    public void keepScore(char type, int row, int column) {

        int toAdd = 0;
        if (type == 'X') {
            toAdd = 1;
        } else {
            toAdd = -1;
        }
        rowsCount[row] += toAdd;
        rowsCount[column] += toAdd;
        if (row == column) {
            diagnalCount++;
        }
        if (column == (3 - row - 1)) {
            antiDiagnalCount++;
        }


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
        // 1. if AI can win in the first move place it there,   no need to check if addToken will return true bc program terminates
        for (int i = 0; i < 3; i++) {       // row col
            if (rowsCount[i] == -2) {
                // add the token to the right place.
                for (int j = 0; j < 3; j++) {
                    addToken('O', i, j);
                }
            }

            if (columnCount[i] == -2) {
                // add the token to the right place.
                for (int j = 0; j < 3; j++) {
                    addToken('O', i, j);
                }
            }
        }
        if (diagnalCount == -2) {
            for (int i = 0; i < 3; i++) {
                addToken('O', i, i);
            }
        }
        if (antiDiagnalCount == -2) {
            for (int i = 0; i < 3; i++) {
                addToken('O', 3-i-1, i);
            }
        }


        // 2. AI blocks the user
        for (int i = 0; i < 3; i++) {
            if (rowsCount[i] == 2) {
                // add the token to the right place.
                for (int j = 0; j < 3; j++) {
                    if (addToken('O', i, j)) return true;
                }
            }

            if (columnCount[i] == 2) {
                // add the token to the right place.
                for (int j = 0; j < 3; j++) {
                    if (addToken('O', i, j)) return true;
                }
            }
        }
        if (diagnalCount == 2) {
            for (int i = 0; i < 3; i++) {
                if (addToken('O', i, i)) return true;
            }
        }
        if (antiDiagnalCount == 2) {
            for (int i = 0; i < 3; i++) {
                if (addToken('O', 3-i-1, i)) return true;
            }
        }

        // 3. if AI just make a move anywhere free
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    addToken('O',i,j);
                    return true;
                }
            }
        }

        return true;
    }

    public char didAnyPlayerWin (char type, int row, int column) {
        if (Math.abs(rowsCount[row]) == 3 ||
                Math.abs(columnCount[column]) == 3 ||
                Math.abs(diagnalCount) == 3  ||
                Math.abs(antiDiagnalCount) == 3)
        {
            board.toString();
            System.out.println("Player " + type + " wins!!");
            return type;
        }
        return '-';
    }

}
