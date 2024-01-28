package baekjoon.silver;

// 19:34

// queue에 순서대로 넣어주고, sorted를 PriorityQueue로 구현하여 다음으로 나올 값들을 저장해줌.
// m이 내가 도출하고자 하는 목표 값의 index 이므로,
// 다음 순서의 (목표가 아닌) 값이 빠져나가거나, index 0에서 다음 순서가 아닌 값을 뽑아내면
// m-- 해줌. (만약 m이 -1이면, queue.size() - 1로 업데이트 시켜줌)
// 그러다가 다음 순서 값이 빠져나가는데 이 때 m이 -1이면, 목표 값이 나왔다는 뜻이므로 return

// peek()는 poll()과 같은 값을 반환하지만, peek는 기존 queue에 변형이 없다는 차이.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_프린터큐 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int count = Integer.parseInt(st.nextToken());

        for (int i = 0; i < count; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            Queue<Integer> queue = new LinkedList<>();
            Queue<Integer> sorted = new PriorityQueue<>(Comparator.comparingInt(o -> -o));

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int next = Integer.parseInt(st.nextToken());
                queue.add(next);
                sorted.add(next);
            }
            System.out.println(next(queue, m, sorted));
        }
    }
    private static int next(Queue<Integer> queue, int m, Queue<Integer> sorted) {
        int answer = 0;

        while (!queue.isEmpty()) {
            int next = sorted.poll();
            while (true) {
                int now = queue.poll();
                m--;
                if (now == next) {
                    answer++;
                    if (m == -1) {
                        return answer;
                    }
                    break;
                } else {
                    queue.add(now);
                    if (m == -1) {
                        m = queue.size() - 1;
                    }
                }
            }
        }

        return 0;
    }
}
