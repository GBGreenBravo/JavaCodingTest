package baekjoon.silver;

/*
20241017
1 / 2
 */

import java.util.Scanner;

public class BOJ_1531_투명 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] area = new int[100][100];

        for (int i = 0; i < n; i++) {
            int[] paper = new int[4];
            for (int j = 0; j < 4; j++) {
                paper[j] = sc.nextInt();
            }
            for (int x = paper[0] - 1; x < paper[2]; x++) {
                for (int y = paper[1] - 1; y < paper[3]; y++) {
                    area[x][y] += 1;
                }
            }
        }

        int answer = 0;
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                if (area[x][y] > m) answer += 1;
            }
        }
        System.out.println(answer);
    }
}
