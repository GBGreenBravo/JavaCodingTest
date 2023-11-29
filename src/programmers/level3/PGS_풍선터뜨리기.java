package programmers.level3;

// 풍선 터뜨리기
// 1. 양 끝은 무조건 삶.
// 2. 제일 작은 놈 무조건 삶.
// 3. 양옆 비교했을 때, 자기보다 큰놈만 있는 곳 한 쪽 있어야 삶.

// 코드 구현
// 양끝 인덱스 제외하고 탐색.
// 오른쪽에 전체최소값 있으면, 왼쪽만 비교해서 왼쪽들보다 가장 작으면 answer ++;
// 그러다 전체최소값 만나면, 왼쪽은 이제 볼 필요 없으므로 leftOver = true / 오른쪽 최소값 업데이트.
// 이제는 전체최소값이 왼쪽에 있으므로 오른쪽만 비교하면되는데, 오른쪽의 최소값의 현재 인덱스여야만 살아남을 수 있음.
// 근데 왜 시간초과? 오른쪽 구하는 더 좋은 방법 있나..?

// 있다! 전체최소값 만나고 다음 놈을 오른쪽 최소값으로 가정하고 카운트하다가
// 더 최소값 만나면 리셋하고 다시 카운트!
// 그런데 오른쪽 카운트할때, 양쪽보다 더 큰 값 카운트 하면안됨.

// 그래서 다른 접근!
// 최소값과 인덱스 구하고
// 양쪽 배제하고 answer = 2로 시작.
// index==1부터 최대인덱스까지 왼쪽의 answer 탐색.
    // leftMin보다 작으면 answer++ 하고 leftMin 업데이트
// index==a.length-2부터 최대인덱스+1까지 오른족의 answer 탐색.
    // rightMin보다 작으면 answer++ 하고 rightMin 업데이트


public class PGS_풍선터뜨리기 {
    public static void main(String[] args) {
        System.out.println(solution(new int[] {-16,27,65,-2,58,-92,-71,68,-61,-33}));
    }
    public static int solution(int[] a) {

        if (a.length <= 2) {
            return a.length;
        }

        int answer = 2;
        int leftMin = a[0];
        int rightMin = a[a.length - 1];
        int totalMinIndex = getMinIndex(a);

        for (int i = 1; i <= Math.min(totalMinIndex, a.length - 2); i++) {
            if (a[i] < leftMin) {
                answer++;
                leftMin = a[i];
            }
        }

        for (int i = a.length - 2; i > totalMinIndex; i--) {
            if (a[i] < rightMin) {
                rightMin = a[i];
                answer++;
            }
        }

        return answer;
    }

    private static int getMinIndex(int[] a) {
        int resultValue = a[0];
        int resultIndex = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < resultValue) {
                resultValue = a[i];
                resultIndex = i;
            }
        }
        return resultIndex;
    }
}
