package com.company;

/**
 * Created by allenlai on 6/22/17.
 */
public class Board {

    private char[][] board;


    private int[] rowsCount;    // X is +1 , O is -1
    private int[] columnCount;    // X is +1, O is -1
    private int diagnal;
    private int antiDiagnal;

    public Board() {
        rowsCount = new int[3];
        columnCount = new int[3];
        diagnal = 0;
        antiDiagnal = 0;
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

                    keepScore(type, row, column);
                    didPlayerWin(type,row,column);
                    return true;
                }
            }
        }
        return false;
    }
    public void keepScore(char type, int row, int column) {
        // keep score
        int toAdd = 0;
        if (type == 'X') {
            toAdd = 1;
        } else {
            toAdd = -1;
        }
        rowsCount[row] += toAdd;
        rowsCount[column] += toAdd;
        if (row == column) {
            diagnal++;
        }
        if (column == (3 - row - 1)) {
            antiDiagnal++;
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

    public char didPlayerWin (char type, int row, int column) {
        if (Math.abs(rowsCount[row]) == 3 ||
                Math.abs(columnCount[column]) == 3 ||
                Math.abs(diagnal) == 3  ||
                Math.abs(antiDiagnal) == 3)
        {
            System.out.println(type + " player won!!!");
            return type;
        }
        return '-';
    }

}
