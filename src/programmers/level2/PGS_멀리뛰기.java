package programmers.level2;

// 피보나치 수열임.
// n의 최대 값이 2000이므로 arr 선언해주고, index == n 때까지만 연산해주고 값 구하면 됨.

public class PGS_멀리뛰기 {
    class Solution {
        long[] arr = new long[2001];
        public long solution(int n) {

            arr[1] = 1;
            arr[2] = 2;

            for (int i = 3; i < n + 1; i++) {
                arr[i] = (arr[i - 1] + arr[i - 2]) % 1234567;
            }

            return arr[n];
        }
    }
}
