package programmers.level2;

// 20:37
//
// 우선 (index+1, 카드숫자) 이런 식으로 int[]를 arr에 모두 저장합니다.
// while문을 이용해서 연결되는 상자들의 조합을 찾아내 answer에 그 숫자를 셉니다.
// arr.get(0)부터 제거하고 now로 저장하며 시작합니다.
// 다음으로 열게 될 상자를 for문에서 찾고, 다음에 열 상자가 존재하지 않는다면 inner while문을 종료합니다.
// 이렇게 하나의 상자 연쇄 조합을 찾아냈다면, answers에 answer을 저장하고 arr이 빌 때까지 반복합니다.
// 그런 다음, answers에서 가장 큰 값과 다음으로 큰 값을 곱한 값을 return합니다.
// second가 0으로 시작하기에, answers.size()가 1이어도 괜찮습니다.

import java.util.ArrayList;

public class PGS_혼자놀기의달인 {
    public int solution(int[] cards) {
        ArrayList<int[]> arr = new ArrayList<>();

        for (int i = 0; i < cards.length; i++) {
            arr.add(new int[]{i+1, cards[i]});
        }

        ArrayList<Integer> answers = new ArrayList<>();

        while (!arr.isEmpty()) {
            int answer = 0;

            int[] now = arr.get(0);
            arr.remove(0);
            answer++;
            boolean stop = false;

            while(!stop) {
                stop = true;
                for (int i = 0; i < arr.size(); i++) {
                    if (arr.get(i)[0] == now[1]) {
                        now = arr.get(i);
                        arr.remove(i);
                        answer++;
                        stop = false;
                    }
                }
            }

            answers.add(answer);
        }

        int first = 0;
        int second = 0;
        for (int ans : answers) {
            if (ans > first) {
                second = first;
                first = ans;
            } else if (ans > second) {
                second = ans;
            }
        }

        return first * second;
    }
}
