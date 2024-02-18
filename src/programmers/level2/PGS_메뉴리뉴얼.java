package programmers.level2;

/*
    49:20

    - solution() 메서드
        - course의 개수에 대해 각각 check() 메서드로 answer에 적절한 답 넣어주기.
        - answer에 다 추가되면, 정렬해서 정답 반환
    - check() 메서드
        - 현재 코스의 메뉴 개수(target)에 따른 조합을 저장할 map 리셋.
        - orders의 주문 각각에 대해서 target 개수의 조합 탐색
            - 주문 총 개수가 target보다 적으면 continue;
            - indexes(조합이 저장될 int[]) 재선언하고,
            - mapping() 메서드로 조합 map에 저장.
        - map에 저장된 value가 2를 넘는 조합 중, 가장 큰 value를 가진 key만 answer에 저장.
    - mapping() 메서드
        - dfs를 구현한 메서드.
        - order.length() - 1 까지 탐색을 완료하면,
            - indexes 중 1인 값이 target과 같으면, 정렬된 조합을 map에 저장.
 */

import java.util.*;

 class PGS_메뉴리뉴얼 {
    static LinkedList<String> answer;
    static HashMap<String, Integer> map;
    static int[] indexes;

    public String[] solution(String[] orders, int[] course) {
        answer = new LinkedList<>();

        for (int target : course) {
            check(orders, target);
        }

        String[] ans = new String[answer.size()];
        int index = 0;

        Collections.sort(answer);
        for (String s : answer) {
            ans[index++] = s;
        }

        return ans;
    }

    private void check(String[] orders, int target) {
        map = new HashMap<>();

        for (String order : orders) {
            if (order.length() < target) continue;

            indexes = new int[order.length()];
            mapping(order, 0, target);
        }

        LinkedList<String> tempAnswer = new LinkedList<>();
        int min = Integer.MIN_VALUE;

        for (String s : map.keySet()) {
            if (map.get(s) == min) {
                tempAnswer.add(s);
            } else if (map.get(s) > min && map.get(s) > 1) {
                tempAnswer.clear();
                tempAnswer.add(s);
                min = map.get(s);
            }
        }

        answer.addAll(tempAnswer);
    }

    private void mapping(String order, int now, int target) {
        if (now == order.length()) {
            String s = "";

            for (int i = 0; i < indexes.length; i++) {
                if (indexes[i] == 1) {
                    s += String.valueOf(order.charAt(i));
                }
            }

            if (s.length() == target) {
                LinkedList<String> sort = new LinkedList<>();
                for (char c : s.toCharArray()) {
                    sort.add(String.valueOf(c));
                }
                Collections.sort(sort);

                String sortedS = "";
                for (String ss : sort) {
                    sortedS += ss;
                }

                map.put(sortedS, map.getOrDefault(sortedS, 0) + 1);
            }
            return;
        }

        indexes[now] = 1;
        mapping(order, now + 1, target);
        indexes[now] = 0;
        mapping(order, now + 1, target);
    }
}

// 초기 답안 (리팩토링 전)
/*
import java.util.*;

 class PGS_메뉴리뉴얼 {
    static LinkedList<String> answer;
    static HashMap<String, Integer> map;
    static int[] indexes;

    public String[] solution(String[] orders, int[] course) {
        answer = new LinkedList<>();

        for (int cours : course) {
            check(orders, cours);
        }

        String[] ans = new String[answer.size()];
        int index = 0;

        Collections.sort(answer);
        for (String s : answer) {
            ans[index++] = s;
        }

        return ans;
    }

    private void check(String[] orders, int target) {
        map = new HashMap<>();

        for (String order : orders) {
            if (order.length() < target) continue;
            int now = 0;
            int end = order.length();
            indexes = new int[order.length()];
            mapping(order, now, end, target);
        }

        LinkedList<String> tempAnswer = new LinkedList<>();
        int min = Integer.MIN_VALUE;

        for (String s : map.keySet()) {
            if (map.get(s) == min) {
                tempAnswer.add(s);
            } else if (map.get(s) > min && map.get(s) > 1) {
                tempAnswer.clear();
                tempAnswer.add(s);
                min = map.get(s);
            }
        }

        answer.addAll(tempAnswer);
    }

    private void mapping(String order, int now, int end, int target) {
        if (now == end) {
            String s = "";

            for (int i = 0; i < indexes.length; i++) {
                if (indexes[i] == 1) {
                    s += String.valueOf(order.charAt(i));
                }
            }

            if (s.length() == target) {
                LinkedList<String> sort = new LinkedList<>();
                for (char c : s.toCharArray()) {
                    sort.add(String.valueOf(c));
                }
                Collections.sort(sort);

                String sortedS = "";
                for (String ss : sort) {
                    sortedS += ss;
                }

                map.put(sortedS, map.getOrDefault(sortedS, 0) + 1);
            }
            return;
        }

        indexes[now] = 1;
        mapping(order, now + 1, end, target);
        indexes[now] = 0;
        mapping(order, now + 1, end, target);
    }
}
 */