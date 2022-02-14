package Thread;

/**
 * 우선순위, 희망사항
 *
 * 윈도우 우선순위 = 32단계
 * 자바 쓰레드 우선순위 (1 ~ 10)
 *  int MAX_PRIORITY = 10    // 최대 우선순위
 *  int MIN_PRIORITY = 1     // 최소 우선순위
 *  int NORM_PRIORITY = 5    // 보통 우선순위 - default
 *
 * 우선순위가 높다고 작업이 먼저 끝나지는 않음
 *  -> 우선순위가 같은 경우 : 비슷한 시간을 할당받아서 작업
 *  -> 우선순위가 다른 경우 : 우선순위가 높은 Thread 에 조금 더 많은 시간을 할당
 *      -> 우선순위가 높은 작업이 더 일찍 마무리될 가능성이 높음
 */
public class Thread07 {
    public static void main(String[] args) {
        Thread7 th7 = new Thread7();
        Thread8 th8 = new Thread8();

        th8.setPriority(7);

        System.out.println("Priority of th7(-) = " + th7.getPriority());
        System.out.println("Priority of th8(|) = " + th8.getPriority());
        th7.start();
        th8.start();
    }
}

class Thread7 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("-");
            for (int x = 0; x < 10000000; x++); // 시간 지연을 위한 for 문
        }
    }
}

class Thread8 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("|");
            for (int x = 0; x < 10000000; x++);
        }
    }
}
