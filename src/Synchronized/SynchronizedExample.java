package Synchronized;

class Counter100 {

    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    /* 위와 같음
    public void increment() {
        synchronized(this) {
            count++;
        }
    }
     */

    public int getCount() {
        return count;
    }

}

public class SynchronizedExample {

    public static void main(String[] args) throws InterruptedException {

        Counter100 counter = new Counter100();

        Thread thread1 = new Thread(() -> {

            for(int i = 0; i< 1000; i++) {
                counter.increment();
            }

        });

        Thread thread2 = new Thread(() -> {

            for(int i = 0; i< 1000; i++) {
                counter.increment();
            }

        });

        thread1.start();
        thread2.start();

        // join() 은 main 스레드가 해당 스레드의 작업이 끝날 때까지 기다리라는 의미
        // 모든 증가 작업이 끝난 다음에 최종 값 출력하는 것을 보장하기 위해 사용
        thread1.join();
        thread2.join();

        System.out.println("최종 값 : " + counter.getCount());

    }

}
