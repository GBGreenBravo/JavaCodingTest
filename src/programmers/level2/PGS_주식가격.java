package programmers.level2;

// 이중 for문으로 탐색하되, 안쪽의 for문은 i 다음만 탐색함.

public class PGS_주식가격 {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length - 1; i++) {
            int sec = 0;
            for (int j = i + 1; j < prices.length; j++) {
                sec++;
                if (prices[i] > prices[j]) {
                    break;
                }
            }
            answer[i] = sec;
        }

        return answer;
    }
}
