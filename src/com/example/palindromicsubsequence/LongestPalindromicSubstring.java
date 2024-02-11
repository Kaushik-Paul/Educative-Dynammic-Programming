package com.example.palindromicsubsequence;

import java.util.ArrayList;
import java.util.Arrays;

// Problem link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/longest-palindromic-substring
/* Problem Statement -
* Given a string s, find the length of its longest palindromic substring. The longest palindromic substring
* is a substring with maximum length that is also a palindrome. A phrase, word, or sequence is a palindrome
* that reads the same forward and backward.
*/
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>(
                Arrays.asList("abcbda", "tworacecars", "pqrssrqp", "mnop", "bbbbbbbbbbbbbbbbbbbb"));

        for (int i=0; i<strings.size(); ++i){
            System.out.println(i+1+".\t Input string: '" + strings.get(i) + "'");
            System.out.println("\t Length of the longest palindromic substring: " + findLpsLength(strings.get(i)));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    private static int findLpsLength(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        String reverseString = stringBuilder.reverse().toString();

        int[][] lookupTable = new int[string.length() ][string.length()];

        for (int[] row : lookupTable) {
            Arrays.fill(row, -1);
        }

        return findLpsLengthHelper(string, 0, string.length() - 1, lookupTable);
    }

    private static int findLpsLengthHelper(String string, int startIdx, int endIdx, int[][] lookupTable) {
        if (startIdx == endIdx) {
            return 1;
        }

        if (lookupTable[startIdx][endIdx] != -1) {
            return lookupTable[startIdx][endIdx];
        }

        if (string.charAt(startIdx) == string.charAt(endIdx)) {
            int subStringLength = endIdx - startIdx + 1;

            if (subStringLength == 2) {
                lookupTable[startIdx][endIdx] = 2;
                return lookupTable[startIdx][endIdx];
            }

            if (subStringLength == 2 + findLpsLengthHelper(string, startIdx + 1, endIdx - 1, lookupTable)) {
                lookupTable[startIdx][endIdx] = subStringLength;
                return lookupTable[startIdx][endIdx];
            }
        }
        lookupTable[startIdx][endIdx] = Math.max(findLpsLengthHelper(string, startIdx + 1, endIdx, lookupTable),
                    findLpsLengthHelper(string, startIdx, endIdx - 1, lookupTable));

        return lookupTable[startIdx][endIdx];
    }
}
