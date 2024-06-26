package ch03.sec02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveReferenceExample {
    public static void main(String[] args) {
        // 큰 크기의 리스트 생성
        int size = 10_000_000;
        List<Integer> integerList = new ArrayList<>(size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            integerList.add(random.nextInt(100));
        }

        // Stream<Integer> 사용
        long startTime = System.nanoTime();
        int sumUsingStream = integerList.stream()
            .mapToInt(Integer::intValue)
            .sum();
        long endTime = System.nanoTime();
        long durationStream = endTime - startTime;
        System.out.println("Sum using Stream<Integer>: " + sumUsingStream);
        System.out.println("Time taken using Stream<Integer>: " + durationStream + " ns");

        // IntStream 사용
        startTime = System.nanoTime();
        int sumUsingIntStream = integerList.stream()
            .mapToInt(Integer::intValue)
            .sum();
        endTime = System.nanoTime();
        long durationIntStream = endTime - startTime;
        System.out.println("Sum using IntStream: " + sumUsingIntStream);
        System.out.println("Time taken using IntStream: " + durationIntStream + " ns");
    }
}
