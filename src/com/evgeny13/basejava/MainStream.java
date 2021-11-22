package com.evgeny13.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {

    public static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (x, y) -> x * 10 + y);
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        boolean isEven = integers.stream().mapToInt(s1 -> s1).sum() % 2 == 0;
        return integers.stream().filter(s2 -> isEven ^ (s2 % 2 == 0)).collect(Collectors.toList());
    }
}
