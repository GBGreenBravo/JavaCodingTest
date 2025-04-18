package baekjoon.silver;

/*
04:54

- 그리디
 */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_11399_ATM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o));

        for (int i = 0; i < n; i++) {
            queue.add(sc.nextInt());
        }

        int answer = 0;
        int count = n;
        for (int i = 0; i < n; i++) {
            answer += queue.poll() * count--;
        }

        System.out.println(answer);
    }
}
