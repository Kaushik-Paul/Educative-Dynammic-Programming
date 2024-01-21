package com.example.longestcommonsubstring;

import java.util.Arrays;
import java.util.stream.Stream;

// Problem link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/solving-the-longest-common-substring-problem
/* Problem Statement -
* Given two strings s1 and s2, you have to find the length of the Longest Common Substring (LCS) in both these strings.
* Let’s say we have two strings, “helloworld” and “yelloword”, there are multiple common substrings, such as
* “llo”, “ello”, “ellowor”, “low”, and “d”. The longest common substring is “ellowor”, with length 7.
*/
public class LongestCommonSubstring {

    public static void main(String[] args) {
        String[] s1 = {
                "educative",
                "bcdcdcd",
                "arefun",
                "yourocks",
                "abc"
        };
        String[] s2 = {
                "education",
                "aacdcdcd",
                "isfun",
                "youawesome",
                "def"
        };

        for (int i = 0; i < s1.length; i++) {
            System.out.print(i + 1);
            System.out.println(".\tInput string 1: \"" + s1[i] + "\"");
            System.out.println("\tInput string 2: \"" + s2[i] + "\"");
            System.out.println("\n\tThe Length of Longest Common Substring is: " + lcsLength(s1[i], s2[i]));
            Stream.generate(() -> "-").limit(100).forEach(System.out::print);
            System.out.println();
        }
    }

    // Use tabulation as this is the easiest to understand
    private static int lcsLength(String string1, String string2) {
        int[][] lookupTable = new int[string1.length() + 1][string2.length() + 1];

        for (int[] row : lookupTable) {
            Arrays.fill(row, 0);
        }

        return lcsLengthRec(string1, string2, lookupTable);
    }

    private static int lcsLengthRec(String string1, String string2, int[][] lookupTable) {
        if (string1.isEmpty() || string2.isEmpty()) {
            return 0;
        }

        int longestSubstring = 0;
        for (int i = 1; i < string1.length() + 1; i++) {
            for (int j = 1; j < string2.length() + 1; j++) {
                if (string1.charAt(i - 1) == string2.charAt(j - 1)) {
                    lookupTable[i][j] = lookupTable[i - 1][j - 1] + 1;
                    longestSubstring = Math.max(longestSubstring, lookupTable[i][j]);
                }
            }
        }
        return longestSubstring;
    }
}
