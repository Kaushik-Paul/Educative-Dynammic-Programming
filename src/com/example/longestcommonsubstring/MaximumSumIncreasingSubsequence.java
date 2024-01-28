package com.example.longestcommonsubstring;

import java.util.Arrays;

// Problem link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/maximum-sum-increasing-subsequence
/* Problem Statement -
* Given an array of integers nums, identify the increasing subsequence whose sum is the highest among all
* increasing subsequences. Return the sum. Note that an increasing subsequence of an array is defined as a
* subsequence whose elements are sorted in strictly increasing order. Let’s say we have an integer
* array [4, 1, 2, 6, 10, 1, 12], and we want to get the maximum sum increasing subsequence in this array.
* There are multiple increasing subsequences with different sums, [1, 2, 6, 10, 12], [2, 6, 10, 12], [6, 10, 12],
* [4, 6, 10, 12], and so on. The maximum sum increasing subsequence (MSIS), in this case, is [4, 6, 10, 12],
* with sum 32.
*/
public class MaximumSumIncreasingSubsequence {

    public static void main(String[] args) {
        int[][] lists = {
                { 4, 1, 2, 6, 10, 1, 12 },
                { -4, 10, 3, 7, 15 },
                { 4, 2, 3, 6, 10, 1, 12 },
                { 3, 2, 6, 4, 5, 1 },
                { 3, 2 },
                { 1, 2, 3, 4, 5, 6, 7 },
                { 1, 101, 2, 3, 100, 4, 5 },
                { 1, 5, 2, 3, 9, 5 }
        };

        for (int i = 0; i < lists.length; i++) {
            System.out.print((i + 1) + ". Input array: ");
            System.out.print(Arrays.toString(lists[i]));
            System.out.println("\nMaximum sum of increasing subsequence is: " + msisLength(lists[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    private static int msisLength(int[] inputs) {
        int[][] lookupTable = new int[inputs.length + 1][inputs.length + 2];

        for (int[] row : lookupTable) {
            Arrays.fill(row, -1);
        }

        return msisLengthRec(inputs, 0, -1, lookupTable, 0);
    }

    private static int msisLengthRec(int[] inputs, int currentIdx, int previousIdx, int[][] lookupTable, int sum) {
        if (currentIdx == inputs.length) {
            return sum;
        }

        if (lookupTable[currentIdx][previousIdx + 1] != -1) {
            return lookupTable[currentIdx][previousIdx + 1];
        }

        int maxSum = msisLengthRec(inputs, currentIdx + 1, previousIdx, lookupTable, sum);
        if (previousIdx < 0 || inputs[previousIdx] < inputs[currentIdx]) {
            maxSum = Math.max(maxSum, sum + msisLengthRec(inputs, currentIdx + 1, currentIdx, lookupTable, inputs[currentIdx]));
        }

        lookupTable[currentIdx][previousIdx + 1] = maxSum;
        return lookupTable[currentIdx][previousIdx + 1];
    }
}
