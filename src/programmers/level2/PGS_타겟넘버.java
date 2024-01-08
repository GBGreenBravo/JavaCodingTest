package programmers.level2;

// 10:30
// dfs 양식 익히기 위한 연습 문제
// depth 하나 더 갔다가 다시 돌아올 때의 상태를 잘 인지해야 함!

public class PGS_타겟넘버 {
    int answer = 0;
    int[] numArr;
    int targetNum;
    int now = 0;

    public int solution(int[] numbers, int target) {
        numArr = numbers;
        targetNum = target;

        dfs(0);

        return answer;
    }

    private void dfs(int depth) {
        if (depth == numArr.length) {
            if (now == targetNum) {
                answer++;
            }
            return;
        }

        now += numArr[depth];
        dfs(++depth);
        --depth;
        now -= numArr[depth] * 2;
        dfs(++depth);
        --depth;
        now += numArr[depth];
    }
}
