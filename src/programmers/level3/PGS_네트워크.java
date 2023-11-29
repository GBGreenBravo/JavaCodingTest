package programmers.level3;

import java.util.ArrayList;

public class PGS_네트워크 {
    static ArrayList<Integer> arr = new ArrayList<>();

    public static void main(String[] args) {
//        System.out.println(solution(3, new int[][] {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        System.out.println(solution(3, new int[][] {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}));
    }
    public static int solution(int n, int[][] computers) {

        int answer = 0;

        for (int i = 0; i < n; i++) {
            arr.add(i);
        }

        while (!arr.isEmpty()) {
//            System.out.println(arr);
            answer ++;
            chain(n, arr.get(0), computers);
        }

        return answer;
    }

    public static void chain(int n, int now, int[][] computers) {
//        System.out.println("now = " + now);
        arr.remove(arr.indexOf(now));
        for (int i = 0; i < n; i++) {
            if (arr.contains(i) && computers[now][i] == 1 && computers[i][now] == 1) {
                chain(n, i, computers);
            }
        }
    }
}
