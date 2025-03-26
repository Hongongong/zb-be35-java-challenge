package ExecutorService;

import java.util.concurrent.*;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3); // 3개의 스레드

        for (int i = 0; i <= 5; i++) {
            final int taskId = i;
            executor.execute(() -> {
                System.out.println("🟢 작업 " + taskId + " 실행 (스레드: " + Thread.currentThread().getName() + ")");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("✅ 작업 " + taskId + " 완료");
                }
            });
        }

        executor.shutdown(); // 스레드 종료
    }
}






