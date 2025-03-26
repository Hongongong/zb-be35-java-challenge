package ExecutorService;

import java.time.LocalDateTime;
import java.util.concurrent.*;

public class ScheduledThreadPoolExample {
    public static void main(String[] args) {
        System.out.println("> Task : ScheduledThreadPoolExample.main()");

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); // 스레드 풀 1개 생성

        Runnable emailTask = () -> {
            String timestamp = LocalDateTime.now().toString();
            System.out.println("[ " + timestamp + " ] 📧 이메일 발송 : 1 (" + Thread.currentThread().getName() + ")");
        };

        // 5초 후 첫 실행, 이후 10초마다 반복 실행
        scheduler.scheduleAtFixedRate(emailTask, 5, 10, TimeUnit.SECONDS);
    }
}
