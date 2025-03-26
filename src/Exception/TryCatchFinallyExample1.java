package Exception;

public class TryCatchFinallyExample1 {
    public static void main(String[] args) {
        try {
            System.out.println("# try 블록 실행");
            int result = 10 / 0; // 예외 발생 (ArithmeticException)
            System.out.println("이 코드는 실행되지 않음!");
        } catch (ArithmeticException e) {
            System.out.println("# catch 블록 실행: " + e.getMessage());
        } finally {
            System.out.println("# finally 블록 실행");
        }
    }
}
