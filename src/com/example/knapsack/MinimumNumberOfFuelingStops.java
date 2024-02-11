package com.example.knapsack;

import java.util.Arrays;
import java.util.stream.Stream;

// Problem Link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/minimum-number-of-refueling-stops
/* Problem Statement -
* You need to find the minimum number of refueling stops that a car needs to make to cover a distance, target.
* For simplicity, assume that the car has to travel from west to east in a straight line. There are various
* fuel stations on the way, that are represented as a 2-D array of stations, i.e., stations[i] = [di, fi],
* where di is the distance in miles of the ith  gas station from the starting position, and fi is the amount of
* fuel in liters that it stores. Initially, the car starts with k liters of fuel. The car consumes one liter of
* fuel for every mile traveled. Upon reaching a gas station, the car can stop and refuel using all the petrol stored
* at the station. In case it cannot reach the target, the program simply returns -1.
*/
public class MinimumNumberOfFuelingStops {

    public static void main(String[] args) {
        int[] target = {3, 120, 15, 570, 1360};
        int[] startFuel = {3, 10, 3, 140, 380};
        int[][][] stations = {
                {}, {{10, 60}, {20, 25}, {30, 30}, {60, 40}},
                {{2, 5}, {3, 1}, {6, 3}, {12,6 }},
                {{140, 200}, {160, 130}, {310, 200}, {330, 250}},
                {{310, 160}, {380, 620}, {700, 89}, {850, 190}, {990, 360}}
        };

        for(int i = 0; i < target.length; i++){
            System.out.print(i + 1);
            System.out.println(".\tStations: " + Arrays.deepToString(stations[i]));
            System.out.println("\tTarget fuel: " + target[i]);
            System.out.println("\tStart fuel: " + startFuel[i]);
            System.out.println("\n\tMinimum number of Refueling stops: " + minRefuelStops(target[i], startFuel[i], stations[i]));
            Stream.generate(() -> "-").limit(100).forEach(System.out::print);
            System.out.println();
        }
    }

    private static int minRefuelStops(int target, int startFuel, int[][] stations) {
        int[][] lookupTable = new int[stations.length + 1][stations.length + 1];

        for (int i = 0; i < stations.length + 1; i++) {
            lookupTable[i][0] = startFuel;
        }

        for (int i = 1; i < stations.length + 1; i++) {
            for (int j = 1; j <= i; j++) {
                // refuel at current station
                // Check if able to reach the station
                if (lookupTable[i - 1][j - 1] >= stations[i - 1][0]) {
                    lookupTable[i][j] = Math.max(lookupTable[i - 1][j], lookupTable[i - 1][j - 1] + stations[i - 1][1]);
                } else {
                    lookupTable[i][j] = lookupTable[i - 1][j];
                }
            }
        }

        // Find the minimum number of station required to reach target
        for (int i = 0; i < stations.length + 1; i++) {
            if (lookupTable[stations.length][i] >= target) {
                return i;
            }
        }
        return -1;
    }
}
