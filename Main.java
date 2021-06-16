package com.company;  //Use for IntelliJ IDE.

import java.util.Random;
import java.util.Scanner;

/**
 * @author Adam Johnson
 * This program is a TicTacToe game that allows user to play against the computer displaying the board in a
 * 3x3 array.
 */
public class Main {

    static Scanner scnr = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        boolean nextGame = true;
        while (nextGame) {            //Game runs until user exits or does not play again.
            start();
            System.out.println("Would you like to play again? y/n ");
            String next = scnr.nextLine();
            nextGame = (next.charAt(0) == 'y') || (next.charAt(0) == 'Y');  //Game loop continues if user chooses
        }
        System.out.println("Goodbye!");                                     //Game loop exits
    }


    public static void start() {
        System.out.println("\nWelcome to Adam's TicTacToe project!\n");
        char[][] map = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};  //Creates map array
        printMap(map);    //Prints the map

        while (true) {                   //Loop to check for win or tie after each move.

            getUserInput(map);            //Checks for user input on the map
            printMap(map);                 //Print the map with user input on it.
            if (checkWin('X', map)) {
                System.out.println("Congratulations, you won!");
                break;
            }
            if (checkWin(map)) {
                System.out.println("It's a draw!");
                break;
            }

            System.out.println("Computer move:");
            getComputerInput(map);
            printMap(map);
            if (checkWin('O', map)) {
                System.out.println("Computer won! Better luck next time!");
                break;
            }
            if (checkWin(map)) {
                System.out.println("It's a draw!");
                break;
            }
            System.out.println("New round!");
        }
    }

    public static void getUserInput(char[][] map) {
        boolean valid = false;
        int row = 0;
        int col = 0;

        while (!valid) {
            System.out.print("Please enter your move (letter and number without a space): ");
            String move = scnr.nextLine();
            if (move.length() == 2) {
                row = (int) move.charAt(0) - 97;
                col = (int) move.charAt(1) - 49;

                if ((row >= 0 && row < 3) && (col >= 0 && col < map[0].length)) {
                    if (map[row][col] == 0 || map[row][col] == ' ') {
                        map[row][col] = 'X';
                        valid = true;
                    } else {
                        System.out.println("This field is already taken, choose another!");
                    }
                } else {
                    System.out.println("This field is invalid!");
                }
            } else {
                System.out.println("Please only enter the right field (a1,...) for the play!");
            }
        }
    }

    /**
     * Get computer to randomly input in empty location in the 2D character array
     *
     * @param map- 3x3 array
     */
    public static void getComputerInput(char[][] map) {
        int row = 0;
        int col = 0;
        do {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
            if (map[row][col] == 0 || map[row][col] == ' ') {
                map[row][col] = 'O';
                break;
            }
        } while (true);
    }

    /**
     * If there is NO empty spot left, it's a tie
     *
     * @param map-3x3 array
     * @return true if tie, false if not
     */
    public static boolean checkWin(char[][] map) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == 0 || map[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Return the winning status. Win at:
     * playerMark in a whole row;
     * playerMark in a whole column;
     * playerMark form diagonal line;
     *
     * @param playerMark
     * @param map-3x3 array
     * @return true if won
     */
    public static boolean checkWin(char playerMark, char[][] map) {
        int win = 3;
        int sum = 0;

        //Check for win in a row
        for (int i = 0; i < 3; i++) {
            sum = 0;
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == playerMark) {
                    sum++;
                }
            }
            if (sum == win)
                return true;
        }
        //check for win in a column

        for (int i = 0; i < 3; i++) {
            sum = 0;
            for (int j = 0; j < map[i].length; j++) {
                if (map[j][i] == playerMark) {
                    sum++;
                }
            }
            if (sum == win)//if any row wins
                return true;
        }

        // Check for win from from top-left to bottom-right
        sum = 0;
        for (int i = 0, j = 0; i < 3 && j < map[0].length; i++, j++) {
            if (i == j && map[i][j] == playerMark)
                sum++;
        }
        if (sum == win)
            return true;

    //Check for win from top-right to bottom-left

    sum = 0;
        for (int i = 2, j = 0; i >= 0 && j < map[0].length; i--, j++) {
        if (map[i][j] == playerMark)
            sum++;
    }
        return sum == win;

}

    public static void printMap(char[][] map) {

        System.out.println("     1     2     3");
        System.out.println("   __________________");
        System.out.println("a |  " + map[0][0] + "  |  " + map[0][1] + "  |  " + map[0][2] + "  |");
        System.out.println("  |-----------------|");
        System.out.println("b |  " + map[1][0] + "  |  " + map[1][1] + "  |  " + map[1][2] + "  |");
        System.out.println("  |-----------------|");
        System.out.println("c |  " + map[2][0] + "  |  " + map[2][1] + "  |  " + map[2][2] + "  |");
        System.out.println("   ------------------");

    }

}



