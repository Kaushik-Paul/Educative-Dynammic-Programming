package com.example.unboundknapsack;

import java.util.Arrays;

// Problem link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/maximum-ribbon-cut
/* Problem statement - Given a ribbon of length n and a set of possible sizes, cut the ribbon in sizes such
that n is achieved with the maximum number of pieces. You have to return the maximum
number of pieces that can make up n by using any combination of the available sizes.
If the n can’t be made up, return -1, and if n is 0, return 0.
*/
public class MaximumRibbonCut {

    public static void main(String[] args) {
        int[][] sizes = { { 1, 2, 3 }, { 3, 2, 5 }, { 2, 3 }, { 3, 5, 7 }, { 5, 3 } };
        int[] n = { 5, 5, 7, 13, 7 };

        // Let's uncomment the below lines and check the effect of dynamic programming using memoization

        // sizes = Arrays.copyOf(sizes, sizes.length + 1);
        // sizes[sizes.length - 1] = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        // n = Arrays.copyOf(n, n.length + 1);
        // n[n.length - 1] = 1500;

        for (int i = 0; i < sizes.length; i++) {
            System.out.print((i + 1) + ".\tThe maximum number of sizes that can make up the n of ");
            System.out.print(n[i] + " from ");
            System.out.print(Arrays.toString(sizes[i]));
            System.out.println(" is: " + countRibbonPieces(n[i], sizes[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    private static int countRibbonPieces(int ribbonLength, int[] sizes) {
        int[][] dp = new int[sizes.length + 1][ribbonLength + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int result = countRibbonPiecesHelper(sizes, ribbonLength, 0, dp);

        return result;

    }

    private static int countRibbonPiecesHelper(int[] sizes, int ribbonLength, int currentIdx, int[][] dp) {
        // Base cases
        if (ribbonLength == 0) {
            return 0;
        }

        if (currentIdx >= sizes.length) {
            return -1;
        }

        // If value is already calculated then return that value
        if (dp[currentIdx][ribbonLength] != -1) {
            return dp[currentIdx][ribbonLength];
        }

        int taken = -1;
        if (sizes[currentIdx] <= ribbonLength) {
            // Calculate by including and excluding current size
            int res = countRibbonPiecesHelper(sizes, ribbonLength - sizes[currentIdx], currentIdx, dp);

            if (res != -1) {
                // recursive call after excluding the ribbon length at the index
                taken = res + 1;
            }
        }
        int notTaken = countRibbonPiecesHelper(sizes, ribbonLength, currentIdx + 1, dp);
        dp[currentIdx][ribbonLength] = Math.max(taken, notTaken);
        return dp[currentIdx][ribbonLength];
    }
}
