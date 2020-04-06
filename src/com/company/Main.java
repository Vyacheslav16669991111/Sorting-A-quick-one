package com.company;

import java.util.Arrays;

public class Main {

    private static QuickSorter quickSorter = new QuickSorter();

    public static void main(String[] args) {
        System.out.println("Hello World! I'm mad sorter!\n");

        String[] result = quickSorter.sort(new LogicalArrayImpl<String>(String[].class, new String[] {"1", "3", "2"}, 3));

        System.out.println(Arrays.toString(result));
    }
}
