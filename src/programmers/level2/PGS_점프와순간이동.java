package programmers.level2;

public class PGS_점프와순간이동 {
    int ans = 0;
    public int solution(int n) {

        while (n != 0) {
            n = check(n);
        }

        return ans;
    }

    public int check(int n) {
        if (n % 2 == 0) {
            return n / 2;
        } else {
            ans++;
            return n - 1;
        }
    }
}
