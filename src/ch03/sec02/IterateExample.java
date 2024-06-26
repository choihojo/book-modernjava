package ch03.sec02;

import java.util.concurrent.Callable;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IterateExample {

    public static void main(String[] args) {
        Stream.iterate(0, n -> {
                System.out.println("111");
            return n + 2;
            })
            .limit(10)
            .forEach(System.out::println);

        IntStream.iterate(0, n -> n < 100, n -> n + 4)
            .forEach(System.out::println);

        IntStream.iterate(0, n -> n + 4)
            .takeWhile(n -> n < 100)
            .forEach(System.out::println);
    }

}
