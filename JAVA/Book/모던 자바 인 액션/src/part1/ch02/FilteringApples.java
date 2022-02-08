package part1.ch02;

import part1.common.Apple;
import part1.common.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static part1.common.Color.GREEN;
import static part1.common.Color.RED;


public class FilteringApples {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED));

        // 두 번째 시도
        List<Apple> greenApples2 = filterApplesByColor(inventory, GREEN);
        System.out.println("greenApples2 = " + greenApples2);

        List<Apple> redApples2 = filterApplesByColor(inventory, RED);
        System.out.println("redApples2 = " + redApples2);

        // 세 번째 시도
        List<Apple> greenApples3 = filterApples(inventory, GREEN, 0, true);
        System.out.println("greenApples3 = " + greenApples3);

        List<Apple> heavyApples3 = filterApples(inventory, null, 150, false);
        System.out.println("heavyApples3 = " + heavyApples3);

        // 네 번째 시도
        List<Apple> heavyApples4 = filter(inventory, new AppleHeavyWeightPredicate());
        System.out.println("heavyApples4 = " + heavyApples4);

        List<Apple> redAndHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());
        System.out.println("redAndHeavyApples = " + redAndHeavyApples);

        // 다섯 번째 시도 - 익명 클래스 사용
        List<Apple> redApples = filter(inventory, new ApplePredicate() {    // filterApples 메서드의 동작을 직접 파라미터화
            @Override
            public boolean test(Apple apple) {
                return RED.equals(apple.getColor());
            }
        });
        System.out.println("redApples = " + redApples);

        // 여섯 번째 시도 - 람다 표현식 사용
        List<Apple> result = filter(inventory, (Apple apple) -> RED.equals(apple.getColor()));
        System.out.println("result = " + result);

        // 일곱 번째 시도
        List<Apple> redApples7 = filter(inventory, (Apple apple) -> RED.equals(apple.getColor()));
        System.out.println("redApples7 = " + redApples7);

    }

    // 첫 번째 시도 - 녹색 사과 필터링
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();         // 사과 누적 리스트
        for (Apple apple : inventory) {
            if (GREEN.equals(apple.getColor())) {       // 녹색 사과만 선택
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 첫 번째 시도 후 요구사항에 빨간 사과 필터링도 추가
     *  -> 비슷한 코드가 반복 존재한다면 그 코드를 추상화
     */

    // 두 번째 시도 - 색을 파라미터화
    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    // 두 번째 시도 - 무게 정보 파라미터화
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 두 번째 시도의 코드는 색 필터링 코드와 무게 정보 필터링 코드가 대부분 중복된다.
     * 이는 소프트웨어 공학의 DRY(don't repeat yourself - 같은 것을 반복하지 말 것) 원칙위배
     * -> 모든 속성으로 필터링 (플래그 추가) - 비추천
     */

    // 세번째 시도 - 가능한 모든 속성으로 필터링
    public static List<Apple> filterApples(List<Apple> inventory, Color color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ((flag && apple.getColor().equals(color)) || (!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 동작 파라미터화를 이용하여 유연성 얻어오기
     */

    // 네 번째 시도 - 추상적 조건으로 필터링
    public static List<Apple> filter(List<Apple> inventory, ApplePredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    // 일곱 번째 시도 - 리스트 형식으로 추상화
    public interface Predicate<T> {
        boolean test(T t);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

}
