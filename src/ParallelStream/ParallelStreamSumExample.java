package ParallelStream;

import java.util.stream.LongStream;

public class ParallelStreamSumExample {
    public static void main(String[] args) {
        System.out.println("> Task : ParallelStreamSumExample.main()");

        // 순차 스트림 합계
        long start = System.currentTimeMillis();
        long sumSequential = LongStream.rangeClosed(1, 500_000_000L).sum();
        long end = System.currentTimeMillis();
        System.out.println("🚀 순차 스트림 합계: " + sumSequential + " (시간: " + (end - start) + "ms)");

        // 병렬 스트림 합계
        start = System.currentTimeMillis();
        long sumParallel = LongStream.rangeClosed(1, 500_000_000L)
                .parallel()
                .sum();
        end = System.currentTimeMillis();
        System.out.println("🔥 병렬 스트림 합계: " + sumParallel + " (시간: " + (end - start) + "ms)");
    }
}
