package programmers.level2;

// 28:19

// 이차원 배열 만들지 않고, 이중 반복문으로 결과 배열 만드는 법
// (int)(left / n)에서 시작하고, i < (int)(right / n)에서 끝나는 첫 번째 반복문.
    // 시간복잡도 줄이기 위한 것. for (int i = 0; i < n; i++) 으로 해도 됨.
// for (int j = 0; j < n; j++)의 2번째 반복문
// (long)i * n + j가 left와 right 사이면 아래 연산 수행
    // answer[index++] = Math.max(i + 1, j + 1);

// long 계산을 고려 안 해줘서 오래 걸렸던 문제.
// i * n + j 연산에서 int의 최대치를 넘어서는 결과가 나올 수 있으므로,
// (long)i * n + j 와 같이, 한 군데에 (long) 박싱을 해주면 long 연산도 반영함.

public class PGS_n2배열자르기 {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right - left) + 1];
        int index = 0;

        for (int i = (int)(left / n); i < (int)(right / n) + 1; i++) {
            for (int j = 0; j < n; j++) {
                if (left <= (long)i * n + j && (long)i * n + j <= right) {
                    answer[index++] = Math.max(i + 1, j + 1);
                }
            }
        }

        return answer;
    }
}
