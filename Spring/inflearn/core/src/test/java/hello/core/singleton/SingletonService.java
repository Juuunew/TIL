package hello.core.singleton;

public class SingletonService {

    /**
     * 싱글톤 패턴의 문제점
     *
     * 싱글톤 패턴을 구현하는 코드 자체가 많이 들어감.
     * 의존관계상 클라이언트가 구체 클래스에 의존 -> DIP 위반
     * 클라이언트가 구체 클래스에 의존해서 OCP원칙(개방-폐쇄원칙)을 위반할 가능성이 높다.
     * 테스트하기가 어려움
     * 내부 속성을 변경하거나 초기화 하기 어려움
     * private 생성자로 자식 클래스를 만들기 어려움
     * 유연성이 떨어짐
     * 안티패턴으로 불리기도 함
     */


    // static = class 레벨에 올라감
    // 딱 하나만 존재하게 됨
    private static final SingletonService instance = new SingletonService();

    // public 접근제어자를 통해 instance 객체가 필요하면 이 static 메서드를 통해서만 조회하도록 허용
    public static SingletonService getInstance() {
        return instance;
    }

    // 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막음
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
