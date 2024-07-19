package ch19;

import java.util.function.Predicate;

public class Empty<T> implements MyList<T> {

    public T head() {
        throw new UnsupportedOperationException();
    }

    public MyList<T> tail() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MyList<T> filter(Predicate<T> p) {
        return null;
    }

}
