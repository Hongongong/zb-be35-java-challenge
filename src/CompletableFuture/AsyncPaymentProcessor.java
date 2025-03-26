package CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class AsyncPaymentProcessor {
    public static void main(String[] args) {
        System.out.println("> Task : AsyncPaymentProcessor.main()");

        int amount = 50_000;
        System.out.println("💳 결제 요청 중.. (" + amount + "원)");

        CompletableFuture
                .supplyAsync(() -> {
                    try {
                        Thread.sleep(3500); // 3초(3000) 이상 시 실패
                        return amount;
                    } catch (InterruptedException e) {
                        throw new RuntimeException("❌ 결제 중 오류 발생");
                    }
                })
                .orTimeout(3, TimeUnit.SECONDS) // 최대 3초 대기
                .thenAccept(result -> System.out.println("✅ 결제 완료: " + result + "원"))
                .exceptionally(e -> {
                    System.out.println("⚠ 결제 실패: null");
                    return null;
                });

        // 메인 스레드 종료 방지
        // 비동기 작업 완료 대기
        try {
            Thread.sleep(5000); // 충분한 대기 시간
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
