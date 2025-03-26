package Set;

/*
 * 중복 없는 회원 관리 시스템
 * Set 인터페이스와 HashSet 사용
 * 회원 ID 추가 (중복된 ID 등록 불가), 회원 ID 삭제, 목록 출력,
 * 회원을 조회하는 프로그램을 작성하세요.
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;


public class MemberManager {

    private Set<String> memberIdSet = new HashSet<>();
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private void start() throws IOException {

        while (true) {
            System.out.println("👥 회원 관리 시스템 (add, remove, list, check, exit)");
            System.out.print("명령 입력: ");
            String command = br.readLine().toLowerCase();

            switch (command) {

                case "add":
                    addMember();
                    break;

                case "check":
                    checkMember();
                    break;

                case "list":
                    listMember();
                    break;

                case "remove":
                    removeMember();
                    break;

                case "exit":
                    System.out.println("🚪 프로그램 종료.");
                    return;

                default:
                    System.out.println("⚠ 명령어가 잘못 입력되었습니다.");
            }

            System.out.println();

        }
    }

    private void addMember() throws IOException {


        System.out.print("추가할 회원 ID: ");
        String id = br.readLine();

        if (memberIdSet.contains(id)) {
            System.out.println("⚠ 이미 존재하는 회원 ID입니다.");
            return;
        }

        memberIdSet.add(id); // Set은 중복값 저장 안함
        System.out.println("✅ 회원이 추가되었습니다.");



    }

    private void checkMember() throws IOException {

        System.out.print("검색할 회원 ID: ");
        if(memberIdSet.contains(br.readLine())) {
            System.out.println("✅ 회원이 존재합니다.");
        } else {
            System.out.println("⚠ 회원이 존재하지 않습니다.");
        }

    }

    private void listMember() {

        if(memberIdSet.isEmpty()) {
            System.out.println("❌ 회원이 한 명도 없습니다.");
            return;
        }

        for(String id : memberIdSet) {
            System.out.println(id);
        }

    }

    private void removeMember() throws IOException {

        System.out.print("삭제할 회원 ID: ");
        String id = br.readLine();

        if(memberIdSet.contains(id)) {
            memberIdSet.remove(id);
            System.out.println("✅ 회원이 삭제되었습니다.");
        } else {
            System.out.println("⚠ 회원이 존재하지 않습니다.");
        }



    }

    public static void main(String[] args) throws IOException {

        MemberManager manager = new MemberManager();

        manager.start();
        manager.br.close();

    }

}
