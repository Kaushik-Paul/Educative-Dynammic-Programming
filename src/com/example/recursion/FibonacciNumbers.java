package com.example.recursion;

import java.util.ArrayList;
import java.util.Arrays;

// Problem link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/fibonacci-numbers
/* Problem Statement -
* Fibonacci numbers are a sequence of numbers where each number is the sum of the two preceding
* numbers. Your task is to find the nth Fibonacci number.
*/
public class FibonacciNumbers {

    public static void main(String[] args) {
        ArrayList<Integer> inputs = new ArrayList<Integer>(
                Arrays.asList(0, 1, 2, 5, 7, 10, 14)
        );

        // Let's uncomment this and check the effect of dynamic programming using memoization
        // inputs.add(60);

        for (int i = 0; i < inputs.size(); i++) {
            System.out.println(i + 1 + ". " + inputs.get(i) + "th Fibonacci number is: " + getFibonacci(inputs.get(i)));
            System.out.println(new String(new char[100]).replace('\0', '-') + '\n');
        }
    }

    private static int getFibonacci(int num) {
        // Initializing the lookup table of size num + 1
        int[] lookupTable = new int[num + 1];
        Arrays.fill(lookupTable, -1);
        return getFibonacciMem(num, lookupTable);
    }

    private static int getFibonacciMem(int num, int[] lookupTable) {
        // Base cases
        if (num < 2) {
            return num;
        }

        // if value is already calculated then return that value
        if (lookupTable[num] != -1) {
            return lookupTable[num];
        }

        lookupTable[num] = getFibonacciMem(num - 1, lookupTable) + getFibonacciMem(num - 2, lookupTable);
        return lookupTable[num];
    }
}
