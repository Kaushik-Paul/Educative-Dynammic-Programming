package com.example.longestcommonsubstring;

import java.util.Arrays;
import java.util.stream.Stream;

// Problem link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/longest-common-subsequence
/* Problem Statement -
* A subsequence is a string formed by removing some characters from the original string, while maintaining the relative
* position of the remaining characters. For example, “abd” is a subsequence of “abcd”, where the removed
* character is “c”. If there is no common subsequence, then return 0.
*/
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String[] firstStrings = {"qstw", "setter", "abcde", "partner", "freedom"};
        String[] secondStrings = {"gofvn", "bat", "apple", "park", "redeem"};

        for (int i = 0; i < firstStrings.length; i++){
            System.out.println(i + 1 + ".\tstr1: " + firstStrings[i] + "\n\tstr2: " + secondStrings[i]
                    + "\n\n\tThe length of the longest common subsequence is: " + longestCommonSubsequence(firstStrings[i], secondStrings[i]));

            Stream.generate(() -> "-").limit(100).forEach(System.out::print);
            System.out.println();
        }
    }

    private static int longestCommonSubsequence(String firstString, String secondString) {
        if (firstString.isEmpty() || secondString.isEmpty()) {
            return 0;
        }

        int[][] lookupTable = new int[firstString.length() + 1][secondString.length() + 1];
        for (int[] row : lookupTable) {
            Arrays.fill(row, 0);
        }
        return longestCommonSubsequenceHelper(firstString, secondString, lookupTable);
    }

    private static int longestCommonSubsequenceHelper(String firstString, String secondString, int[][] lookupTable) {
        for (int i = 1; i < firstString.length() + 1; i++) {
            for (int j = 1; j < secondString.length() + 1; j++) {
                // If char matches then add +1 to the previous diagonal element,
                // otherwise max of left and above element
                if (firstString.charAt(i - 1) == secondString.charAt(j - 1)) {
                    lookupTable[i][j] = lookupTable[i - 1][j - 1] + 1;
                } else {
                    lookupTable[i][j] = Math.max(lookupTable[i - 1][j], lookupTable[i][j - 1]);
                }
            }
        }
        return lookupTable[firstString.length()][secondString.length()];
    }
}
