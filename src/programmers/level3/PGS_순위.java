package programmers.level3;

// 21:06

// 그래프 문제
// n이 1이상 100이하여서, 이긴 방향 or 진 방향으로 반복 탐색해도 시간복잡도 O(N^2)라고 생각해서, 완전탐색(?)으로 풀었습니다.
// 지난 주의 코딩테스트 스터디에서 병룡님께서 알려주신 간선 저장 방식(ArrayList<ArrayList<Integer>>)을 활용해서
// 이긴/진 경우의 간선 정보를 upList와 downList로 따로 저장했습니다.
// 그러고 나서는, 1부터 시작해서 이긴 방향과 진 방향에 대한 완전 탐색을 하여,
// upBef(현재 선수를 이긴 방향의 모든 선수)와 downBef(현제 선수에게 진 방향의 모든 선수)의 합이 n - 1이면 answer++ 해줬습니다.

// 다른 풀이를 보니, 이긴 경우와 진 경우 모두 담을 수 있는(true/false 두 경우로 나뉠 경우 적용 가능한)
// 플로이드 와샬 알고리즘을 채택한 경우가 있다고 합니다.
// int[n][n] 에 대해서 이긴 경우와 진 경우를 한꺼번에 표기하는데
// 입력 시에는 조금 더 오래 걸리나, 나중에 조회 시에는 훨씬 편하게 풀어낼 수 있는 방식으로 보입니다.
// 해당 풀이는 아래 주석으로 명시 해놨습니다.

import java.util.ArrayList;

public class PGS_순위 {
    public int solution(int n, int[][] results) {
        ArrayList<ArrayList<Integer>> upList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> downList = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            upList.add(new ArrayList<Integer>());
            downList.add(new ArrayList<Integer>());
        }
        for (int[] temp : results) {
            int a = temp[1];
            int b = temp[0];

            upList.get(a).add(b);
            downList.get(b).add(a);
        }

        int answer = 0;

        for (int i = 1; i < n + 1; i++) {
            ArrayList<Integer> upNow = new ArrayList<>();
            ArrayList<Integer> upBef = new ArrayList<>();
            ArrayList<Integer> downNow = new ArrayList<>();
            ArrayList<Integer> downBef = new ArrayList<>();

            upNow.addAll(upList.get(i));
            downNow.addAll(downList.get(i));
            upBef.addAll(upList.get(i));
            downBef.addAll(downList.get(i));

            while(!upNow.isEmpty() || !downNow.isEmpty()) {
                ArrayList<Integer> upTemp = new ArrayList<>();
                ArrayList<Integer> downTemp = new ArrayList<>();

                for (Integer k : upNow) {
                    ArrayList<Integer> tempUp = upList.get(k);
                    for (int j : tempUp) {
                        if (!upBef.contains(j)) {
                            upTemp.add(j);
                            upBef.add(j);
                        }
                    }
                }

                for (Integer k : downNow) {
                    ArrayList<Integer> tempdown = downList.get(k);
                    for (int j : tempdown) {
                        if (!downBef.contains(j)) {
                            downTemp.add(j);
                            downBef.add(j);
                        }
                    }
                }

                upNow = new ArrayList<>(upTemp);
                downNow = new ArrayList<>(downTemp);
            }

            if (upBef.size() + downBef.size() == n - 1) answer++;
        }

        return answer;
    }
}

// 플로이드 와샬 풀이코드
/*class Solution {
    public int solution(int n, int[][] results) {

        int[][] floyd = new int[n][n];
        for (int i = 0; i < n; i++) {
            floyd[i][i] = 2;
        }
        for (int[] result : results) {
            int win = result[0] - 1;
            int lose = result[1] - 1;

            floyd[win][lose] = 1;
            floyd[lose][win] = -1;
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    if(floyd[i][k] == 1 && floyd[j][k] == -1){
                        floyd[i][j] = 1;
                        floyd[j][i] = -1;
                    }
                    if(floyd[i][k] == -1 && floyd[j][k] == 1){
                        floyd[i][j] = -1;
                        floyd[j][i] = 1;
                    }
                }
            }
        }

        int answer = 0;

        for (int i = 0 ; i < n; i++) {
            boolean canRate = true;
            int index = 0;
            while (index < n) {
                if (floyd[index++][i] == 0) {
                    canRate = false;
                    break;
                }
            }
            if (canRate == true) answer++;
        }

        return answer;
    }
}*/
