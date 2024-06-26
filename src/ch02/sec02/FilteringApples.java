package ch02.sec02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * 이론적으로는 Collections.sort(list, comparator)가 아니라 list.sort(comparator)를 수행하는 것이 적절하다.
 * 하지만 자바 8이 등장하기 전까지는 이 문제를 해결한다는 것은 불가능에 가까웠다.
 * 인터페이스를 업데이트하려면 해당 인터페이스를 구현하는 모든 클래스도 업데이트해야 했기 때문이다.
 * 자바 8에서는 이러한 문제를 디폴트 메서드를 도입하여 해결하였다.
 */

public class FilteringApples {

  public static void main(String... args) {
    List<Apple> inventory = Arrays.asList(
        new Apple(80, Color.GREEN),
        new Apple(155, Color.GREEN),
        new Apple(120, Color.RED));

//    // [Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
//    List<Apple> greenApples = filterApplesByColor(inventory, Color.GREEN);
//    System.out.println(greenApples);
//
//    // [Apple{color=RED, weight=120}]
//    List<Apple> redApples = filterApplesByColor(inventory, Color.RED);
//    System.out.println(redApples);
//
//    // [Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
//    List<Apple> greenApples2 = filter(inventory, new AppleColorPredicate());
//    System.out.println(greenApples2);
//
//    // [Apple{color=GREEN, weight=155}]
//    List<Apple> heavyApples = filter(inventory, new AppleWeightPredicate());
//    System.out.println(heavyApples);
//
//    // []
//    List<Apple> redAndHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());
//    System.out.println(redAndHeavyApples);
//
//    // [Apple{color=RED, weight=120}]
//    List<Apple> redApples2 = filter(inventory, new ApplePredicate() {
//      @Override
//      public boolean test(Apple a) {
//        return a.getColor() == Color.RED;
//      }
//    });
//    System.out.println(redApples2);

    prettyPrintApple(inventory, new WeightFormatter());
  }

  public static class WeightFormatter implements AppleFormatter {
    @Override
    public String format(Apple apple) {
      return apple.weight > 130 ? apple.weight + ": HEAVEY" : apple.weight + ": LIGHT";
    }
  }

  public static List<Apple> filterGreenApples(List<Apple> inventory) {
    List<Apple> result = new ArrayList<>();
//    result.sort()
//    Collections.sort();
    for (Apple apple : inventory) {
      if (apple.getColor() == Color.GREEN) {
        result.add(apple);
      }
    }
    return result;
  }

  public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (apple.getColor() == color) {
        result.add(apple);
      }
    }
    return result;
  }

  public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (apple.getWeight() > weight) {
        result.add(apple);
      }
    }
    return result;
  }

  public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (p.test(apple)) {
        result.add(apple);
      }
    }
    return result;
  }

  enum Color {
    RED,
    GREEN
  }

  public static class Apple {

    private int weight = 0;
    private Color color;

    public Apple(int weight, Color color) {
      this.weight = weight;
      this.color = color;
    }

    public int getWeight() {
      return weight;
    }

    public void setWeight(int weight) {
      this.weight = weight;
    }

    public Color getColor() {
      return color;
    }

    public void setColor(Color color) {
      this.color = color;
    }

    @SuppressWarnings("boxing")
    @Override
    public String toString() {
      return String.format("Apple{color=%s, weight=%d}", color, weight);
    }

  }

  interface ApplePredicate {

    boolean test(Apple a);

  }

  static class AppleWeightPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
      return apple.getWeight() > 150;
    }

  }

  static class AppleColorPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
      return apple.getColor() == Color.GREEN;
    }

  }

  static class AppleRedAndHeavyPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
      return apple.getColor() == Color.RED && apple.getWeight() > 150;
    }

  }

  public static void prettyPrintApple(List<Apple> inventory, AppleFormatter appleFormatter) {
    StringBuilder sb = new StringBuilder();
    for (Apple apple : inventory) {
      String output = appleFormatter.format(apple);
      sb.append(output).append("\n");
    }
    System.out.println(sb);
  }

  public interface AppleFormatter {
    String format(Apple apple);
  }

}
