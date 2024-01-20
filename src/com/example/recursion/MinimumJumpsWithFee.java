package com.example.recursion;

import java.util.Arrays;

public class MinimumJumpsWithFee {

    public static void main(String[] args) {
        int[][] inputs = {{1, 2, 5, 2, 1, 2}, {2, 3, 4, 5},
                {1, 100, 1, 1, 1, 100, 1, 1, 100, 1},
                {10, 15, 20}, {0, 1, 2, 3, 4, 5, 6, 7, 8}};

        String printedOutput = "";
        for (int i = 0; i < inputs.length; i++) {
            printedOutput = (i + 1) + ".\tSteps: " + inputs[i].length + "\n\tFee: " + Arrays.toString(inputs[i]) + "\n\n\t";

            int minimumFee = minFee(inputs[i]);
            System.out.println(printedOutput + "Minimum fee: " + minimumFee);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    private static int minFee(int[] fee) {
        int[] lookupTable = new int[fee.length];
        Arrays.fill(lookupTable, -1);
        return minFeeRec(fee, lookupTable, 0);
    }

    private static int minFeeRec(int[] fee, int[] lookupTable, int currentIdx) {
        if (currentIdx >= fee.length - 1) {
            return 0;
        }

        if (lookupTable[currentIdx] != -1) {
            return lookupTable[currentIdx];
        }

        // Find all possible outcomes and then get the minimum
        int oneStep = fee[currentIdx + 1] + minFeeRec(fee, lookupTable, currentIdx + 1);
        int twoStep = fee[currentIdx + 2] + minFeeRec(fee, lookupTable, currentIdx + 2);
        int threeStep = fee[currentIdx + 3] + minFeeRec(fee, lookupTable, currentIdx + 3);
        lookupTable[currentIdx] = Math.min(Math.min(oneStep, twoStep), threeStep);
        return lookupTable[currentIdx];
    }
}
