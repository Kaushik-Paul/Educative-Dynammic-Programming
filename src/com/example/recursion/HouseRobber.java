package com.example.recursion;

import java.util.Arrays;

// Problem link - https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/house-thief-problem
/* Problem Statement -
* You are a professional robber, and after weeks of planning, you plan to rob some houses along a street.
* Each of these houses has a lot of money and valuables. Let’s say that you cannot rob houses adjacent to each other
* since they have security and burglar alarms installed. Following the above-mentioned constraint and given an
* integer array, money, that represents the amount of money in each house, return the maximum amount of money
* you can steal tonight without alerting the police.
*/
public class HouseRobber {

    public static void main(String[] args) {
        int[][] list = {{2, 7, 9, 31, 33, 4, 99, 1, 2, 3, 15, 34, 23, 11, 9, 1, 4},
                {1, 2, 3, 1}, {4, 6, 3, 9, 3, 8, 3}, {1, 5, 7, 3, 7, 2, 3}, {2, 7, 9, 3, 1}};

        for (int i = 0; i < list.length; i++) {
            // V1 of the solution
            System.out.print("Maximum Theft in example ");
            System.out.print(Arrays.toString(list[i]));
            System.out.println(" is " + (houseThief(list[i])));

            // V2 of the solution
            System.out.print("Maximum Theft using v2 in example ");
            System.out.print(Arrays.toString(list[i]));
            System.out.println(" is " + (houseThiefV2(list[i])));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    private static int houseThief(int[] money) {
        int[] lookupTable = new int[money.length];
        Arrays.fill(lookupTable, -1);
        return houseThiefRec(money, lookupTable, 0);
    }

    private static int houseThiefRec(int[] money, int[] lookupTable, int currentIdx) {
        if (currentIdx >= money.length) {
            return 0;
        }

        if (lookupTable[currentIdx] != -1) {
            return lookupTable[currentIdx];
        }

        // Either skip the current house and choose next house or choose the current house and select next next house
        lookupTable[currentIdx] = Math.max(houseThiefRec(money, lookupTable, currentIdx + 1), money[currentIdx] + houseThiefRec(money, lookupTable, currentIdx + 2));
        return lookupTable[currentIdx];
    }

    private static int houseThiefV2(int[] money) {
        if (money.length == 0) {
            return 0;
        } else if (money.length == 1) {
            return money[0];
        }

        int[] lookupTable = new int[money.length];
        lookupTable[0] = money[0];
        lookupTable[1] = Math.max(money[0], money[1]);

        for (int i = 2; i < money.length; i++) {
            lookupTable[i] = Math.max(lookupTable[i - 1], money[i] + lookupTable[i - 2]);
        }

        return lookupTable[money.length - 1];
    }
}
