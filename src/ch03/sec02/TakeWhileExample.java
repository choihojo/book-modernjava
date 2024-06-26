package ch03.sec02;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class TakeWhileExample {

    // IntStream
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 3, 5, 6, 7);
        List<Integer> list2 = list.stream().takeWhile(dish -> {
            System.out.println("111");
            return dish < 5;
        }).toList();
    }

}
