package com.evgeny13.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        // test case for minValue-method:
        int[] testNumbers1 = {1, 5, 6, 3, 4};
        minValue(testNumbers1);

        // test case for oddOrEven-method:
        List<Integer> testNumbers2 = new ArrayList<>();
        testNumbers2.add(2);
        testNumbers2.add(5);
        testNumbers2.add(7);
        testNumbers2.add(8);
        oddOrEven(testNumbers2);
    }

    public static void minValue(int[] values) {
        int number = Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (x, y) -> x * 10 + y);

        System.out.println("Number from minValue-method: " + number);
    }

    public static void oddOrEven(List<Integer> integers) {
        boolean isEven = integers.stream()
                .mapToInt(s1 -> s1)
                .sum() % 2 == 0;

        List<Integer> numbers = integers.stream()
                .filter(s2 -> isEven ^ (s2 % 2 == 0))
                .collect(Collectors.toList());

        System.out.println("Numbers from oddOrEven-method: " + numbers);
    }
}
