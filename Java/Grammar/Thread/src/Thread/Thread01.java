package Thread;

public class Thread01 {
    public static void main(String[] args) {
        ThreadExtends t1 = new ThreadExtends();

        Runnable r = new ThreadImplements();
        Thread t2 = new Thread(r);

        /**
         * start() 를 호출했다고 즉시 실행되는 것이 아님.
         * 먼저 start() 생성된 순서로 실행되는 것이 아닌 OS 스케줄러가 실행 순서를 결정함.
         *
         * start 가 새로운 호출스택 생성
         *  -> 생성된 호출스택에 run 등록
         *  -> start 종료
         *
         * 각각의 Thread 가 자기만의 호출 스택을 소유
         *  -> 서로 독립적인 작업 수행 가능
         */
        t1.start(); // 0
        t2.start(); // 1

    }
}

/**
 * 자바는 단일상속 원칙
 * 상속을 받으면 다른 클래스를 상속받기 어려우니
 * 인터페이스 구현 방식이 좀 더 나음
 */

// Thread 클래스 상속
class ThreadExtends extends Thread {
    @Override
    public void run() {
        // Thread 가 수행할 작업 작성
        for (int i = 0; i < 5; i++) {
            System.out.println(0);
        }
    }
}

// Runnable 인터페이스 구현해서 쓰레드를 구현
class ThreadImplements implements Runnable {
    @Override
    public void run() {
        // Thread 가 수행할 작업 작성
        for (int i = 0; i < 5; i++) {
            System.out.println(1);
        }
    }
}
