package com.company;

import java.util.Scanner;

public class Main {


    // 3 x 3 Tic Tac Toe. User is always 'X' and starts first

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Board newBoard = new Board();


        while (!newBoard.isBoardFull()) {
            System.out.println(newBoard.toString());
            boolean validIntput = false;

            // 1. get user input to start playing Tic-Tac-Toe
            System.out.println("Enter in row number: (0-2)");
            int row=sc.nextInt();
            System.out.println("Enter in column number: (0-2)");
            int column=sc.nextInt();
            validIntput = newBoard.addToken('X',row,column);

            // 2. if input is invalid, get user to try again
            while (!validIntput) {
                System.out.println("Enter in row number: (0-2)");
                int row1=sc.nextInt();
                System.out.println("Enter in column number: (0-2)");
                int column1=sc.nextInt();
                validIntput = newBoard.addToken('X',row1,column1);
                System.out.println("Invalid input, try again");
            }
            // 3. Computers turn to make a move
            System.out.println(newBoard.toString());
            System.out.println("AI makes move:");
            newBoard.aiMakeMove();

        }


    }
}
