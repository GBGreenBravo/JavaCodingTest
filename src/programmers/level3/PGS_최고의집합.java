package programmers.level3;

import java.util.Arrays;

public class PGS_최고의집합 {
    public static void main(String[] args) {
        System.out.println(solution(2, 9));
    }

    public static int[] solution(int n, int s) {

        if (n > s) {
            return new int[]{-1};
        }

        int a = (int) s / n;
        int b = s - a * n;

        int[] result = new int[n];
        Arrays.fill(result, a);

        for (int i = 0; i < b; i++) {
            result[n - 1 - i] += 1;
        }

        return result;
    }
}
