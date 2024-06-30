package ch06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorExample {

    private static int start = 0;

    public static void main(String[] args) throws InterruptedException {
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        int total = stream.collect(Collectors.reducing(0, Integer::sum));
        System.out.println(total);


//        threadA
//        Supplier<Integer> supplier1 = integerSupplier2(); // () -> 1

        start++;
        start++;
//        Supplier<Integer> supplier2 = integerSupplier2(); // () -> 3

        List<Integer> list = new ArrayList<>();
        while (true) {
            System.out.println(start);
            list.add(start++);
            Thread.sleep(1000);
        }

    }

    List<Data> list = Arrays.asList(new Data("Alice"), new Data("Bob"), new Data("Charlie"));
    // reducing은 BinaryOperator<T> (BiFunction<T, T, T>)를 인수로 받음
    // reducing은 두 인수를 받아 같은 형식을 반환하는 함수를 인수로 받는데 여기서는 Data를 인수로 받아서 문자열을 반환하므로 컴파일 에러가 발생함
//    String shortData = list.stream().collect(Collectors.reducing((d1, d2) -> d1.getName() + d2.getName())).get();
    String shortData2 = list.stream().collect(Collectors.reducing("", Data::getName, (s1, s2) -> s1 + s2));

    public Supplier<Integer> integerSupplier() {
        start++;
        return () -> start;
    }

    public static Supplier<Integer> integerSupplier2(final int a) {
        final int localVar = start;
//        localVar++;
        return () -> localVar;
//        threadA: () -> 0;
//        threadB: () -> 2; (10분 뒤) start++ start
    }

}

class Data {
    private String name;

    public Data(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
