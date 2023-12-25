package com.example.knapsack;

import java.util.Arrays;

// Problem link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/target-sum
/* Problem Statement
* Given an array of positive integers arr and a target T, build an expression using these numbers by
* inserting a [+] or a [-] before each integer, and evaluating this expression.
* Find the total number of different expressions that evaluate to T.
*/
public class TargetSum {

    public static void main(String[] args) {
        int[][] arrs = {{1}, {3, 3, 3, 3}, {2, 3, 4, 6, 8, 12}, {2, 2, 2, 4, 4, 4, 8, 8, 8}};
        int[] targets = {1, 6, 15, 18};

        for (int i=0; i<arrs.length; ++i){
            String a =  "[" + arrs[i][0];
            for (int j=1; j<arrs[i].length; ++j)
                a = a + ", " + arrs[i][j];
            a += "]";
            System.out.println(i + 1 + ".\t Input array: " + a);
            System.out.println("\t Target: " + targets[i]);
            System.out.println("\t Number of ways in which we can find the target sum: " + findTargetSumWays(arrs[i], targets[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    private static int findTargetSumWays(int[] array, int T) {
        int total = 0;
        for (int num : array) {
            total += num;
        }

        // If the target can't be generated using the given numbers
        if (total < Math.abs(T)) {
            return 0;
        }

        // Initialize a lookup table
        int[][] dp = new int[array.length][2 * total + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return findTargetSumWaysRec(array, T, total, 0, 0, dp);
    }

    private static int findTargetSumWaysRec(int[] array, int T, int total, int currentIndex, int sum, int[][] dp) {
        if (array.length == currentIndex) {
            if (sum == T) {
                return 1;
            }
            return 0;
        }

        if (dp[currentIndex][total + sum] != -1) {
            return dp[currentIndex][total + sum];
        }

        // Calculate both sub-problems and save the results in the memory
        int value = findTargetSumWaysRec(array, T, total, currentIndex + 1, sum - array[currentIndex], dp) +
                findTargetSumWaysRec(array, T, total, currentIndex + 1, sum + array[currentIndex], dp);

        dp[currentIndex][sum + total] = value;
        return value;
    }

}
