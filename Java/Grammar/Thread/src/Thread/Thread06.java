package Thread;

import javax.swing.*;

public class Thread06 {
    public static void main(String[] args) {

        /**
         * 멀티 Thread
         *
         * 입력받는 작업 (A)와 카운트 다운 하는 작업 (B)이 분리되어서 동시에 작업 수행
         */

        // A 작업
        Thread6 th6 = new Thread6();
        th6.start();

        String input = JOptionPane.showInputDialog("값을 입력하세요.");
        System.out.println("입력한 값은" + input + "입니다.");

    }
}

// B 작업
class Thread6 extends Thread {
    @Override
    public void run() {
        for (int i = 10; i > 0; i--) {
            System.out.println(i);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
