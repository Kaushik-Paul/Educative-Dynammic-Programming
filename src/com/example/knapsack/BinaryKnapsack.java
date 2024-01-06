package com.example.knapsack;

import java.util.Arrays;

// Problem link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/solving-the-0-1-knapsack-problem

/* Problem statement -
* Suppose you have a list of weights and corresponding values for n items. You have a knapsack that can carry items up
* to a specific maximum weight, known as the capacity of the knapsack.
* You want to maximize the sum of values of the items in your knapsack while ensuring that
* the sum of the weights of the items remains less than or equal to the knapsack’s capacity.
* If all the combinations exceed the given knapsack’s capacity, then return 0
*/
public class BinaryKnapsack {

    public static void main(String[] args) {
        int[][] weights = {
                {1, 2, 3, 5},
                {4},
                {2},
                {3, 6, 10, 7, 2},
                {3, 6, 10, 7, 2, 12, 15, 10, 13, 20}
        };

        int[][] values = {
                {1, 5, 4, 8},
                {2},
                {3},
                {12, 10, 15, 17, 13},
                {12, 10, 15, 17, 13, 12, 30, 15, 18, 20}
        };

        int[] capacity = {6, 3, 3, 10, 20};

        for (int i = 0; i < values.length; ++i) {
            System.out.print(i + 1);
            System.out.println(". We have a knapsack of capacity " + capacity[i] + " and we are given the following list of item values and weights:");
            System.out.println(new String(new char[30]).replace('\0', '-'));
            System.out.println("Weights   |     Values");
            System.out.println(new String(new char[30]).replace('\0', '-'));
            for (int j = 0; j < values[i].length; ++j)
                System.out.printf("%-10d|%6d\n", weights[i][j], values[i][j]);
            int result = findKnapsack(capacity[i], weights[i], values[i]);
            System.out.println("\nThe maximum we can earn is: " + result);
            System.out.println(new String(new char[100]).replace('\0', '-'));
            System.out.println();
        }
    }

    private static int findKnapsack(int capacity, int[] weights, int[] values) {
        int[][] dp = new int[weights.length + 1][capacity + 1];

        for (int[] num : dp) {
            Arrays.fill(num, -1);
        }

        return findKnapsackRec(capacity, weights, values, 0, dp);
    }

    private static int findKnapsackRec(int capacity, int[] weights, int[] values, int currentIdx, int[][] dp) {

        // Base cases
        if (capacity <= 0 || currentIdx >= weights.length) {
            return 0;
        }

        // Return value if it is already present
        if (dp[currentIdx][capacity] != -1) {
            return dp[currentIdx][capacity];
        }

        // Solve and store the result
        if (weights[currentIdx] <= capacity) {

            // Either take the value or leave the value
            dp[currentIdx][capacity] = Math.max(values[currentIdx] + findKnapsackRec(capacity - weights[currentIdx], weights, values, currentIdx + 1, dp),
                    findKnapsackRec(capacity, weights, values, currentIdx + 1, dp));
            return dp[currentIdx][capacity];
        }
        dp[currentIdx][capacity] = findKnapsackRec(capacity, weights, values, currentIdx + 1, dp);
        return dp[currentIdx][capacity];
    }
}
