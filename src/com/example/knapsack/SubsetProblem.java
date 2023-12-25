package com.example.knapsack;

import java.util.Arrays;

// Problem link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/subset-sum
/*
* Problem Statement - Given a set of positive numbers arr and a value total, determine if there exists a subset
* in the given set whose sum is equal to total.
* A subset can be an empty set, or it can either consist of some elements of the set or all the elements of the set.
*/
public class SubsetProblem {

    public static void main(String[] args) {
        int[][] inputArr = {
                {3, 5, 8},
                {2, 4, 7},
                {2, 3, 5},
                {1, 2, 3, 7},
                {10, 20, 23, 24}
        };
        int[] total = {13, 8, 5, 6, 57};

        int[][] newInputArr = Arrays.copyOf(inputArr, inputArr.length + 1);
        newInputArr[inputArr.length] = new int[]{0, 1, 4, 6, 7, 8, 9, 10, 11, 16, 17, 18, 21, 23, 25, 26, 28, 34,
                35, 36, 38, 39, 40, 41, 42, 44, 47, 50, 51, 54, 55, 61, 62, 63, 65, 69, 71, 72,
                73, 75, 76, 78, 79, 80, 82, 83, 84, 85, 86, 88, 90, 91, 92, 93, 94, 98, 99, 100,
                101, 103, 104, 106, 109, 116, 118, 119};
        inputArr = newInputArr;

        int[] newTotal = Arrays.copyOf(total, total.length + 1);
        newTotal[total.length] = 2593;
        total = newTotal;

        String result;
        for (int i = 0; i < total.length; i++) {
            if (subsetSum(inputArr[i], total[i]))
                result = "Yes.";
            else
                result = "No.";
            System.out.println(i + 1 + ".\tDoes a subset of " + Arrays.toString(inputArr[i]) + " sum up to " + total[i] + "?  " + result);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    private static boolean subsetSum(int[] inputArray, int targetSum) {
        int[][] lookupTable = new int[inputArray.length][targetSum + 1];

        for (int[] nums : lookupTable) {
            Arrays.fill(nums, -1);
        }

        return subsetSumRec(inputArray, targetSum, 0, lookupTable) >= 1;
    }

    private static int subsetSumRec(int[] inputArray, int requiredSum, int currentIndex, int[][] lookupTable) {
        if (requiredSum == 0) {
            return 1;
        }
        if (currentIndex >= inputArray.length) {
            return 0;
        }

        if (lookupTable[currentIndex][requiredSum] == -1) {
            int sum1 = 0;
            if (inputArray[currentIndex] <= requiredSum) {
                // Calculate by both including and not including current num
                sum1 = subsetSumRec(inputArray, requiredSum - inputArray[currentIndex], currentIndex + 1, lookupTable);
            }
            int sum2 = subsetSumRec(inputArray, requiredSum, currentIndex + 1, lookupTable);
            lookupTable[currentIndex][requiredSum] = sum1 + sum2;
        }
        return lookupTable[currentIndex][requiredSum];
    }
}
