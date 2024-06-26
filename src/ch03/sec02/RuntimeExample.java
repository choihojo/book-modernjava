package ch03.sec02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RuntimeExample {
    public static void main(String[] args) {
        // 큰 크기의 리스트 생성
        int size = 100_000_000;
        List<Integer> integerList = new ArrayList<>(size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            integerList.add(random.nextInt(10));
        }

        // 테스트 반복 횟수
        int iterations = 10;
        long totalDurationStream = 0;
        long totalDurationIntStream = 0;
        long totalMemoryUsedStream = 0;
        long totalMemoryUsedIntStream = 0;

        // Stream<Integer> 사용
        for (int j = 0; j < iterations; j++) {
            Runtime runtime = Runtime.getRuntime();
            runtime.gc(); // GC를 호출하여 메모리 정리

            long startMemory = runtime.totalMemory() - runtime.freeMemory();
            long startTime = System.nanoTime();
            int sumUsingStream = integerList.stream()
                .mapToInt(Integer::intValue)
                .sum();
            long endTime = System.nanoTime();
            long endMemory = runtime.totalMemory() - runtime.freeMemory();

            long durationStream = endTime - startTime;
            long memoryUsedStream = endMemory - startMemory;

            totalDurationStream += durationStream;
            totalMemoryUsedStream += memoryUsedStream;
        }

        // IntStream 사용
        for (int j = 0; j < iterations; j++) {
            Runtime runtime = Runtime.getRuntime();
            runtime.gc(); // GC를 호출하여 메모리 정리

            long startMemory = runtime.totalMemory() - runtime.freeMemory();
            long startTime = System.nanoTime();
            int sumUsingIntStream = integerList.stream()
                .mapToInt(Integer::intValue)
                .sum();
            long endTime = System.nanoTime();
            long endMemory = runtime.totalMemory() - runtime.freeMemory();

            long durationIntStream = endTime - startTime;
            long memoryUsedIntStream = endMemory - startMemory;

            totalDurationIntStream += durationIntStream;
            totalMemoryUsedIntStream += memoryUsedIntStream;
        }

        // 평균 시간 및 메모리 계산
        long averageDurationStream = totalDurationStream / iterations;
        long averageMemoryUsedStream = totalMemoryUsedStream / iterations;
        long averageDurationIntStream = totalDurationIntStream / iterations;
        long averageMemoryUsedIntStream = totalMemoryUsedIntStream / iterations;

        System.out.println("Average time taken using Stream<Integer>: " + averageDurationStream + " ns");
        System.out.println("Average memory used using Stream<Integer>: " + averageMemoryUsedStream + " bytes");
        System.out.println("Average time taken using IntStream: " + averageDurationIntStream + " ns");
        System.out.println("Average memory used using IntStream: " + averageMemoryUsedIntStream + " bytes");
    }
}
