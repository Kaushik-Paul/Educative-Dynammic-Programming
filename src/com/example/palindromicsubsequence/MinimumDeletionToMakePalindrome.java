package com.example.palindromicsubsequence;

import java.util.ArrayList;
import java.util.Arrays;

// Problem Link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/minimum-deletions-in-a-string-to-make-it-a-palindrome
/* Problem Statement -
* You are given a string s. The task is to count the minimum deletions from s such that the resultant string
* is a palindrome.Let’s take an example string, “radear”. We can remove the character “e” from this string
* and we will be left with “radar”, which is a valid palindrome. So, we need minimum of one deletion to make
* this string a valid palindrome.
*/
public class MinimumDeletionToMakePalindrome {
    public static void main(String[] args) {
        ArrayList < String > strings = new ArrayList< String >(
                Arrays.asList("raddar", "lleveal", "aqwrqhanisahinahqwe", "pqr")
        );

        int index = 0;
        for (String input: strings) {
            System.out.println((++index) + ". The minimum deletions required to make '" + input + "' a palindrome is: " + minimumDeletions(input));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    // minDel = len(str) - LCS(str, revStr)
    private static int minimumDeletions(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        String reverseString = stringBuilder.reverse().toString();

        int[][] lookupTable = new int[string.length() + 1][string.length() + 1];

        for (int[] row : lookupTable) {
            Arrays.fill(row, 0);
        }

        int LPS = longestPalindromicSubsequenceHelper(string, reverseString, lookupTable);
        return string.length() - LPS;
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

        return lookupTable[string.length()][reverseString.length()];
    }
}
