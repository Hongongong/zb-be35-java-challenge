package CustomException;

import java.util.Scanner;

// 사용자 정의 예외 (Checked Exception)
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}


public class AgeValiator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("나이를 입력하세요 (종료: exit)");

        while (true) {

            System.out.print("입력: ");

            String input = scanner.nextLine().trim();

            if(input.equals("exit")) {
                System.out.println("프로그램 종료");
                break;
            }

            try {
                int age = Integer.parseInt(input);

                validateAge(age);

                System.out.println("유효한 나이입니다.");
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요.");
            } catch (InvalidAgeException e) {
                System.out.println("입력 오류: " + e.getMessage());
            }

        }

        scanner.close();

    }

    private static void validateAge(int age) throws InvalidAgeException{
        if(age < 0 || age > 120) {
            throw new InvalidAgeException("나이는 0 이상 120 이하만 입력 가능합니다.");
        }
    }

}
