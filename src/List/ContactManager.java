package List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/*
* 연락처 관리 프로그램
* List 인터페이스와 ArrayList 사용
* 연락처 추가 (이름, 전화번호), 연락처 삭제, 연락처 검색 (이름 기준),
* 연락처 목록 출력 (정렬, 이름 오름차순)의 기능을 만족하는 프로그램을 작성하세요.
* */

class Contact implements Comparable<Contact>{

    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return name + " - " + phoneNumber;
    }

    // compareTo() 를 오버라이딩하여 객체 비교 기준 설정
    @Override
    public int compareTo(Contact other) {
        return this.name.compareTo(other.name); // 이름 기준 오름차순 정렬
    }
}

public class ContactManager {

    private List<Contact> contactList = new ArrayList<>();
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void start() throws IOException {
        while (true) {
            System.out.println("📞 연락처 관리 시스템 (add, remove, list, search, exit)");
            System.out.print("명령 입력: ");
            String command = br.readLine().toLowerCase();

            switch (command) {

                case "add":
                    addContact();
                    break;

                case "search":
                    searchContact();
                    break;

                case "list":
                    listContact();
                    break;

                case "remove":
                    removeContact();
                    break;

                case "exit":
                    System.out.println("🚪 종료합니다.");
                    return;

                default:
                    System.out.println("⚠ 명령어가 잘못 입력되었습니다.");
            }

            System.out.println();
        }
    }

    private void addContact() throws IOException {

        System.out.print("이름: ");
        String name = br.readLine();

        System.out.print("전화번호: ");
        String phoneNumber = br.readLine();

        contactList.add(new Contact(name, phoneNumber));
        System.out.println("✅ 연락처가 추가되었습니다.");

    }

    private void searchContact() throws IOException {

        System.out.print("검색할 이름: ");
        String name = br.readLine();

        for(Contact contact : contactList) {
            if(contact.getName().equals(name)) {
                System.out.println(contact);
                return;
            }
        }
        System.out.println("⚠ 해당 이름의 연락처가 없습니다.");

    }

    private void listContact() {

        if(contactList.isEmpty()) {
            System.out.println("❌ 연락처 목록이 비어 있습니다.");
            return;
        }

        Collections.sort(contactList); // compareTo() 기준으로 정렬 수행

        for(Contact contact : contactList) {
            System.out.println(contact);
        }

    }

    private void removeContact() throws IOException {

        System.out.print("삭제할 연락처 이름: ");
        String name = br.readLine();

        // for-each문으로 반복 중 리스트 변경보다 itrator 사용하는 것이 안전함
        Iterator<Contact> iterator = contactList.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getName().equals(name)) {
                iterator.remove();
                System.out.println("✅ 삭제 완료!");
                return;
            }
        }
        System.out.println("⚠ 해당 이름의 연락처가 없습니다.");


    }


    public static void main(String[] args) throws IOException {

        ContactManager manager = new ContactManager();

        manager.start();
        manager.br.close();

    }

}
