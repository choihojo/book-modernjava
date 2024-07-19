package ch19;

import java.util.stream.IntStream;

public class StreamExample {

    private static IntStream numbers() {
        return IntStream.iterate(2, n -> n + 1);
    }

    private static int head(IntStream numbers) {
        return numbers.findFirst()
                      .getAsInt();
    }

    private static IntStream tail(IntStream numbers) {
        return numbers.skip(1);
    }

    private static IntStream primes(IntStream numbers) {
        System.out.println("Call primes method");
//        int head = head(numbers);
        int head = 2;
        return IntStream.concat(IntStream.of(head), primes(tail(numbers).filter(n -> n % head != 0)));
    }

    public static void main(String[] args) {
        IntStream numbers = StreamExample.numbers();
        IntStream primes = StreamExample.primes(numbers);
    }

}
