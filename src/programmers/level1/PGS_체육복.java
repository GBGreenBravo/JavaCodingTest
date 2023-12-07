package programmers.level1;

// lost와 reserve에 담긴 값이 index + 1이므로, 넣을 때는 -1 해줘야 함!

import java.util.ArrayList;

public class PGS_체육복 {
    public static void main(String[] args) {
        System.out.println(solution(5, new int[]{2, 3, 4}, new int[]{1, 2, 3}));
    }
    public static int solution(int n, int[] lost, int[] reserve) {
        ArrayList<Integer> lost2 = new ArrayList<>();
        for (int i : lost) {
            lost2.add(i-1);
        }

        ArrayList<Integer> reserve2 = new ArrayList<>();
        for (int i : reserve) {
            reserve2.add(i-1);
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (lost2.contains(i)) {
                if (reserve2.contains(i - 1) && !lost2.contains(i - 1)) {
                    answer++;
                    reserve2.remove(reserve2.indexOf(i - 1));
                    continue;
                }
                if (reserve2.contains(i)) {
                    reserve2.remove(reserve2.indexOf(i));
                    answer++;
                    continue;
                }
                if (reserve2.contains(i + 1) && !lost2.contains(i + 1)) {
                    answer++;
                    reserve2.remove(reserve2.indexOf(i + 1));
                    continue;
                }
                continue;
            }
            answer++;
        }

        return answer;
    }
}
