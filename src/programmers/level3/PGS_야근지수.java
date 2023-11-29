package programmers.level3;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PGS_야근지수 {
    public static void main(String[] args) {
//        System.out.println(solution(4, new int[]{4, 3, 3}));
//        System.out.println(solution(1, new int[]{2, 1, 2}));
        System.out.println(solution(3, new int[]{1, 1}));
    }
    public static long solution(int n, int[] works) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < works.length; i++) {
            pq.add(works[i]);
        }

//        System.out.println(pq);

        for (int i = 0; i < n; i++) {
            if (pq.isEmpty()) {
                break;
            }
            int temp = pq.poll();
            if (temp != 1) {
                pq.add(temp - 1);
            }
        }

//        System.out.println(pq);

        long answer = 0;
        int count = pq.size();
        for (int i = 0; i < count; i++) {
            answer += Math.pow(pq.poll(), 2);
        }

        return answer;
    }
}
