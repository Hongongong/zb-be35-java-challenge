package Thread;

class MyThread100 extends Thread {
    @Override
    public void run() {
        System.out.println("hello, thread (스레드: " + Thread.currentThread().getName() + ")");
    }
}

class MyThread200 implements Runnable {
    @Override
    public void run() {
        System.out.println("hello, thread (runnable)(스레드: " + Thread.currentThread().getName() + ")");
    }
}

public class ThreadExample {

    public static void main(String[] args) {
        // Thread 클래스 상속해서 구현
        MyThread100 thread101 = new MyThread100();
        MyThread100 thread102 = new MyThread100();
        MyThread100 thread103 = new MyThread100();

        thread101.start();
        thread102.start();
        thread103.start();

        // Runnable 인터페이스 구현
        Thread thread201 = new Thread(new MyThread200());
        Thread thread202 = new Thread(new MyThread200());
        Thread thread203 = new Thread(new MyThread200());

        thread201.start();
        thread202.start();
        thread203.start();
    }

}
