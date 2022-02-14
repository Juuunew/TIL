package Thread;

/**
 * Thread 실행제어 method 중 static 인 method
 *  -> sleep(잠자기)
 *  -> yield(양보)
 *      -> 자기 자신에게만 동작
 *
 *  예외처리 필수 (InterruptedException 이 발생하면 깨어남)
 *
 *  특정 Thread 를 지정해서 멈추게 하는 것은 불가능능
*/
public class Thread09 {
    public static void main(String[] args) {
        Thread9 th9 = new Thread9();
        Thread10 th10 = new Thread10();

        th9.start();
        th10.start();

        delay(2 * 1000);
        System.out.print("<<main 종료>>");
    }

    static void delay(long millis) {
        try {
            //th9.sleep(2000);          // 특정 Thread 지정 불가능
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread9 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("-");
        }
        System.out.print("<<th1 종료>>");
    }
}

class Thread10 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("|");
        }
        System.out.print("<<th2 종료>>");
    }
}
