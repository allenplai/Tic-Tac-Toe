package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        Board newBoard = new Board();

        while (!newBoard.isBoardFull()) {
            System.out.println(newBoard.toString());
            boolean validIntput = false;

            System.out.println("Enter in row number: (0-2)");
            int row=sc.nextInt();
            System.out.println("Enter in column number: (0-2)");
            int column=sc.nextInt();
            validIntput = newBoard.addToken('X',row,column);

            while (!validIntput) {
                System.out.println("Enter in row number: (0-2)");
                int row1=sc.nextInt();
                System.out.println("Enter in column number: (0-2)");
                int column1=sc.nextInt();
                validIntput = newBoard.addToken('X',row1,column1);
                System.out.println("Invalid input, try again");

            }
            System.out.println(newBoard.toString());
            System.out.println("AI makes move:");

            newBoard.aiMakeMove();
        }
        // determine who won



    }
}
