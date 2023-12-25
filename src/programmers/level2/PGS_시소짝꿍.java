package programmers.level2;

// weights의 길이는 100,000이지만, 몸무게 값은 100에서 1000 사이로 겹칠 가능성이 매우 크다.
// 그러므로 같은 값에 대해 얼마나 빨리 처리해줄지가 관건이었음.
// 그리고 정렬해서 계산할 필요 없는 범위는 break하는 것도 포인트.

import java.util.Arrays;

public class PGS_시소짝꿍 {
    public long solution(int[] weights) {
        long answer = 0;

        Arrays.sort(weights);

        long before = 0;

        for (int i = 0; i < weights.length - 1; i++) {
            if (i > 0 && weights[i - 1] == weights[i]) {
                answer += before - 1;
                before--;
                continue;
            }

            long temp = 0;
            for (int j = i + 1; j < weights.length; j++) {
                int a = weights[i];
                int b = weights[j];
                if (2 * a < b) break;
                if (a == b || 3 * a == 2 * b || 2 * a == b || 4 * a == 3 * b) {
                    temp++;
                }
            }
            answer += temp;
            before = temp;
        }

        return answer;
    }
}

// 3개 시간초과. 정렬 때문?
/* public long solution(int[] weights) {
        long answer = 0;

        Arrays.sort(weights);

        for (int i = 0; i < weights.length - 1; i++) {
            for (int j = i + 1; j < weights.length; j++) {
                int a = weights[i];
                int b = weights[j];
                if (2 * a < b) break;
                if (a == b || 3 * a == 2 * b || 2 * a == b || 4 * a == 3 * b) {
                    answer++;
                }
            }
        }


        return answer;
    } */

// 이것도 정답
/* public long solution(int[] weights) {
        long[] answers = new long[weights.length];
        int index = 0;

        Arrays.sort(weights);

        for (int i = 0; i < weights.length - 1; i++) {
            if (i > 0 && weights[i - 1] == weights[i]) {
                answers[index] = answers[index-1] - 1;
                index++;
                continue;
            }

            long temp = 0;
            for (int j = i + 1; j < weights.length; j++) {
                int a = weights[i];
                int b = weights[j];
                if (2 * a < b) break;
                if (a == b || 3 * a == 2 * b || 2 * a == b || 4 * a == 3 * b) {
                    temp++;
                }
            }
            answers[index++] = temp;
        }

        long answer = 0;
        for (long l : answers) answer += l;
        return answer;
    } */
