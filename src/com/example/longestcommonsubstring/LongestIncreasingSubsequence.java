package com.example.longestcommonsubstring;

import java.util.Arrays;

// Problem link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/longest-increasing-subsequence
/* Problem Statement -
* The Longest Increasing Subsequence (LIS) is the longest subsequence from a given array in which the subsequence
* elements are sorted in strictly increasing order. Given an integer array, nums, find the length of the LIS in
* this array. Let’s say we have an integer array [6, 9, 8, 2, 3, 5, 1, 4, 7], and we want to get the longest increasing
* subsequence in this array. There are multiple increasing subsequences of different lengths, such as [6, 9], [6, 8],
* [2, 3, 5], [2, 3, 5, 7], and so on. The longest increasing subsequences, in this case, are [2, 3, 4, 7] and
* [2, 3, 5, 7], both of length 4.
*/
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[][] arrays = {
                {10, 9, 2, 5, 3, 7, 101, 18},
                {7, 7, 7, 7, 7, 7, 7},
                {0, 1, 0, 3, 2, 3},
                {3, 2},
                {6, 9, 8, 2, 3, 5, 1, 4, 7},
                {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15},
                {9, 2, 5, 3, 6, 14, 11, 7, 9, 5, 13, 3, 15, 0, 8, 4, 1, 9, 5, 13, 3, 11, 7, 15, 0, 10, 6, 14, 9, 2, 5, 3, 2, 10, 6, 10, 6, 5, 13, 3, 11, 7, 15, 3, 11, 7, 15}
        };

        for (int i = 0; i < arrays.length; i++) {
            System.out.println((i + 1) + ".\tInput array: "+ Arrays.toString(arrays[i]));
            System.out.println("\tLength of LIS is: " + lisLength(arrays[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    private static int lisLength(int[] inputs) {
        int[][] lookupTable = new int[inputs.length + 1][inputs.length + 2];

        for (int[] row : lookupTable) {
            Arrays.fill(row, -1);
        }
        return lisLengthHelper(inputs, 0, -1, lookupTable);
    }

    private static int lisLengthHelper(int[] inputs, int currentIdx, int previousIdx, int[][] lookupTable) {
        if (currentIdx == inputs.length) {
            return 0;
        }

        if (lookupTable[currentIdx][previousIdx + 1] != -1) {
            return lookupTable[currentIdx][previousIdx + 1];
        }

        int length = lisLengthHelper(inputs, currentIdx + 1, previousIdx, lookupTable);
        if (previousIdx < 0 || inputs[previousIdx] < inputs[currentIdx]) {
            length = Math.max(length, 1 + lisLengthHelper(inputs, currentIdx + 1,  currentIdx, lookupTable));
        }

        lookupTable[currentIdx][previousIdx + 1] = length;
        return lookupTable[currentIdx][previousIdx + 1];
    }
}
