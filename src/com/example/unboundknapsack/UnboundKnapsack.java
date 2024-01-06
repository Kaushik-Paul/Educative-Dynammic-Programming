package com.example.unboundknapsack;

import java.util.Arrays;
import java.util.stream.Stream;


// Problem link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/solving-the-unbounded-knapsack-problem
/*
* Problem statement - Suppose you have a list of weights and corresponding values for n items.
* Each item will have a weight and a certain value associated with it. You have a knapsack that can carry items
* up to a specific maximum weight, known as the capacity of the knapsack.
* You want to maximize the sum of values of the items in your knapsack while ensuring that the sum of the weights
* of the items remains less than or equal to the knapsack’s capacity. If all the combinations exceed the
* given knapsack’s capacity, return 0.
*/
public class UnboundKnapsack {

    public static void main(String[] args) {
        int[][] weights = {{2, 4, 6}, {1, 2, 3, 5}, {4}, {2}, {3, 6, 10, 7, 2}, {3, 6, 10, 7, 2, 12, 15, 10, 13, 20}};
        int[][] values = {{5, 11, 13}, {1, 5, 4, 8}, {2}, {3}, {12, 10, 15, 17, 13}, {12, 10, 15, 17, 13, 12, 30, 15, 18, 20}};
        int[] capacity = {10, 6, 3, 3, 10, 20};

        for (int i = 0; i < values.length; i++) {
            System.out.print(i + 1);
            System.out.println(". We have a knapsack of capacity " + capacity[i] + " and we are given the following list of item values and weights:");
            Stream.generate(() -> "-").limit(30).forEach(System.out::print);
            System.out.println();
            System.out.println("Weights   |     Values");
            Stream.generate(() -> "-").limit(30).forEach(System.out::print);
            System.out.println();
            for (int j = 0; j < values[i].length; ++j)
                System.out.printf("%-10d|%6d\n", weights[i][j], values[i][j]);
            int result = unboundedKnapsack(weights[i], values[i], capacity[i]);
            System.out.println("\nThe maximum we can earn is: " + result);
            Stream.generate(() -> "-").limit(100).forEach(System.out::print);
            System.out.println("\n");
        }
    }

    private static int unboundedKnapsack(int[] weights, int[] values, int capacity) {

        int[][] dp = new int[weights.length + 1][capacity + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return unboundedKnapsackRec(weights, values, 0, capacity, dp);
    }

    private static int unboundedKnapsackRec(int[] weights, int[] values, int currentIdx, int capacity, int[][] dp) {
        // Base cases
        if (capacity <= 0 || currentIdx >= weights.length) {
            return 0;
        }

        // If value already present then return that value
        if (dp[currentIdx][capacity] != -1) {
            return dp[currentIdx][capacity];
        }

        // Calculate by adding or not adding the item
        if (weights[currentIdx] <= capacity) {

            int taken = values[currentIdx] + unboundedKnapsackRec(weights, values, currentIdx, capacity - weights[currentIdx], dp);
            int notTaken = unboundedKnapsackRec(weights, values, currentIdx + 1, capacity, dp);

            dp[currentIdx][capacity] = Math.max(taken, notTaken);
        } else {
            dp[currentIdx][capacity] = unboundedKnapsackRec(weights, values, currentIdx + 1, capacity, dp);
        }
        return dp[currentIdx][capacity];
    }
}
