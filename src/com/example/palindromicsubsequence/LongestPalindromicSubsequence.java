package com.example.palindromicsubsequence;

import java.util.ArrayList;
import java.util.Arrays;

// Problem link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/longest-palindromic-subsequence
/* Problem Statement -
* Given a string, find the length of the longest palindromic subsequence if it exists. In a palindromic subsequence,
* elements read the same backward and forward.
* A subsequence is a sequence that can be derived from another sequence by deleting or keeping some elements
* without changing the order of the remaining elements. Let’s say that we are given a string “abbca” to find
* its longest palindromic subsequence. We see in this example that if we ignore “c” in the given string, we get
* the longest palindrome (“abba”) of this sequence which has a length of 4.
*/
public class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        ArrayList< String > strings = new ArrayList < String > (
                Arrays.asList("cddpd", "abdbca", "racecar", "pqr")
        );

        int index = 0;
        for (String input: strings) {
            System.out.println((++index) + ". The length of the longest palindromic subsequence in '" + input + "'  is: " + longestPalindromicSubsequence(input));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    // longestPalindromicSubsequence is just longestCommonSubsequence of string and its reverse.
    private static int longestPalindromicSubsequence(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        String reverseString = stringBuilder.reverse().toString();

        int[][] lookupTable = new int[string.length() + 1][string.length() + 1];

        for (int[] row : lookupTable) {
            Arrays.fill(row, 0);
        }

        return longestPalindromicSubsequenceHelper(string, reverseString, lookupTable);
    }

    private static int longestPalindromicSubsequenceHelper(String string, String reverseString, int[][] lookupTable) {
        for (int i = 1; i < string.length() + 1; i++) {
            for (int j = 1; j < reverseString.length() + 1; j++) {
                if (string.charAt(i - 1) == reverseString.charAt(j - 1)) {
                    lookupTable[i][j] = 1 + lookupTable[i - 1][j - 1];
                } else {
                    lookupTable[i][j] = Math.max(lookupTable[i - 1][j], lookupTable[i][j - 1]);
                }
            }
        }

        return lookupTable[string.length()][string.length()];
    }
}
