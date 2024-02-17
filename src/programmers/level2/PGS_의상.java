package programmers.level2;

// 10:12

// 각 카테고리 별로 안 입는 경우 + 1 해서 다 곱해주고, 다 안 입는 경우(1) 빼주면 됨.

import java.util.HashMap;
import java.util.HashSet;

class PGS_의상 {
    public int solution(String[][] clothes) {

        int answer = 1;

        HashMap<String, HashSet<String>> hashMap = new HashMap<>();
        for (String[] s : clothes) {
            HashSet temp = hashMap.getOrDefault(s[1], new HashSet<String>());
            temp.add(s[0]);
            hashMap.put(s[1], temp);
        }

//         System.out.println(hashMap.entrySet());

        for (String s : hashMap.keySet()) {
            answer *= hashMap.get(s).size() + 1;
        }

        return answer - 1;
    }
}
