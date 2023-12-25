package com.example.knapsack;

import java.util.Arrays;

// Problem link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/count-of-subset-sum
// Problem Statement
/*
* Given a set of positive numbers nums and a value targetSum, count the total number of subsets of
* the given set whose sum is equal to the targetSum.
*/
public class CountOfSubSetProblem {
    public static void main(String[] args) {
        int[][] inputNums = {
                {1},
                {11, 33},
                {4, 2, 3},
                {1, 4, 2, 3},
                {1, 2, 7, 4, 5},
                {1, 2, 3, 7}
        };
        int[] targetSums = {10, 11, 6, 4, 9, 6};

        int[] newTargetSums = Arrays.copyOf(targetSums, targetSums.length + 1);
        newTargetSums[targetSums.length] = 2593;
        targetSums = newTargetSums;

        int index = 0;
        for (int[] input : inputNums) {
            System.out.println((index + 1) + ".\tnums = " + Arrays.toString(input));
            System.out.println("\ttarget sum = " + targetSums[index]);
            System.out.println("\tTotal number of subsets whose sum is equal to the target sum = " + countSubsetSum(input, targetSums[index]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
            index++;
        }
    }

    private static int countSubsetSum(int[] input, int targetSum) {

        int[][] lookupTable = new int[input.length][targetSum + 1];

        // Initialize the array
        for (int[] num : lookupTable) {
            Arrays.fill(num, -1);
        }

        return countSubsetSumRec(input, targetSum, 0, lookupTable);
    }

    private static int countSubsetSumRec(int[] input, int requiredSum, int currentIndex, int[][] lookupTable) {
        if (requiredSum == 0) {
            return 1;
        }

        if (currentIndex >= input.length) {
            return 0;
        }

        if (lookupTable[currentIndex][requiredSum] == -1) {
            int sum1 = 0;
            if (input[currentIndex] <= requiredSum) {
                // Include the current index
                sum1 = countSubsetSumRec(input, requiredSum - input[currentIndex], currentIndex + 1, lookupTable);
            }

            // Does not include the current index
            int sum2 = countSubsetSumRec(input, requiredSum, currentIndex + 1, lookupTable);

            lookupTable[currentIndex][requiredSum] = sum1 + sum2;
        }

        return lookupTable[currentIndex][requiredSum];
    }
}
