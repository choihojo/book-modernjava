package ch18;

public class Factorial {
    public static void main(String[] args) {
        long result = factorial(Integer.MAX_VALUE, 1);
        System.out.println(result);
    }

    public static long factorial(int n, int acc) {
        if (n == 0) {
            return acc;
        } else {
            return factorial(n - 1, n * acc);
        }
    }
}
