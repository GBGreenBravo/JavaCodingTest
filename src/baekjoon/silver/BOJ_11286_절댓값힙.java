package baekjoon.silver;

// 12:10
// Heap(PriorityQueue)과 Comparator 람다표현식을 알고 있어서 쉽게 푼 문제.
// 혹시를 위해 아래에 구현한 Comparator compare() 오버라이딩도 외우자!

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_11286_절댓값힙 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Queue<Integer> queue = new PriorityQueue(Comparator
                .comparingInt(a -> Math.abs((Integer) a))
                .thenComparing(a -> (Integer) a > 0)
        );

        for (int i = 0; i < n; i++) {
            int now = sc.nextInt();
            if (now != 0) {
                queue.add(now);
            } else {
                if (queue.isEmpty()) System.out.println(0);
                else System.out.println(queue.poll());
            }
        }

    }
}

/* public class BOJ_절댓값힙 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

//        Queue<Integer> queue = new PriorityQueue(Comparator
//                .comparingInt(a -> Math.abs((Integer) a))
//                .thenComparing(a -> (Integer) a > 0)
//        );
        Queue<Integer> queue = new PriorityQueue(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                int a = Math.abs((int) o1);
                int b = Math.abs((int) o2);
                if (a > b) return 1;
                else if (a < b) return -1;
                else {
                    if ((int) o1 > (int) o2) return 1;
                    else return -1;
                }
            }
        });

        for (int i = 0; i < n; i++) {
            int now = sc.nextInt();
            if (now != 0) {
                queue.add(now);
            } else {
                if (queue.isEmpty()) System.out.println(0);
                else System.out.println(queue.poll());
            }
        }

    }
} */
