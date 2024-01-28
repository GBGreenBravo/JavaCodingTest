package baekjoon.silver;

// 05:52

import java.util.LinkedList;
import java.util.Scanner;

public class BOJ_카드1 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int n = sc.nextInt();

        sc.close();

        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }

        while (queue.size() != 1) {
            sb.append(queue.poll() + " ");
            queue.offer(queue.poll());
        }

        sb.append(queue.poll() + " ");

        System.out.println(sb);
    }
}
