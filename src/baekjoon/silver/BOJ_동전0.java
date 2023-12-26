package baekjoon.silver;

import java.util.Scanner;

public class BOJ_동전0 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        int answer = 0;

        for (int i = n - 1; i >= 0; i--) {
            answer += (k / coins[i]);
            k %= coins[i];
        }

        System.out.println(answer);
    }
}
