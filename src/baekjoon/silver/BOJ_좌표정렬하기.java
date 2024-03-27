package baekjoon.silver;

/*
12:32

- PriorityQueue<int[]>에서 ComparingInt를 연속으로 사용하지는 못함을 깨달을 수 있었던 문제였습니다.
- comparing()의 경우 stream의 map()처럼 형변환된 결과가 그 후의 메서드에도 미치는 것임을 알 수 있었습니다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class BOJ_좌표정렬하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            } else {
                return o1[1] - o2[1];
            }
        });

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            queue.add(new int[]{x, y});
        }

        for (int i = 0; i < n; i++) {
            int[] ii = queue.poll();
            System.out.println(ii[0] + " " + ii[1]);
        }
    }
}
