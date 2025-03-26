package Exception;

import java.util.Scanner;

public class ATMSystem {

    private static int balance = 10000; // 계좌 초기 잔액

    private static void processWithdrawal(int withdrawal) {

        if(withdrawal <= 0) {
            throw new IllegalArgumentException("⚠ 출금 금액은 1원 이상이어야 합니다.");
        }

        if (withdrawal > balance) {
            throw new IllegalArgumentException("⚠ 잔액 부족!");
        }

        balance -= withdrawal;
        System.out.println("✅ 출금 완료! 남은 잔액: " + balance);

    }

    public static void main(String[] args) {

        System.out.println("> Task : ATMSystem.main()");

        Scanner scanner = new Scanner(System.in);

        try {

            System.out.print("출금할 금액 입력: ");

            int withdrawal = Integer.parseInt(scanner.nextLine());

            processWithdrawal(withdrawal);

        } catch (NumberFormatException e) {
            System.out.println("⚠ 숫자만 입력 가능합니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // ⚠ 잔액 부족! / ⚠ 출금 금액은 1원 이상이어야 합니다.
        } finally {
            System.out.println("💰 거래 기록이 저장되었습니다.");

            scanner.close();
        }


    }

}
