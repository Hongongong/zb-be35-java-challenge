package ThreadLocal;

public class AuthenticationSystem {

    // ThreadLocal로 사용자 인증 정보를 저장
    private static final ThreadLocal<String> userAuthThreadLocal = new ThreadLocal<>();

    static class LoginTask implements Runnable {
        private final String userName;

        public LoginTask(String userName) {
            this.userName = userName;
        }

        @Override
        public void run() {
            try {
                // 사용자 로그인 정보 저장
                userAuthThreadLocal.set(userName);

                // 현재 스레드의 인증 정보 출력
                System.out.println(Thread.currentThread().getName() + " 로그인: " + userAuthThreadLocal.get());
            } finally {
                // 메모리 누수 방지
                userAuthThreadLocal.remove();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("> Task : AuthenticationSystem.main()");

        Thread thread1 = new Thread(new LoginTask("사용자 A"), "Thread-1"); // 스레드 이름 명시적으로 지정
        Thread thread2 = new Thread(new LoginTask("사용자 B"), "Thread-2");

        thread1.start();
        thread2.start();
    }
}