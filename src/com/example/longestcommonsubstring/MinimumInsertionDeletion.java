package com.example.longestcommonsubstring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

// Problem link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/minimum-number-of-deletions-and-insertions
/* Problem Statement -
* Given two strings, str1 and str2, find the minimum number of deletions and insertions required to transform
* str1 into str2. For example, if we want to transform str1 “pqr” into str2 “tqr”, we need to delete “p” and
* insert “t” in str1. Therefore, the minimum number of deletions and insertions required are:
* Deletion: 1, Insertion: 1
*/
public class MinimumInsertionDeletion {

    public static void main(String[] args) {
        List<String> str1List = new ArrayList<String>(Arrays.asList("pqr", "heap", "passport", "baller", "sam", "bed"));

        List <String> str2List = new ArrayList<String>(Arrays.asList("tqr", "pea", "ppsspt", "ball", "samson", "read"));

        for(int i = 0; i < str1List.size(); i++)
        {
            System.out.println(i + 1 + ".\tstr1: " + str1List.get(i));
            System.out.println("\tstr2: " + str2List.get(i));
            int[] output = minDelIns(str1List.get(i), str2List.get(i));
            System.out.println("\n\tMinimum deletions required:  " + output[0]);
            System.out.println("\tMinimum insertions required: " + output[1]);
            Stream.generate(() -> "-").limit(100).forEach(System.out::print);
            System.out.println();
        }
    }

    private static int[] minDelIns(String firstString, String secondString) {
        int[][] lookupTable = new int[firstString.length() + 1][secondString.length() + 1];

        for (int[] row : lookupTable) {
            Arrays.fill(row, 0);
        }

        int matchingCharacter = findMaxMatchingSubseq(firstString, secondString, lookupTable);

        // calculating number of deletions required from firstString to transform it into secondString
        int deletion = firstString.length() - matchingCharacter;

        // calculating number of insertions required in firstString to transform it into secondString
        int insertion = secondString.length() - matchingCharacter;
        return new int[]{deletion, insertion};
    }

    private static int findMaxMatchingSubseq(String firstString, String secondString, int[][] lookupTable) {
        for (int i = 1; i < firstString.length() + 1; i++) {
            for (int j = 1; j < secondString.length() + 1; j++) {
                // If character is matching then add 1 to the previous diagonal
                // or max of left and above
                if (firstString.charAt(i - 1) == secondString.charAt(j - 1)) {
                    lookupTable[i][j] = 1 + lookupTable[i - 1][j - 1];
                } else {
                    lookupTable[i][j] = Math.max(lookupTable[i - 1][j], lookupTable[i][j - 1]);
                }
            }
        }

        return lookupTable[firstString.length()][secondString.length()];
    }
}
