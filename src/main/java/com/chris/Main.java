package com.chris;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Console program to run simple room calculator based on the challenge:
 *
 *      The challenge is to write a program that takes as input the dimensions of a room and outputs the following:
 *          Area of the floor
 *          Amount of paint required to paint the walls
 *          Volume of the room
 *      Consider the use of unit tests where appropriate and ensure your code is clean and readable.
 *
 * @author Chris Blythe
 * @version 1.0
 */

public class Main {

    public static void main(String[] args) {

        System.out.println("Room Calculator.\n");

        while (true) {
            // Capture room dimensions and create room
            Room calcRoom = new Room(readDimensionsInput());
            // Print formatted room properties
            calcRoom.printRoomProperties();

            // Offer new room calc or exit
            System.out.println("\nHit 'Enter' to calculate a new room, or 'x' to exit...");
            Scanner input = new Scanner(System.in);
            String inputLine = input.nextLine();
            if (inputLine.isEmpty()) {
                // continue to new room
            }else if (inputLine.trim().charAt(0) == 'x') {
                // Exit command
                System.exit(0);
            }
        }
    }

    // Read 3 numbers from console, validate input and return array of room dimensions
    private static double[] readDimensionsInput() {

        double[] dimensions = new double[3];
        Scanner input = new Scanner(System.in);
        int inputCount = 0;
        String breakCode = "";

        System.out.println("Enter room dimensions in metres - Width x Length x Height, separated by spaces or 'Enter':");
        System.out.println("e.g. > 3.75 4.42 2.20");

        // Capture room dimensions
        while (inputCount < 3) {
            double dimension;
            // Validate inputs
            if (input.hasNextFloat()) {
                dimension = input.nextFloat();
                if (dimension <=0.009) {
                    // If not 1cm or more, break to restart input sequence
                    breakCode = "invalid";
                } else {
                    // else add to dimensions
                    dimensions[inputCount++] = dimension;
                }
            } else {
                // Non double entry
                if (input.next().trim().charAt(0) == 'x') {
                    // Exit command issued
                    breakCode = "exit";
                } else {
                    // If not double, break to restart input sequence
                    breakCode = "invalid";
                }
            }

            // Behaviour for non-double input
            switch (breakCode) {
                case "invalid":
                    System.out.println("Invalid input - please restart and enter positive numbers 'WW LL HH', or 'x' to exit:");
                    System.out.println("e.g. > 3.75 4.42 2.20");
                    // Reset array and scanner
                    Arrays.fill(dimensions, 0);
                    inputCount = 0;
                    breakCode = "";
                    input.nextLine();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }

        return dimensions;
    }

}



/*
The challenge is to write a program that takes as input the dimensions of a room and outputs the following:

    Area of the floor
    Amount of paint required to paint the walls
    Volume of the room

Consider the use of unit tests where appropriate and ensure your code is clean and readable.
*/
