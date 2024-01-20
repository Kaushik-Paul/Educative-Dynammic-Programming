package com.example.recursion;

import java.util.Arrays;

// Problem link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/minimum-jumps-to-reach-the-end
/* Problem Statement - Given an array nums of positive numbers, start from the first index and reach the last index
with the minimum number of jumps, where a number at an index represents the maximum jump from that index.
*
*/
public class MinimumJumpToReachEnd {

    public static void main(String[] args) {
        int[][] inputs = {
                {1, 7},
                {2, 1, 0},
                {5, 2, 3, 1, 1, 4},
                {5, 2, 1, 1, 1, 4},
                {9, 1, 1, 3, 6, 9, 3, 0, 1, 3}
        };

        for (int i = 0; i < inputs.length; i++) {
            System.out.println(i + 1 + ".\t" + Arrays.toString(Arrays.copyOfRange(inputs[i], 1, inputs[i].length)));
            System.out.println("\tMinimum jumps to reach the end: " + findMinJumps(Arrays.copyOfRange(inputs[i], 1, inputs[i].length)));
            System.out.println(new String(new char[100]).replace('\0', '-') + '\n');
        }
    }

    private static int findMinJumps(int[] jumps) {
        int[] lookupTable = new int[jumps.length];
        Arrays.fill(lookupTable, Integer.MAX_VALUE);
        return findMinJumpsRec(jumps, lookupTable, 0);
    }

    private static int findMinJumpsRec(int[] jumps, int[] lookupTable, int currentIndex) {
        if (currentIndex >= jumps.length - 1) {
            return 0;
        }

        if (lookupTable[currentIndex] != Integer.MAX_VALUE) {
            return lookupTable[currentIndex];
        }

        int minJump = Integer.MAX_VALUE;
        for (int i = currentIndex + 1; i <= currentIndex + jumps[currentIndex]; i++) {
            minJump = Math.min(minJump, findMinJumpsRec(jumps, lookupTable, i) + 1);
        }
        lookupTable[currentIndex] = minJump;
        return minJump;
    }
}
