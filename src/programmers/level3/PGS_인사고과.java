package programmers.level3;

// 34:20

// 완호보다 점수합이 낮거나 같은 사람은 볼 필요 없음. (어차피 순위 이하고, 완호보다 둘다 높을 경우 없기 때문)
// 완호보다 둘다 점수 높은 사람 나오면 return -1;
// 이제 완호보다 점수 합 높은 (하나는 완호보다 점수 낮거나 같은) 사람들에 대해서,
    // 기존에 있던 앞 순위 사람들(arr) 중 어느 한 사람보다 점수 둘다 낮으면 continue;
    // 그리고 기존에 있던 사람들도 이 사람보다 둘다 점수 낮으면 앞 순위 리스트에서 제거 및 answer--;
// 위에서 필터링된 앞순위 사람에 대해서 arr.add() 및 answer++;

// 다른 풀이들은 [0]내림차순 & [1]오름차순으로, 인센티브 못 받는 경우 걸렀던데,
// 내 풀이는 시간복잡도 안 걸리나..?

import java.util.ArrayList;

public class PGS_인사고과 {
    public int solution(int[][] scores) {
        int wha = scores[0][0];
        int whb = scores[0][1];
        int wh = wha + whb;

        int answer = 1;

        ArrayList<int[]> arr = new ArrayList<>();

        for (int[] score : scores) {
            if (score[0] + score[1] <= wh) continue;
            if (score[0] > wha && score[1] > whb) return -1;

            boolean con = false;
            for (int i = 0; i < arr.size(); i++) {
                int[] ar = arr.get(i);
                if (score[0] < ar[0] && score[1] < ar[1]) {
                    con = true;
                }
                if (ar[0] < score[0] && ar[1] < score[1]) {
                    arr.remove(i--);
                    answer--;
                }
            }

            if (con) continue;

            arr.add(score);
            answer++;
        }

        return answer;
    }
}
