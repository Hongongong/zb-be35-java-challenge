package Map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class StudentGradeManager {

    private Map<String, List<Integer>> studentGradeMap = new HashMap<>();
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private void start() throws IOException {

        while (true) {
            System.out.println("🎓 성적 관리 시스템 (add, remove, grade, average, list, exit)");
            System.out.print("명령 입력: ");
            String command = br.readLine().toLowerCase();

            switch (command) {

                case "add":
                    addStudent();
                    break;

                case "grade":
                    addGrade();
                    break;

                case "average":
                    averageGrade();
                    break;

                case "list":
                    listStudent();
                    break;

                case "remove":
                    removeStudent();
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

    private void addStudent() throws IOException {

        System.out.print("학생 이름: ");
        String name = br.readLine();

        if(studentGradeMap.containsKey(name)) {
            System.out.println("⚠ 이미 존재하는 학생입니다.");
            return;
        }

        studentGradeMap.put(name, new ArrayList<>());
        System.out.println("✅ 학생이 추가되었습니다.");

    }

    private void addGrade() throws IOException {

        System.out.print("성적 추가할 학생 이름: ");
        String name = br.readLine();

        if(!studentGradeMap.containsKey(name)) {
            System.out.println("⚠ 해당 학생이 존재하지 않습니다.");
            return;
        }

        System.out.print("성적 입력: ");
        try {
            int grade = Integer.parseInt(br.readLine().trim());
            if (grade < 0) {
                System.out.println("⚠ 성적은 0 이상 입력해야 합니다.");
                return;
            }
            studentGradeMap.get(name).add(grade);
            System.out.println("✅ 성적이 추가되었습니다.");
        } catch (NumberFormatException e) {
            System.out.println("⚠ 올바른 숫자를 입력하세요.");
        }


    }

    private void averageGrade() throws IOException {

        System.out.print("평균 조회 학생 이름: ");
        String name = br.readLine();

        if (!studentGradeMap.containsKey(name)) {
            System.out.println("⚠ 해당 학생이 존재하지 않습니다.");
            return;
        }

        List<Integer> gradeList = studentGradeMap.get(name);

        if (gradeList.isEmpty()) {
            System.out.println("⚠ " + name + "의 성적이 없습니다.");
            return;
        }

        int sum = 0;
        for(int grade : gradeList) {
            sum += grade;
        }

        double average = (double) sum / gradeList.size();
        System.out.printf("📊 %s의 평균 성적: %.2f%n", name, average); // %n : printf()에서 사용하는 개행 문자

    }

    private void listStudent() {

        if (studentGradeMap.isEmpty()) {
            System.out.println("❌ 등록된 학생이 없습니다.");
            return;
        }

        System.out.println("📋 학생 목록: ");

        Set<String> nameSet = studentGradeMap.keySet();
        for(String name : nameSet) {
            System.out.println(name + " - 성적: " + studentGradeMap.get(name));
        }

    }

    private void removeStudent() throws IOException {

        System.out.print("삭제 학생 이름: ");
        String name = br.readLine();

        if(!studentGradeMap.containsKey(name)) {
            System.out.println("⚠ 해당 학생이 존재하지 않습니다.");
            return;
        }

        studentGradeMap.remove(name);
        System.out.println("✅ 학생이 삭제되었습니다.");

    }

    public static void main(String[] args) throws IOException {

        StudentGradeManager manager = new StudentGradeManager();

        manager.start();
        manager.br.close();

    }

}
