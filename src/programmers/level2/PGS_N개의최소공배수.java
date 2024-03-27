package programmers.level2;

/*
15:40

- 각 수의 약수들을 temp에 저장하고 temp에 저장된 값과 result에 저장된 값 중 최댓값을 result에 저장. (효율성 위해, map으로 발전 가능.)

- 원래는 "두 수의 최소공배수는, 두 수의 곱을 최대공약수로 나눈 것과 같다"는 규칙으로 풀어내는 것.
- 효율성 검사 타이트하게 들어갔으면, 내 풀이는 효율에서 문제 발생했을 것.
 */

class PGS_N개의최소공배수 {
    static int[] result;

    public int solution(int[] arr) {
        result = new int[98];

        for (int i : arr) {
            check(i);
        }

        int answer = 1;
        for (int j = 2; j < 98; j++) {
            while (result[j] != 0) {
                answer *= j;
                result[j] -= 1;
            }
        }
        return answer;
    }

    private void check(int i) {
        int[] temp = new int[98];

        while (i != 1) {
            for (int k = 2; k < 98; k++) {
                if (i % k == 0) {
                    temp[k] += 1;
                    i /= k;
                    break;
                }
            }
        }

        for (int j = 2; j < 98; j++) {
            result[j] = Math.max(result[j], temp[j]);
        }
    }
}
