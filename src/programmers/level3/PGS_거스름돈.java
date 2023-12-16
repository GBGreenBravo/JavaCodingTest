package programmers.level3;

// DP로 푸는 문제.
// 동전 종류 중 작은 것부터 하나씩 n까지 계산하면서 풀어야 함.
// 현재숫자를 구성할 때, 현재숫자-현재동전에 값이 1이상이면 그 값만큼의 경우에 현재 동전 추가할 수 있으므로, += 연산해주면 됨.

public class PGS_거스름돈 {
    int[] answer = new int[100001];

    public int solution(int n, int[] money) {

        answer[0] = 1;
        for (int i = 0; i < money.length; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (j - money[i] >= 0) {
                    answer[j] += answer[j - money[i]];
                    answer[j] %= 1000000007;
                }
            }
        }

        return answer[n];
    }
}

// 정확성은 다 맞았는데, 효율성 다 틀림.
/*class Solution {
    int answer = 0;
    public int solution(int n, int[] money) {

        next(money, n, Integer.MAX_VALUE);

        return answer % 1000000007;
    }

    public void next(int[] money, int now, int recent) {
        if (now < 0) return;
        if (now == 0) {
            answer++;
            return;
        }
        for (int mon : money) {
            if (recent >= mon) {
                next(money, now - mon, mon);
            }
        }
    }
}*/

// 효율성 6개중에 2개 빼고 다 맞음.
//
/*import java.util.*;

class Solution {
    long answer = 0;
    public int solution(int n, int[] money) {

        // Arrays.sort(money);

        next(money, n, money.length - 1);

        return (int) (answer % 1000000007);
    }

    public void next(int[] money, int now, int index) {
        if (now == 0) {
            answer++;
            return;
        }
        if (index == 0) {
            if (now % money[index] == 0) answer++;
            return;
        }
        for(int i = 0; i * money[index] <= now; i++) {
            next(money, now - i * money[index], index - 1);
        }
    }
}*/
