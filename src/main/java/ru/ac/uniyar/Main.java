package ru.ac.uniyar;

import java.util.Scanner;

public class Main {
    private static Scanner scanner;

    public static int getTaskModeData() {
        int mode = 0;
        System.out.println("There are 5 modes:\n1) Task to check if two segments cross\n2) Task to get distance from " +
                "point to polygon\n3) Task to check if point is inside of the triangle\n4) Task to check if point is " +
                "inside the polygon\n5) Task to get points of crossing of two circles");
        System.out.print("Enter task mode: ");
        scanner = new Scanner(System.in);
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
        return mode;
    }

    public static void main(String[] args) {
        switch (getTaskModeData()) {
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
        double x1 = getDouble("Enter first point x: ");
        double y1 = getDouble("Enter first point y: ");
        double x2 = getDouble("Enter second point x: ");
        double y2 = getDouble("Enter second point y: ");
        double x3 = getDouble("Enter third point x: ");
        double y3 = getDouble("Enter third point y: ");
        double x4 = getDouble("Enter fourth point x: ");
        double y4 = getDouble("Enter fourth point y: ");

        double k1, k2, b1, b2, xCross, yCross;

        if (x1 == x2) {
            if (x3 == x4) {
                if (x1 >= Math.min(x3, x4) && x1 <= Math.max(x3, x4) || x2 >= Math.min(x3, x4) && x2 <= Math.max(x3, x4)) {
                    System.out.println("Segments lay on one line and cross.");
                    return;
                }
            }
            k2 = (y4 - y3) / (x4 - x3);
            b2 = y3 - k2 * x3;
            xCross = x1;
            yCross = k2 * x1 + b2;
            if (xCross >= Math.min(x3, x4) && xCross <= Math.max(x3, x4) && yCross >= Math.min(y1, y2) && yCross <= Math.max(y1, y2)) {
                System.out.println("Segments cross in x = " + xCross + ", y = " + yCross);
                return;
            }
            System.out.println("Segments don't cross.");
            return;
        }

        if (x3 == x4) {
            k1 = (y2 - y1) / (x2 - x1);
            b1 = y1 - k1 * x1;
            xCross = x3;
            yCross = k1 * x3 + b1;
            if (xCross >= Math.min(x1, x2) && xCross <= Math.max(x1, x2) && yCross >= Math.min(y3, y4) && yCross <= Math.max(y3, y4)) {
                System.out.println("Segments cross in x = " + xCross + ", y = " + yCross);
                return;
            }
            System.out.println("Segments don't cross.");
            return;
        }

        k1 = (y2 - y1) / (x2 - x1);
        k2 = (y4 - y3) / (x4 - x3);

        if (k1 == k2) {
            if (x1 >= Math.min(x3, x4) && x1 <= Math.max(x3, x4) || x2 >= Math.min(x3, x4) && x2 <= Math.max(x3, x4)) {
                System.out.println("Segments lay on one line and cross.");
                return;
            }
            System.out.println("Segments are parallel.");
            return;
        }

        b1 = y1 - k1 * x1;
        b2 = y3 - k2 * x3;

        xCross = (b2 - b1) / (k1 - k2);
        yCross = k1 * xCross + b1;

        if (xCross >= Math.min(x1, x2) && xCross <= Math.max(x1, x2) && yCross >= Math.min(y1, y2) && yCross <= Math.max(y1, y2))
            if (xCross >= Math.min(x3, x4) && xCross <= Math.max(x3, x4) && yCross >= Math.min(y3, y4) && yCross <= Math.max(y3, y4)) {
                System.out.println("Segments cross in x = " + xCross + ", y = " + yCross);
                return;
            }

        System.out.println("Segments don't cross.");
    }

    public static void getDistanceFromPointToPolygon() {
        return;
    }

    public static void checkIfPointLaysInsideTriangle() {
        double x1, x2, x3, y1, y2, y3, x, y;
        x1 = getDouble("Enter first point of triangle x: ");
        y1 = getDouble("Enter first point of triangle y: ");
        x2 = getDouble("Enter second point of triangle x: ");
        y2 = getDouble("Enter second point of triangle y: ");
        x3 = getDouble("Enter third point of triangle x: ");
        y3 = getDouble("Enter third point of triangle y: ");

        x = getDouble("Enter point's x: ");
        y = getDouble("Enter point's y: ");

        if (checkIfPointsLayOnTheSameSide(x1, y1, x2, y2, x, y, x3, y3)
                && checkIfPointsLayOnTheSameSide(x2, y2, x3, y3, x, y, x1, y1)
                && checkIfPointsLayOnTheSameSide(x1, y1, x3, y3, x, y, x2, y2)) {
            System.out.println("Point lays inside the triangle.");
            return;
        }
        System.out.println("Point does not lay inside the triangle.");
    }

    private static boolean checkIfPointsLayOnTheSameSide(double startX, double startY, double endX, double endY,
                                                         double pointX, double pointY, double checkX, double checkY) {
        double k, b;
        if (startX == endX) {
            return pointX <= startX && checkX < startX || pointX >= startX && checkX > startX || pointX == startX;
        } else {
            k = (endY - startY) / (endX - startX);
            b = startY - k * startX;
            if (startY == endY) return pointY <= startY && checkY < startY || pointY >= startY && checkY > startY
                    || pointY == startY;
            double a = pointY - k * pointX - b;
            double c = checkY - k * checkX - b;
            return a <= 0 && c < 0 || a >= 0 && c > 0 || a == 0;
        }
    }

    public static void checkIfPointLaysInsidePolygon() {
        return;
    }

    public static void getPointsOfCrossingOfTwoCircles() {
        return;
    }

    private static double getDouble(String message) {
        double toGet = Double.MAX_VALUE;
        System.out.print(message);
        while (toGet == Double.MAX_VALUE) {
            try {
                toGet = Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("You should enter a number.");
                System.out.print(message);
            }
        }
        return toGet;
    }
}