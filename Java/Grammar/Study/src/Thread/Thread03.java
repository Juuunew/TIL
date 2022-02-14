package Thread;

/**
 * 싱글 Thread
 *  -> 먼저 실행한 작업이 끝나야 다음 작업이 실행됨
 */
public class Thread03 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 300; i++) {
            System.out.printf("%s", new String("-"));
        }

        System.out.print("소요시간 1 : " + (System.currentTimeMillis() - startTime));

        for (int i = 0; i < 300; i++) {
            System.out.printf("%s", new String("|"));
        }

        System.out.print("소요시간 2 : " + (System.currentTimeMillis() - startTime));
    }
}
