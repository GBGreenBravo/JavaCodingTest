package programmers.level2;

import java.util.HashSet;

/*
43:42

- 간선 정보를 int[][] arr에 저장합니다.
- deliver()은 arr을 바탕으로 1부터 시작하는 DFS를 수행하는 메서드입니다.
    - now에서 다음 도시로의 간선이 있고, 그 도시를 방문하지 않은 경우,
        - 그 도시까지 가는 배달시간이 k를 넘으면 continue;
        - answer[다음 도시]에 아직 값이 없거나, 기존값보다 지금 계산한 값이 작으면 => answer[다음 도시] 교체
        - 계산한 값이 기존 answer[다음 도시]보다 크면 continue;
        - 방문한 도시(gone)에 다음 도시를 추가한 후, deliver();
- 이렇게 DFS가 끝나고, answer에 저장된 2부터 끝까지의 도시 중 0이 아닌(k 넘으면 저장 안됨) 도시 개수 return;

 */
public class PGS_배달 {
    static int[] answer;
    static int n;
    static int k;
    static int[][] arr;

    public int solution(int N, int[][] road, int K) {

        arr = new int[N + 1][];
        for (int i = 1; i <= N; i++) {
            arr[i] = new int[N + 1];
        }

        for (int[] roa : road) {
            if (arr[roa[0]][roa[1]] > roa[2] || arr[roa[0]][roa[1]] == 0) {
                arr[roa[0]][roa[1]] = roa[2];
                arr[roa[1]][roa[0]] = roa[2];
            }
        }

        answer = new int[N + 1];
        n = N;
        k = K;

        HashSet<Integer> gone = new HashSet<>();
        gone.add(1);
        deliver(1, 0, gone);

        int count = 1;
        for (int i = 2; i < n + 1; i++) {
            if (answer[i] != 0) count++;
        }

        return count;
    }

    private void deliver(int now, int time, HashSet<Integer> gone) {

        for (int i = 1; i < n + 1; i++) {
            if (!gone.contains(i) && arr[now][i] != 0) {
                if (time + arr[now][i] > k) continue;
                if (answer[i] > time + arr[now][i] || answer[i] == 0) {
                    answer[i] = time + arr[now][i];
                } else if (answer[i] != 0 && answer[i] < time + arr[now][i]) {
                    continue;
                }
                gone.add(i);
                deliver(i, time + arr[now][i], gone);
                gone.remove(i);
            }
        }
    }
}

// 정확성 96.9 / 마지막 케이스만 시간 초과.
/*
class Solution {
    static int[] answer;
    static int n;
    static int k;
    static int[][] arr;

    public int solution(int N, int[][] road, int K) {

        arr = new int[N + 1][];
        for (int i = 1; i <= N; i++) {
            arr[i] = new int[N + 1];
        }

        for (int[] roa : road) {
            if (arr[roa[0]][roa[1]] > roa[2] || arr[roa[0]][roa[1]] == 0) {
                arr[roa[0]][roa[1]] = roa[2];
                arr[roa[1]][roa[0]] = roa[2];
            }
        }

        answer = new int[N + 1];
        n = N;
        k = K;

        ArrayList<Integer> gone = new ArrayList<Integer>();
        gone.add(1);
        deliver(1, 0, gone);

        int count = 1;
        for (int i = 2; i < n + 1; i++) {
            if (answer[i] <= k && answer[i] != 0) count++;
        }

        return count;
    }

    private void deliver(int now, int time, ArrayList<Integer> gone) {
        if (time > k) return;

        for (int i = 1; i < n + 1; i++) {
            if (!gone.contains(i) && arr[now][i] != 0) {
                if (answer[i] > time + arr[now][i] || answer[i] == 0) {
                    answer[i] = time + arr[now][i];
                }
                gone.add(i);
                deliver(i, time + arr[now][i], gone);
                gone.remove(gone.indexOf(i));
            }
        }

        return;
    }
}
 */