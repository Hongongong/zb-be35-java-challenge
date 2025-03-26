package Map;

import java.util.HashMap;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();

        // 추가
        hashMap.put("A", "a");
        hashMap.put("B", "b");
        System.out.println(hashMap); // {A=a, B=b}

        // 삭제
        hashMap.remove("A");
        System.out.println(hashMap); // {B=b}

        // 중복값 추가
        hashMap.put("B", "bbbb");
        System.out.println(hashMap); // {B=bbbb}

        // 조회
        System.out.println(hashMap.get("B")); // bbbb
        System.out.println(hashMap.get("A")); // 존재하지 않는 key 조회하면 null 출력

        System.out.println(hashMap.getOrDefault("A", "없을때 값")); // null 대신 지정한 default값 출력
    }
}
