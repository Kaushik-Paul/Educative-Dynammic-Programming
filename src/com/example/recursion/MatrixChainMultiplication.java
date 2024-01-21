package com.example.recursion;

import java.util.Arrays;
import java.util.stream.Stream;

// Problem Link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/matrix-chain-multiplication
/* Problem Statement -
* You are given a chain of matrices to be multiplied. You have to find the least number of primitive multiplications
* needed to evaluate the result. Your algorithm will take dims as input, a list denoting the dimensions of the matrices
* to be multiplied. Since the columns of the first matrix and the rows of the second matrix are the same, we have not
* repeated this information in the list dims. So the dimensions of the first matrix would be (dims[0] X dims[1])
*  Similarly, the dimensions of the third matrix would be (dims[1] X dims[2]), and so on.
*/
public class MatrixChainMultiplication {

    public static void main(String[] args) {
        int[][] dims = {
                {3, 3, 2, 1},
                {4, 3, 2, 1},
                {2, 2, 2},
                {1, 1, 1},
                {13, 16, 11, 99, 3}};

        for (int i = 0; i < dims.length; i++) {
            System.out.print(i + 1);
            System.out.println(".\tInput dims: " + Arrays.toString(dims[i]));
            System.out.println("\n\tThe least number of primitive multiplications possible: " + minMultiplications(dims[i]));
            Stream.generate(() -> "-").limit(100).forEach(System.out::print);
            System.out.println();
        }
    }

    private static int minMultiplications(int[] dims) {
        int n = dims.length;
        int[][] lookupTable = new int[n + 1][n + 1];
        for (int[] row : lookupTable) {
            Arrays.fill(row, -1);
        }
        return minMultiplicationsRec(dims, 0, n, lookupTable);
    }

    private static int minMultiplicationsRec(int[] dims, int start, int end, int[][] lookupTable) {
        // Base case
        if (end - start <= 2) {
            return 0;
        }

        if (lookupTable[start][end] != -1) {
            return lookupTable[start][end];
        }

        int minimum = Integer.MAX_VALUE;
        for (int i = start + 1; i < end - 1; i++) {
            minimum = Math.min(minimum, minMultiplicationsRec(dims, start, i + 1, lookupTable) +
                                        minMultiplicationsRec(dims, i, end, lookupTable) +
                                        dims[start] * dims[i] * dims[end - 1]);
        }
        lookupTable[start][end] = minimum;
        return minimum;
    }
}
