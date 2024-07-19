package ch19;

import java.util.List;

public class LazyEvaluationExample {

    /**
     * Eager Evaluation vs. Lazy Evaluation Eager Evaluation:
     * 1. 1~10까지 요소들 중 6보다 작은 값들을 구함 (1, 2, 3, 4, 5)
     * 2. 1번에서 구한 요소들 중 짝수를 구함 (2, 4)
     * 3. 2번에서 구한 요소들에 10을 곱함 (20, 40)
     * Lazy Evaluation:
     * 1. 각 요소들에 대해 순차적으로 6보다 작은지 검사, 짝수 검사, 10을 곱함을 수행함
     * 2. 그래서 Lazy Evaluation을 적절히 활용하면 불필요한 연산을 피할 수 있음
     */
    public static void main(String[] args) {
        final List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        final List<Integer> resultList = list.stream()
                                             .peek(System.out::println)
                                             .filter(i -> i < 6)
                                             .peek(System.out::println)
                                             .filter(i -> i % 2 == 0)
                                             .peek(System.out::println)
                                             .map(i -> i * 10)
                                             .peek(System.out::println)
                                             .toList();
        System.out.println(resultList);

        System.out.println("===");

        final Integer first = list.stream()
                                  .peek(System.out::println)
                                  .filter(i -> i < 6)
                                  .peek(System.out::println)
                                  .filter(i -> i % 2 == 0)
                                  .peek(System.out::println)
                                  .map(i -> i * 10)
                                  .peek(System.out::println)
                                  .findFirst()
                                  .orElse(null);

    }

}
