package baekjoon.silver;

// 24:00
//
// 백준 풀이에 익숙하지 않아, BufferedReader, StringTokenizer, StringBuilder 활용에 미숙했음.
// 앞으로는 이 부분에서 시간 소모하지 말것!!

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_크리스마스선물 {
    public static void main(String[] args) throws IOException {

        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(a -> -(int)a));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());

            if (start == 0) {
                if (queue.isEmpty()) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(queue.poll()).append("\n");
                }
            } else {
                for (int j = 0; j < start; j++) {
                    queue.add(Integer.parseInt(st.nextToken()));
                }
            }
        }

        System.out.println(sb.toString());
    }
}
