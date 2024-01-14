package com.example.recursion;

import java.util.Arrays;

// Problem Link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/staircase-problem
/* Problem Statement -
* A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3 steps at a time.
* Your task is to count the number of possible ways that the child can climb up the stairs.
*/
public class StaircaseProblem {

    public static void main(String[] args) {
        int[] inputs = {0, 4, 3, 5, 6};

        for (int i = 0; i < inputs.length; i++) {
            System.out.println((i + 1) + ".\tSteps: " + inputs[i] +
                    "\n\n\tNumber of ways: " + countWays(inputs[i]));

            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    private static int countWays(int num) {
        int[] lookupTable = new int[num + 1];
        Arrays.fill(lookupTable, -1);

        return countWaysRec(num, lookupTable);
    }

    private static int countWaysRec(int num, int[] lookupTable) {
        if (num < 0) {
            return 0;
        }
        if (num == 0) {
            return 1;
        }

        if (lookupTable[num] > -1) {
            return lookupTable[num];
        }

        lookupTable[num] = countWaysRec(num - 1, lookupTable) + countWaysRec(num - 2, lookupTable) + countWaysRec(num - 3, lookupTable);
        return lookupTable[num];
    }
}
