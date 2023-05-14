package ru.ac.uniyar;

import java.util.Scanner;

public class Main {
    private static int mode;

    public static void getTaskModeData() {
        System.out.println("There are 5 modes:\n1) Task to check if two segments cross\n2) Task to get distance from " +
                "point to polygon\n3) Task to check if point is inside of the triangle\n4) Task to check if point is " +
                "inside the polygon\n5) Task to get points of crossing of two circles");
        System.out.print("Enter task mode: ");
        Scanner scanner = new Scanner(System.in);
        while (mode == 0) {
            try {
                mode = Integer.parseInt(scanner.nextLine());
                if (mode < 1 || mode > 5) {
                    System.out.println("Mode should be an integer between 1 and 5");
                    System.out.print("Enter task mode: ");
                    mode = 0;
                }
            } catch (Exception e) {
                System.out.println("Mode should be an integer between 1 and 5");
                System.out.print("Enter task mode: ");
            }
        }
    }

    public static void main(String[] args) {
        getTaskModeData();
        switch (mode) {
            case 1:
                checkIfSegmentsCross();
            case 2:
                getDistanceFromPointToPolygon();
            case 3:
                checkIfPointLaysInsideTriangle();
            case 4:
                checkIfPointLaysInsidePolygon();
            default:
                getPointsOfCrossingOfTwoCircles();
        }
    }

    public static void checkIfSegmentsCross() {
        return;
    }

    public static void getDistanceFromPointToPolygon() {
        return;
    }

    public static void checkIfPointLaysInsideTriangle() {
        return;
    }

    public static void checkIfPointLaysInsidePolygon() {
        return;
    }

    public static void getPointsOfCrossingOfTwoCircles() {
        return;
    }
}