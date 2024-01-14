package com.example.recursion;

import java.util.Arrays;
import java.util.stream.Stream;


// Problem Link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/number-factors
/* Problem Statement -
* Given a fixed list of numbers, [1, 3, 4], and a target number, n, count all the possible ways
* in which n can be expressed as the sum of the given numbers. If there is no possible way to
* represent n using the provided numbers, return 0.

*/
public class NumberFactors {

    public static void main(String[] args) {
        int[] targetNumbers = {3, 5, 10, 25, 0};

        for (int i = 0; i<targetNumbers.length; i++) {
            System.out.println(i + 1 + ".\tn: " + targetNumbers[i] +
                    "\n\n\tNumber of ways to reach the target number: " + countWays(targetNumbers[i]));

            Stream.generate(() -> "-").limit(100).forEach(System.out::print);
            System.out.println(" ");
        }
    }

    private static int countWays(int num) {
        int[] lookupTable = new int[num + 1];
        Arrays.fill(lookupTable, -1);

        return countWaysRec(num, lookupTable);
    }

    private static int countWaysRec(int num, int[] lookupTable) {

        // Base cases
        if (num < 0) {
            return 0;
        }
        if (num == 0) {
            return 1;
        }

        if (lookupTable[num] != -1) {
            return lookupTable[num];
        }

        lookupTable[num] = countWaysRec(num - 1, lookupTable) + countWaysRec(num - 3, lookupTable) + countWaysRec(num - 4, lookupTable);
        return lookupTable[num];
    }
}
