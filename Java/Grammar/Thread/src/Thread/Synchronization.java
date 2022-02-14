package Thread;

/**
 * 쓰레드의 동기화 (synchronization)
 *  -> 멀티 쓰레드 프로세스에서는 다른 쓰레드의 작업에 영향을 미칠 수 있음
 *  -> 진행중인 작업이 다른 쓰레드에게 간섭받지 않게 하려면 "동기화(synchronization)" 필요
 *  -> 동기화를 하기 위해서는 간섭받지 않아야 하는 문장들을 "임계 영역" 으로 설정
 *      -> 임계영역은 락(lock)을 얻은 단 하나의 쓰레드만 출입가능(객체 1개에 락 1개)
 *
 * synchronized 로 임계영역 설정하는 방법
 *  1. 메서드 전체를 임계영역으로 지정
 *      -> public synchronized void calcSum(){}
 *
 *  2. 특정한 영역을 임계 영역으로 지정
 *      -> synchronized(객체의 참조변수) {}
 */

public class Synchronization {
    public static void main(String[] args) {
        Runnable r= new RunnableEx01();

        new Thread(r).start();  // ThreadGroup 에 의해 참조되므로 gc 대상이 아님
        new Thread(r).start();  // ThreadGroup 에 의해 참조되므로 gc 대상이 아님

    }
}

class Account {
    private int balance = 1000;

    public int getBalance() {
        return balance;
    }

    // 임계영역 설정
    public synchronized void withdraw(int money) {
        if (balance >= money) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance -= money;
        }
    }

    // 특정 영역 임계 영역 설정
    /*public void withdraw(int money) {
        synchronized (this) {
            if (balance >= money) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance -= money;
            }
        }
    }*/
}

class RunnableEx01 implements Runnable {
    Account acc = new Account();

    @Override
    public void run() {
        while (acc.getBalance() > 0) {
            int money = (int) (Math.random() * 3 + 1) * 100;
            acc.withdraw(money);
            System.out.println("acc.getBalance() = " + acc.getBalance());
        }
    }
}
