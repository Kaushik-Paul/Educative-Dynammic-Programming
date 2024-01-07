package com.example.unboundknapsack;

import java.util.Arrays;
import java.util.stream.Stream;


// Problem link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/coin-change-ii
/* Problem Statement -
* Suppose you are given a list of coins and a certain amount of money. Each coin in the
* list is unique and of a different denomination. You are required to count the number of ways
* the provided coins can sum up to represent the given amount. If there is no possible way to
* represent that amount, then return 0.
*/

// TODO: Make the solution compatible with memoization
public class CoinChange2 {

    public static void main(String[] args) {
        int[][] coinsLists = {{7}, {1, 2, 5}, {10}, {49, 233, 96, 132, 188},
                {310, 278, 99, 326, 5, 575, 569, 15, 141, 54},
                {2823, 4551, 1750, 49, 3256, 405, 380, 4785, 3893, 874},
                {17, 1422, 30, 1153, 1275}, {1460}, {9, 10, 11}};

        int[] amounts = {9, 5, 10, 225, 350, 3200, 700, 2000, 0};

        for (int i = 0; i<coinsLists.length; i++) {
            long numWays = countWays(coinsLists[i], amounts[i]);
            System.out.println(i + 1 + ".\tcoins: " + printArrays(coinsLists[i]) +
                    "\n\tamount: " + amounts[i] +
                    "\n\n\tNumber of Ways: " + numWays);

            Stream.generate(() -> "-").limit(100).forEach(System.out::print);
            System.out.println(" ");
        }
    }

    // Helper function to print an array
    public static String printArrays(int[] arr) {
        StringBuilder res = new StringBuilder("[");
        for (int i : arr) {
            res.append(i).append(", ");
        }

        return res.substring(0, res.length() - 2) + "]";
    }

    public static long countWays(int[] coins, int amount) {
        int max = Arrays.stream(coins).max().getAsInt();
        return countWaysRec(coins, amount, max);
    }

    private static int countWaysRec(int[] coins, int amount, int max) {
        // Base cases
        if (amount == 0) {
            return 1;
        }
        if (amount < 0) {
            return 0;
        }

        int ways = 0;
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= max && coins[i] <= amount) {
                ways += countWaysRec(coins, amount - coins[i], coins[i]);
            }
        }

        return ways;
    }
}
