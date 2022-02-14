package Thread;

/**
 * Daemon Thread
 *  -> 일반 쓰레드(non-daemon Thread)의 작업을 돕는 보조적인 역할 수행
 *  -> 일반 쓰레드가 모두 종료되면 자동적으로 종료됨
 *  -> 가비지 컬렉터, 자동저장, 화면 자동갱신 등에 사용됨
 *  -> 무한루프와 조건문을 이용, 실행 후 대기하다가 특정조건이 만족되면 작업을 수행하고 다시 대기하도록 작성
 *  -> setDaemon(boolean on)은 반드시 start()를 호출하기 전에 실행되어야 한다.
 *      -> 그렇지 않을 경우 IllegalThreadStateException 이 발생
 */

public class Thread08 implements Runnable {

    static boolean autoSave = false;

    public static void main(String[] args) {
        /**
         * main(일반) 쓰레드
         */
        Thread t = new Thread(new Thread08());

        t.setDaemon(true);  // 없으면 종료되지 않음
        t.start();

        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);

            if (i == 5) {
                autoSave = true;
            }
        }
        System.out.println("프로그램을 종료합니다");
    }

    /**
     * 데몬쓰레드
     *  -> 일반쓰레드가 하나도 없을 때 자동종료
     */
    @Override
    public void run() {
        // 무한루프
        while (true) {
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // autoSave = true 면 autoSave() 호출
            if (autoSave) {
                autoSave();
            }
        }
    }

    public void autoSave() {
        System.out.println("작업파일이 자동저장 되었습니다.");
    }
}
