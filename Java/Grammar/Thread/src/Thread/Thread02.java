package Thread;

/**
 * main 메서드가 종료되더라도 다른 Thread 가 실행 중 이면 프로그램이 종료되지 않음
 *  -> Thread 가 종료되어야 프로그램 종료
 */

public class Thread02 {
    static long startTime = 0;

    public static void main(String[] args) {
        Thread1 th1 = new Thread1();
        Thread2 th2 = new Thread2();

        th1.start();
        th2.start();

        startTime = System.currentTimeMillis();

        try {
            // join 메서드 = main 쓰레드가 다른 작업이 끝날 때 까지 기다리게 만듦
            th1.join();
            th2.join();
        } catch (InterruptedException e) {}

        // main
        System.out.println("소요시간 : " + (System.currentTimeMillis() - startTime));
    }

}

class Thread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print(new String("-"));
        }
    }
}

class Thread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print(new String("|"));
        }
    }
}
