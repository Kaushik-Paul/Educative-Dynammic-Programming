package com.example.unboundknapsack;

import java.util.Arrays;

// Problem statement - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/minimum-coin-change
/* Problem statement -
* You’re given an integer total and a list of integers called coins. The integers inside the coins represent
* the coin denominations, and total is the total amount of money.You have to return the minimum number
* of coins that can make up the total amount by using any combination of the available coins.
* If the amount can’t be made up, return -1. If the total amount is 0, return 0.
*/
public class MinimumCoinChange {

    public static void main(String[] args) {
        int[][] coins = {
                { 1, 3, 4, 5 },
                { 1, 2, 3 },
                { 2, 3, 7 },
                { 1, 3, 9 },
                { 1, 4, 6, 9 }
        };
        int[] total = { 7, 5, 1, 4, 11 };

        for (int i = 0; i < total.length; i++)
        {
            System.out.println(i + 1 + ".\tThe minimum number of coins required to find " + total[i] + " from " + Arrays.toString(coins[i]) + " is: "+ coinChange(coins[i], total[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    private static int coinChange(int[] coins, int total) {
        int[] minimumChange = new int[total + 1];

        Arrays.fill(minimumChange, -1);

        return calculateMinimumCoins(coins, total, minimumChange);
    }

    private static int calculateMinimumCoins(int[] coins, int total, int[] minimumChange) {
        // Base cases
        if (total < 0) {
            return -1;
        }

        if (total == 0) {
            return 0;
        }

        // If already calculated then return that value
        if (minimumChange[total] != -1) {
            return minimumChange[total];
        }

        int minimum = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int result = calculateMinimumCoins(coins, total - coins[i], minimumChange);
            if (result >= 0 && minimum > result) {
                minimum = 1 + result;
            }
        }

        if (minimum != Integer.MAX_VALUE) {
            minimumChange[total] = minimum;
        }
        return minimumChange[total];
    }
}
