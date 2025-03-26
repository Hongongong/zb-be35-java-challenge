package CustomException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AgeRestrictedSystem {

    List<String> userList = new ArrayList<>();
    final static int adult = 18;
    private Scanner scanner = new Scanner(System.in);

    // 사용자 예외
    static class UnderageException extends Exception {
        public UnderageException(String message) {
            super(message);
        }
    }

    public void start() {

        while (true) {
            System.out.println("👤 사용자 등록 시스템 (register, list, exit)");
            System.out.print("명령 입력: ");
            String command = scanner.nextLine().toLowerCase();

            try {
                switch (command) {

                    case "register":
                        registerUser();
                        break;

                    case "list":
                        listUser();
                        break;

                    case "exit":
                        System.out.println("🚪 시스템 종료.");
                        return;

                    default:
                        System.out.println("⚠ 명령어가 잘못 입력되었습니다.");
                }
            } catch (UnderageException e) {
                System.out.println(e.getMessage());
            }

            System.out.println();
        }

    }

    private void registerUser() throws UnderageException {

        System.out.print("이름 입력: ");
        String name = scanner.nextLine();
        System.out.print("나이 입력: ");
        int age = Integer.parseInt(scanner.nextLine());

        // 미성년자 불가
        if(age < adult) {
            throw new UnderageException("⚠ 미성년자는 등록할 수 없습니다.");
        }

        userList.add(name + " (나이: " + age + ")");
        System.out.println("✅ 사용자 등록 완료!");

    }

    private void listUser() {

        System.out.println("📋 등록된 사용자 목록:");
        for(String s : userList) {
            System.out.println(s);
        }

    }


    public static void main(String[] args) {

        AgeRestrictedSystem system = new AgeRestrictedSystem();

        try {
            system.start();
        } finally {
            system.scanner.close(); // Scanner 리소스 정리
        }

    }



}
