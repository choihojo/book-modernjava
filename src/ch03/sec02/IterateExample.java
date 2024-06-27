package ch03.sec02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IterateExample {
    static int test = 0;
    int test2 = 0;

    public static void main(String[] args) {
        Stream.iterate(0, n -> {
                System.out.println("111");
            return n + 2;
            })
            .limit(10)
            .forEach(System.out::println);

        List<Integer> list = new ArrayList<>();
        list.add(1);

        // capturing lambda와 관련한 예제
        // 지역 변숫값은 스택에 존재하므로 자신을 정의한 스레드와 생존을 같이 해야 하며 따라서 지역 변수는 final이다.
        // 인스턴스 변수는 스레드가 공유하는 힙에 존재하므로 특별한 제약이 없다.
        int portNumber = 1337;
        Runnable r = () -> System.out.println(portNumber);
//        portNumber = 2337;
        Runnable r2 = () -> System.out.println(test);
        test = 3;
        IterateExample iterateExample = new IterateExample();
        Runnable r5 = () -> System.out.println(iterateExample.test2);
        iterateExample.test2 = 1000;

        // functional interface의 abstract class 시그니처는 람다 표현식의 시그니처를 가리킨다.
        // 람다 표현식의 시그니처를 서술하는 메서드를 함수 디스크립터라고 한다.

        // 람다의 형식 검사: 람다가 사용되는 context를 이용해서 람다의 type을 추론할 수 있다.
        // 여기에서 람다 표현식의 context는 target type인 Object이다. (참고로 target type이란 어떤 context에서 기대되는 람다 표현식의 형식을 말한다.)
        // 그런데 Object는 functional interface가 아니므로 컴파일 에러가 발생한다.
//        Object o = () -> { System.out.println("Tricky Example"); };
        // 그래서 아래 둘 중 하나의 방법을 택해야 한다.
        // () -> void 형식의 함수 디스크립터를 갖는 Runnable로 대상 형식을 바꾼다.
        Runnable r3 = () -> { System.out.println("Tricky Example"); };
        // 람다 표현식을 명시적으로 대상 형식을 제공하는 Runnable로 캐스팅한다.
        Object o = (Runnable) () -> { System.out.println("Tricky Example"); };

        IntStream.iterate(0, n -> n < 100, n -> n + 4)
            .forEach(System.out::println);

        IntStream.iterate(0, n -> n + 4)
            .takeWhile(n -> n < 100)
            .forEach(System.out::println);

//        useLambda();
    }

//    // 지역변수를 캡처하여 사용하는 람다를 외부로 반환하는 메서드
//    private static Runnable getLambda() {
//        int localVariable = 1000; // 지역변수 localVariable
//
//        return () -> {
//            System.out.println(localVariable);
//            localVariable += 5;
//        }; // 자유변수 localVariable
//    }

//    static void useLambda() {
//        Runnable r = getLambda();
//
//        // 자유변수 localVariable에 담긴 1000에 5를 더한 값을 반환 받고 변수 actual에 저장
//        r.run();
//    }

}
