package programmers.level3;

// 문제 지문에서 "만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다."라고 했는데, 이 문장에서 "가능한 경로" 부분을 반영하지 못해서 헤맸음.

import java.util.*;

class PGS_여행경로 {
    String[] answer;
    public String[] solution(String[][] tickets) {
        answer = new String[tickets.length];
        for (int i = 0; i < tickets.length; i++) {
            answer[i] = "ZZZ";
        }

        ArrayList<String[]> arr = new ArrayList<>();
        Collections.addAll(arr, tickets);

        ArrayList<String> gone = new ArrayList<>();
        gone.add("ICN");

        compute(gone, "ICN", arr);

        return answer;
    }

    private void compute(ArrayList<String> gone, String now, ArrayList<String[]> tickets) {
        if(tickets.isEmpty()) {
            for (int i = 0; i < answer.length; i++) {
                if (!gone.get(i).equals(answer[i])) {
                    if (gone.get(i).compareTo(answer[i]) < 0) {
                        answer = gone.toArray(new String[0]);
                    }
                    return;
                }
            }
            return;
        }

        for (String[] ticket : tickets) {
            if (ticket[0].equals(now)) {
                ArrayList<String> nextGone = new ArrayList<>(gone);
                ArrayList<String[]> nextTickets = new ArrayList<>(tickets);
                nextGone.add(ticket[1]);
                nextTickets.remove(ticket);
                compute(nextGone, ticket[1], nextTickets);
            }
        }
    }
}

//
/* class Solution {
    ArrayList<String> answer = new ArrayList<>();
    HashMap<String, String> map;
    public String[] solution(String[][] tickets) {
        String now = "ICN";

        map = new HashMap<>();

        for (String[] ticket : tickets) {
            map.put(ticket[0], ticket[1]);
        }

        answer.add("ICN");
        compute("ICN");

        return answer.toArray(new String[tickets.length]);
    }

    private void compute(String now) {
        String next = map.get(now);
        map.remove(now, next);
        answer.add(next);

        if (map.containsKey(next)) {
            compute(next);
        }
    }
}*/

// set.addAll(Arrays.stream(tickets).toList()); // toList() 지원 안되고
// if (str[0] == now) { // == 연산자 안 먹힘.
// set.remove(new String[]{now, next}); // 삭제도 안됨.
/* class Solution {
    ArrayList<String> answer = new ArrayList<>();
    HashSet<String[]> set;
    public String[] solution(String[][] tickets) {
        String now = "ICN";

        set = new HashSet<>();

        set.addAll(Arrays.stream(tickets).toList());

        answer.add("ICN");
        compute("ICN");

        return answer.toArray(new String[tickets.length]);
    }

    private void compute(String now) {
        ArrayList<String> nexts = new ArrayList<>();
        for (String[] str : set) {
            if (str[0] == now) {
                nexts.add(str[1]);
            }
        }

        if (nexts.isEmpty()) return;

        nexts.sort(Comparator.comparing(a -> a));
        String next = nexts.get(0);
        answer.add(next);
        set.remove(new String[]{now, next});
        compute(next);
    }
}*/

// 추측하기로는, 출발지/도착지 모두 같은 티켓이 중복되는 경우도 고려해야 할 것으로 생각함.
/* class Solution {
    ArrayList<String> answer = new ArrayList<>();
    HashSet<String[]> set;
    ArrayList<String> gone = new ArrayList<>();
    public String[] solution(String[][] tickets) {
        String now = "ICN";

        set = new HashSet<>();

        for (String[] ticket : tickets) {
            set.add(ticket);
        }

        answer.add("ICN");
        compute("ICN");

        return answer.toArray(new String[tickets.length]);
    }

    private void compute(String now) {
        ArrayList<String> nexts = new ArrayList<>();
        for (String[] str : set) {
            if (str[0].equals(now)) {
                if (!gone.contains(now + str[1])) {
                    nexts.add(str[1]);
                }
            }
        }

        if (nexts.isEmpty()) return;

        nexts.sort(Comparator.comparing(a -> a));
        String next = nexts.get(0);
        answer.add(next);
        gone.add(now + next);
        compute(next);
    }
}*/

// 중복되는 티켓도 반영했는데, 그 이슈가 아니었나봄.
// 무조건 알파벳 우선하는 곳 가는 게 아니었음. "가능한 경로" 중 알파벳 우선 순위로 가는 것이었음.
/* class Solution {
    ArrayList<String> answer = new ArrayList<>();
    ArrayList<String[]> arr = new ArrayList<>();
    public String[] solution(String[][] tickets) {
        Collections.addAll(arr, tickets);

        answer.add("ICN");
        compute("ICN");

        return answer.toArray(new String[tickets.length]);
    }

    private void compute(String now) {
        ArrayList<String> nexts = new ArrayList<>();
        for (String[] str : arr) {
            if (str[0].equals(now)) {
                nexts.add(str[1]);
            }
        }

        if (nexts.isEmpty()) return;

        nexts.sort(Comparator.comparing(a -> a));
        String next = nexts.get(0);
        answer.add(next);
        for (String[] str : arr) {
            if (str[0].equals(now) && str[1].equals(next)) {
                arr.remove(str);
                break;
            }
        }
        compute(next);
    }
} */
