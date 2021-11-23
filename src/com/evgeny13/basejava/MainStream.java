package com.evgeny13.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        // test case for minValue-method:
        int[] testNumbers1 = {1, 5, 6, 3, 4};
        System.out.println("Number from minValue-method: " + minValue(testNumbers1));

        // test case for oddOrEven-method:
        List<Integer> testNumbers2 = new ArrayList<>(Arrays.asList(2, 5, 7, 8));
        System.out.println("Numbers from oddOrEven-method: " + oddOrEven(testNumbers2));
    }

    public static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (x, y) -> x * 10 + y);
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        boolean isEven = integers.stream()
                .mapToInt(s1 -> s1)
                .sum() % 2 == 0;

        return integers.stream()
                .filter(s2 -> isEven ^ (s2 % 2 == 0))
                .collect(Collectors.toList());
    }
}
