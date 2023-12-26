package programmers.level2;

// 16:00
//
// 문제에서 요구하는 대로 했음.
// arr에 우박수열 과정 저장하고, end로 그래프의 마지막 x값 저장.
// 그에 맞게 ranges의 뒷자리를 변환하고,
    // 같으면 0.0 저장,
    // 뒷자리가 더 작으면 -1.0 저장,
    // 뒷자리가 더 크면 그 영역만큼 '사다리꼴 넓이 구하는 식'으로 정적분 결과 저장.
// 어차피 기본 temp가 0.0이므로, 같은 경우 따로 안 빼도 됨.

import java.util.ArrayList;

public class PGS_우박수열정적분 {
    public double[] solution(int k, int[][] ranges) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(k);
        while (k != 1) {
            if (k % 2 == 0) {
                k /= 2;
            } else {
                k = k * 3 + 1;
            }
            arr.add(k);
        }

        double[] answer = new double[ranges.length];
        int index = 0;
        int end = arr.size() - 1;

        for (int[] range : ranges) {
//            if (range[0] == end + range[1]) {
//                answer[index++] = 0.0;
//                continue;
//            }
            if (range[0] > end + range[1]) {
                answer[index++] = -1.0;
                continue;
            }
            double temp = 0.0;
            for (int i = range[0]; i < end + range[1]; i++) {
                temp += (arr.get(i) + arr.get(i + 1)) * 0.5;
            }
            answer[index++] = temp;
        }

        return answer;
    }
}
