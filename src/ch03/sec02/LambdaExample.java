package ch03.sec02;

import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaExample {

    public static void main(String[] args) {
    }

    public Callable<String> fetch() {
        return () -> "test";
    }

}
