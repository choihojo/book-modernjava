package ch19;

public class MyListExample {

    public static void main(String[] args) {
        MyList<Integer> myList = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));

        LazyList<Integer> numbers = LazyList.from(2);
        int two = numbers.head();
        int three = numbers.tail()
                           .head();
        int four = numbers.tail()
                          .tail()
                          .head();
    }

}
