package com.example.longestcommonsubstring;

import java.util.Arrays;

// Problem link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/shortest-common-supersequence
/* Problem Statement -
* Given two strings, str1 and str2, find the length of the shortest string that has both the input
* strings as subsequences.
*/
public class ShortestCommonSupersequence {

    public static void main(String[] args) {
        String[] input1Strings = {"abcd", "educativeisfun", "cpprocks", "abcf", "dynamic"};
        String[] input2Strings = {"efgh", "algorithmsarefun", "cppisfun", "bdcf", "programming"};

        for (int i = 0; i < input1Strings.length; i++) {
            System.out.print(i + 1);
            System.out.println("\tString 1: " + input1Strings[i]);
            System.out.println("\tString 1: " + input2Strings[i]);
            System.out.println("\tLength of Shortest Common Supersequence: " + shortestCommonSupersequence(input1Strings[i], input2Strings[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    private static int shortestCommonSupersequence(String firstString, String secondString) {
        if (firstString.isEmpty() || secondString.isEmpty()) {
            return 0;
        }

        int[][] lookupTable = new int[firstString.length() + 1][secondString.length() + 1];
        for (int[] row : lookupTable) {
            Arrays.fill(row, 0);
        }

        // filling the additional row and column with constants
        // TODO: Find out why this is required ?
        for (int i = 0; i <= firstString.length(); i++) {
            lookupTable[i][0] = i;
        }
        for (int j = 0; j <= secondString.length(); j++) {
            lookupTable[0][j] = j;
        }

        return shortestCommonSupersequenceHelper(firstString, secondString, lookupTable);
    }

    private static int shortestCommonSupersequenceHelper(String firstString, String secondString, int[][] lookupTable) {
        for (int i = 1; i < firstString.length() + 1; i++) {
            for (int j = 1; j < secondString.length() + 1; j++) {
                // If character matches then add one to the diagonal element
                // otherwise consider two shortest common supersequences -
                // one without str1[i1-1] and one without str2[i2-1].
                if (firstString.charAt(i - 1) == secondString.charAt(j - 1)) {
                    lookupTable[i][j] = 1 + lookupTable[i - 1][j - 1];
                } else {
                    lookupTable[i][j] = 1 + Math.min(lookupTable[i - 1][j], lookupTable[i][j - 1]);
                }
            }
        }
        return lookupTable[firstString.length()][secondString.length()];
    }
}
