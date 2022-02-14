package Thread;

import static Thread.Thread04.startTime;

/**
 * 멀티 Thread
 *  -> context switching 때문에 시간이 더 오래걸림
 */
public class Thread04 {
    static long startTime = 0;

    public static void main(String[] args) {
        MyThread1 th1 = new MyThread1();
        MyThread2 th2 = new MyThread2();

        th1.start();
        th2.start();

        startTime = System.currentTimeMillis();
    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.printf("%s", new String("-"));
        }
        System.out.print("소요시간 1 :" + (System.currentTimeMillis() - startTime));
    }
}

class MyThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.printf("%s", new String("|"));
        }
        System.out.print("소요시간 2 :" + (System.currentTimeMillis() - startTime));
    }
}