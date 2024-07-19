package ch19;

import java.util.function.Predicate;

public class MyLinkedList<T> implements MyList<T> {

    private final T head;
    private final MyList<T> tail;

    public MyLinkedList(T head, MyList<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    public T head() {
        return head;
    }

    public MyList<T> tail() {
        return tail;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public MyList<T> filter(Predicate<T> p) {
        return null;
    }

}
