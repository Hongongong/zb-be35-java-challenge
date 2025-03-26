package Synchronized;

import java.util.LinkedList;
import java.util.Queue;

class DataQueue {
    private final Queue<Integer> queue;
    public final int MAX_SIZE;

    public DataQueue(int queueMaxSize) {
        this.queue = new LinkedList<>();
        this.MAX_SIZE = queueMaxSize; // 큐 크기 10으로 설정
    }

    public synchronized void add(int data) {
        while (queue.size() >= MAX_SIZE) {
            try {
                // 스레드 sleep, wait, join 사용 시 InterruptedException 발생 가능
                // 발생 시 인터럽트 상태 초기화됨
                wait(); // 큐가 가득 찬 경우 기다림
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 예외 발생 시 초기화된 인터럽트 상태 복구
            }
        }
        queue.offer(data); // 데이터 큐에 데이터 삽입
        System.out.println("📦 생산: " + data);
        notifyAll(); // 큐에 데이터 삽입 후 대기중인 소비자 모두 깨움 (지금은 생산/소비자 1개씩이지만 더 있으면 all 필수)
    }

    public synchronized int remove() {
        while (queue.isEmpty()) {
            try {
                wait(); // 큐가 비어 있는 경우 기다림
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        int data = queue.poll(); // 데이터 큐에 데이터 빼냄 (선입선출)
        System.out.println("📥 소비: " + data);
        notifyAll(); // 큐의 데이터 소비 후 대기중인 생산자 모두 깨움
        return data;
    }
}

class Producer implements Runnable {
    private final DataQueue queue;

    public Producer(DataQueue queue) {
        this.queue = queue;
    }

    public void run() {
        for (int i = 1; i <= queue.MAX_SIZE; i++) {
            queue.add(i);
            try {
                Thread.sleep(100); // 생산 텀 (소비보다 조금 빠르게 설정해서 큐에 데이터가 쌓이도록 함)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer implements Runnable {
    private final DataQueue queue;

    public Consumer(DataQueue queue) {
        this.queue = queue;
    }

    public void run() {
        for (int i = 1; i <= queue.MAX_SIZE; i++) {
            queue.remove();
            try {
                Thread.sleep(150); // 소비 텀
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class ProducerConsumerExample {
    public static void main(String[] args) {
        System.out.println("> Task : ProducerConsumerExample.main()");

        DataQueue queue = new DataQueue(10); // 큐 크기(생산/소비 수) 설정

        Thread producer = new Thread(new Producer(queue)); // 생산자 생성
        Thread consumer = new Thread(new Consumer(queue)); // 소비자 생성

        producer.start(); // 생산 실행
        consumer.start(); // 소비 실행
    }
}
