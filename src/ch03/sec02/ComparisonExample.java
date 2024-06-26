package ch03.sec02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComparisonExample {
    public static void main(String[] args) {
        // 큰 크기의 리스트 생성
        int size = 10_000_000;
        List<Integer> integerList = new ArrayList<>(size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            integerList.add(random.nextInt(100));
        }

        // 테스트 반복 횟수
        int iterations = 10;
        long totalDurationStream = 0;
        long totalDurationMapToInt = 0;
        long totalDurationForLoop = 0;
        long totalMemoryUsedStream = 0;
        long totalMemoryUsedMapToInt = 0;
        long totalMemoryUsedForLoop = 0;

        // Stream<Integer> 사용
        for (int j = 0; j < iterations; j++) {
            Runtime runtime = Runtime.getRuntime();
            runtime.gc(); // GC를 호출하여 메모리 정리

            long startMemory = runtime.totalMemory() - runtime.freeMemory();
            long startTime = System.nanoTime();
            int sumUsingStream = integerList.stream()
                .reduce(0, Integer::sum);
            long endTime = System.nanoTime();
            long endMemory = runtime.totalMemory() - runtime.freeMemory();

            long durationStream = endTime - startTime;
            long memoryUsedStream = endMemory - startMemory;

            totalDurationStream += durationStream;
            totalMemoryUsedStream += memoryUsedStream;
        }

        // IntStream 사용 (mapToInt)
        for (int j = 0; j < iterations; j++) {
            Runtime runtime = Runtime.getRuntime();
            runtime.gc(); // GC를 호출하여 메모리 정리

            long startMemory = runtime.totalMemory() - runtime.freeMemory();
            long startTime = System.nanoTime();
            int sumUsingMapToInt = integerList.stream()
                .mapToInt(Integer::intValue)
                .sum();
            long endTime = System.nanoTime();
            long endMemory = runtime.totalMemory() - runtime.freeMemory();

            long durationMapToInt = endTime - startTime;
            long memoryUsedMapToInt = endMemory - startMemory;

            totalDurationMapToInt += durationMapToInt;
            totalMemoryUsedMapToInt += memoryUsedMapToInt;
        }

        // for 루프 사용
        for (int j = 0; j < iterations; j++) {
            Runtime runtime = Runtime.getRuntime();
            runtime.gc(); // GC를 호출하여 메모리 정리

            long startMemory = runtime.totalMemory() - runtime.freeMemory();
            long startTime = System.nanoTime();
            int sumUsingForLoop = 0;
            for (int value : integerList) {
                sumUsingForLoop += value;
            }
            long endTime = System.nanoTime();
            long endMemory = runtime.totalMemory() - runtime.freeMemory();

            long durationForLoop = endTime - startTime;
            long memoryUsedForLoop = endMemory - startMemory;

            totalDurationForLoop += durationForLoop;
            totalMemoryUsedForLoop += memoryUsedForLoop;
        }

        // 평균 시간 및 메모리 계산
        long averageDurationStream = totalDurationStream / iterations;
        long averageMemoryUsedStream = totalMemoryUsedStream / iterations;
        long averageDurationMapToInt = totalDurationMapToInt / iterations;
        long averageMemoryUsedMapToInt = totalMemoryUsedMapToInt / iterations;
        long averageDurationForLoop = totalDurationForLoop / iterations;
        long averageMemoryUsedForLoop = totalMemoryUsedForLoop / iterations;

        System.out.println("Average time taken using Stream<Integer>.reduce: " + averageDurationStream + " ns");
        System.out.println("Average memory used using Stream<Integer>.reduce: " + averageMemoryUsedStream + " bytes");
        System.out.println("Average time taken using Stream<Integer>.mapToInt: " + averageDurationMapToInt + " ns");
        System.out.println("Average memory used using Stream<Integer>.mapToInt: " + averageMemoryUsedMapToInt + " bytes");
        System.out.println("Average time taken using for loop: " + averageDurationForLoop + " ns");
        System.out.println("Average memory used using for loop: " + averageMemoryUsedForLoop + " bytes");
    }
}
