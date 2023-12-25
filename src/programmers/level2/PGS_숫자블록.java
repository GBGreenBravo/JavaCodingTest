package programmers.level2;

// 2:53:00
//
// 설치할 수 있는 블록의 최대 크기는 10,000,000 이라는 점이 가장 유의미.
// 내가 놓쳤던 케이스:
    // 1말고 약수는 있으나, 제곱근까지 계산한 나뉘는 큰 약수의 값이 10,000,000보다 큰 경우.
    // 이 경우에는 1보다 큰 약수 있으나, 계산 중에 소멸되는 값임.
// 그래서 위 케이스 포함하기 위해 작은 약수 모아두는 arr 만들고,
// 큰 약수가 모두 10,000,000보다 컸던 경우에 작은 약수 중, 가장 큰 값을 도출하도록 구현.

import java.util.ArrayList;

public class PGS_숫자블록 {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int)end - (int)begin + 1];
        int index = 0;

        for (int i = (int)begin; i < (int)end + 1; i++) {
            int temp = 1;

            ArrayList<Integer> arr = new ArrayList<>();

            for (int j = 2; j <= Math.floor(Math.sqrt(i)); j++) {
                if (i % j == 0) {
                    arr.add(j);
                    if (i / j <= 10000000) {
                        temp = i / j;
                        break;
                    }
                }
            }

            if (temp == 1 && !arr.isEmpty()) {
                temp = arr.get(arr.size() - 1);
            }

            if (i == 1) temp = 0;
            answer[index++] = temp;
        }

        return answer;
    }
}

// 정확성 다 맞고, 효율성 다 틀림.
/* public int[] solution(long begin, long end) {
        int[] answer = new int[(int)end - (int)begin + 1];
        int index = 0;

        for (long i = begin; i < end + 1L; i++) {
            int temp = 1;

            int start = 2;
            for (int j = 2; j < i; j++) {
                if (i / j <= 10000000) {
                    start = j;
                    break;
                }
            }

            for (long j = start; j*2 <= i+1; j++) {
                if (i % j == 0) {
                    temp = (int) (i / j);
                    break;
                }
            }

            if (i == 1) temp = 0;
            answer[index++] = temp;
        }

        return answer;
    } */

// 내림차순으로 해도 마찬가지. 효율성 0.
/* public int[] solution(long begin, long end) {
        int[] answer = new int[(int)end - (int)begin + 1];
        int index = 0;

        for (long i = begin; i < end + 1L; i++) {
            int temp = 1;

            for (long j = Math.min(10000000, i / 2 + 1); j > 1; j--) {
                if (i % j == 0) {
                    temp = (int) j;
                    break;
                }
            }

            if (i == 1) temp = 0;
            if (i == 2) temp = 1;
            answer[index++] = temp;
        }

        return answer;
    } */

/* public int[] solution(long begin, long end) {
        int[] answer = new int[(int)end - (int)begin + 1];
        ArrayList<Long> arr = new ArrayList<>();
        for (long i = begin; i < end + 1L; i++) {
            arr.add(i);
        }

        for (int i = 10000000; i >= 1; i--) {
            for(int j = 0; j < arr.size(); j++) {
                if (arr.get(j) % i == 0 && arr.get(j) > i) {
                    answer[(int)(arr.get(j)-begin)] = i;
                    arr.remove(j--);
                }
            }
        }

        return answer;
    } */

// 정확성 100 효율성 0
/* public int[] solution(long begin, long end) {
        int[] answer = new int[(int)end - (int)begin + 1];
        int[] done = new int[(int)end - (int)begin + 1];

        for (int i = 10000000; i >= 1; i--) {
            for (int j = (int)end; j >= (int)begin; j--) {
                if (j % i == 0) {
                    int now = j;
                    while(now >= (int)begin && now > i) {
                        if (done[now - (int)begin] == 0) {
                            answer[now - (int)begin] = i;
                            done[now - (int)begin] = 1;
                        }
                        now -= i;
                    }
                    break;
                }
            }
        }

        return answer;
    } */

/* class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int)end - (int)begin + 1];
        int index = 0;

        for (int i = (int)begin; i < (int)end + 1; i++) {
            int temp = 1;

            for (int j = 2; j <= Math.floor(Math.sqrt(i)); j++) {
                if (i % j == 0 && i / j <= 10000000) {
                    temp = i / j;
                    break;
                }
            }

            if (i == 1) temp = 0;
            answer[index++] = temp;
        }

        return answer;
    }
} */
