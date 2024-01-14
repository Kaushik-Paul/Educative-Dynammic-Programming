package com.example.recursion;

import java.util.ArrayList;
import java.util.Arrays;

// Problem Link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/nth-tribonacci-number
/* Problem Statement -
* Tribonacci numbers are a sequence of numbers where each number is the sum of the three preceding numbers.
* Your task is to find the nth Tribonacci number.
*/
public class NthTribonacciNumber {

    public static void main(String[] args) {
        ArrayList < Integer > n = new ArrayList< Integer >(
                Arrays.asList(4, 5, 25, 17, 19)
        );

        int index = 0;
        for (int input: n) {
            System.out.println((++index) + ".\tThe " + input + "th Tribonacci number is:  " +
                    tribonacci(input));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    private static int tribonacci(int num) {
        int[] lookupTable = new int[num + 1];
        Arrays.fill(lookupTable, -1);
        return tribonacciHelper(num, lookupTable);
    }

    private static int tribonacciHelper(int num, int[] lookupTable) {
        // Base case F(0) = 0, F(1) = 1, F(2) = 1
        if (num < 3) {
            return num == 0 ? 0 : 1;
        }

        if (lookupTable[num] != -1) {
            return lookupTable[num];
        }

        lookupTable[num] = tribonacciHelper(num - 1, lookupTable) + tribonacciHelper(num - 2, lookupTable) + tribonacciHelper(num - 3, lookupTable);
        return lookupTable[num];
    }
}
