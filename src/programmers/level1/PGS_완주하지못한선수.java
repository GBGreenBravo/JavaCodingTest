package programmers.level1;

// 7:45

// ArrayList가 아닌, HashMap을 써야하는 문제.
// 데이터 검색이 더 자주 일어나므로, List보다 Map을 활용해야 함!

// getOrDefault() 써도 좋을 듯함.

import java.util.HashMap;

public class PGS_완주하지못한선수 {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> pp = new HashMap<>();
        for (String s : participant) {
            if (pp.containsKey(s)) pp.put(s, pp.get(s) + 1);
            else pp.put(s, 1);
        }
        for (String s : completion) {
            pp.put(s, pp.get(s) - 1);
        }
        for (String key : pp.keySet()) {
            if (pp.get(key) == 1) return key;
        }

        return "";
    }
}

// 효율성에서 시간 초과. 자료구조 바꾸자.
/*
class Solution {
    public String solution(String[] participant, String[] completion) {
        ArrayList<String> start = new ArrayList<>();
        for (String s : participant) {
            start.add(s);
        }
        for (String s : completion) {
            start.remove(s);
        }
        return start.get(0);
    }
}*/
