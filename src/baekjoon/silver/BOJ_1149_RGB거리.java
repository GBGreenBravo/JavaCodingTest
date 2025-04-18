package baekjoon.silver;

// 25:36

// DP 문제.
// arr에 각 집 칠하는 비용 저장하고
// result에 각 집의 색 칠하는 최소 비용을 계산함.
    // int[3]의 index 0/1/2에 따라 r/g/b로 계산함.
// 핵심: arr[index][0] = Math.min(rgb(index - 1, 1), rgb(index - 1, 2)) + arr[index][0]

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149_RGB거리 {
    static int[][] arr;
    static int[][] result;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        arr = new int[n][3];
        result = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        result[0][0] = arr[0][0];
        result[0][1] = arr[0][1];
        result[0][2] = arr[0][2];

        int answer = Math.min(rgb(n - 1, 0), rgb(n - 1, 1));
        answer = Math.min(answer, rgb(n - 1, 2));

        System.out.println(answer);
    }

    public static int rgb(int index, int rgb) {
        if (index == 0) {
            return arr[0][rgb];
        }
        if(result[index][rgb] != 0) {
            return result[index][rgb];
        }
        result[index] = new int[]{
                Math.min(rgb(index - 1, 1), rgb(index - 1, 2)) + arr[index][0],
                Math.min(rgb(index - 1, 0), rgb(index - 1, 2)) + arr[index][1],
                Math.min(rgb(index - 1, 0), rgb(index - 1, 1)) + arr[index][2]
        };

        return result[index][rgb];
    }
}
