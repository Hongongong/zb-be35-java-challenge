package ThreadLocal;

public class ThreadLocalExample {
    private static ThreadLocal<Integer> threadLocalValue = ThreadLocal.withInitial(() -> 0); // 초기 값 설정

    public static void main(String[] args) {
        Runnable task = () -> {
            threadLocalValue.set((int)(Math.random() * 100));
            System.out.println(Thread.currentThread().getName() + " - value: " + threadLocalValue.get());
            threadLocalValue.remove();
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}














