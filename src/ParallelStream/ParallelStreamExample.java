package ParallelStream;

import java.util.List;
import java.util.stream.LongStream;

public class ParallelStreamExample {
    public static void main(String[] args) {
        List<Long> numbers = LongStream.rangeClosed(1, 100_000_000).boxed().toList();

        // sequential
        long start = System.currentTimeMillis();
        long sumSequential = numbers.stream()
                .mapToLong(Long::longValue)
                .sum();
        long end = System.currentTimeMillis();
        System.out.println("sumSequential = " + sumSequential + ", Time = " + (end - start) + "ms");

        // parallel stream
        start = System.currentTimeMillis();
        long sumParallelStream = numbers.parallelStream()
                .mapToLong(n -> n)
                .sum();
        end = System.currentTimeMillis();
        System.out.println("sumParallelStream = " + sumParallelStream + ", Time = " + (end - start) + "ms");
    }
}
