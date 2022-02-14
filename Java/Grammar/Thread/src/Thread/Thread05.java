package Thread;

import javax.swing.*;

public class Thread05 {
    public static void main(String[] args) {

        /**
         * 싱글 Thread
         *
         * 입출력 Blocking
         * A 작업 실행 후 B 작업 실행
         *  -> A 작업을 실행하지 않으면 B 작업 실행 불가
         */

        // A 작업
        String input = JOptionPane.showInputDialog("값을 입력하세요.");
        System.out.println("입력한 값은" + input + "입니다.");

        // B 작업
        for (int i = 10; i > 0; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000); // 1초간 시간을 지연
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
