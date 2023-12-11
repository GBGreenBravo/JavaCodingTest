package baekjoon.silver;

// 백트래킹. 이렇게 재귀로 푸는 것도 맞지만, visited 만들고 찍고 depth 추가하고 visited 초기화하는 방법도 있음.

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_N과M1 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        recursive(n, m, new ArrayList<>());

        System.out.println(sb);
    }

    static void recursive(int n, int m, ArrayList<Integer> arr) {
        if (arr.size() == m) {
            for (int i = 0; i < m; i++) {
                sb.append(arr.get(i)).append(" ");
            }
            sb.append("\n");
            return;
        }
        ArrayList<Integer> restArr = new ArrayList<>();
        for (int i = 1; i < n + 1; i++) {
            if (arr.contains(i)) {
                continue;
            }
            restArr.add(i);
        }
        for (Integer integer : restArr) {
            ArrayList<Integer> nextArr = new ArrayList<>(arr);
            nextArr.add(integer);
            recursive(n, m, nextArr);
        }
    }
}

// 시간 초과. 아마 출력에서 시간 초과?
/*public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();

    recursive(n, m, new ArrayList<>());
}

    static void recursive(int n, int m, ArrayList<Integer> arr) {
        if (arr.size() == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(arr.get(i) + " ");
            }
            System.out.println();
            return;
        }
        ArrayList<Integer> restArr = new ArrayList<>();
        for (int i = 1; i < n + 1; i++) {
            if (arr.contains(i)) {
                continue;
            }
            restArr.add(i);
        }
        for (Integer integer : restArr) {
            ArrayList<Integer> nextArr = new ArrayList<>(arr);
            nextArr.add(integer);
            recursive(n, m, nextArr);
        }
    }*/
