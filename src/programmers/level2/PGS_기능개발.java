package programmers.level2;

/*
17:10

- 스택/큐

- 각 process의 수행기간을 계산하고, 앞에서부터 현재보다 더 큰 값이 나올 때까지 모아뒀다가 추가하면 됨.

- ArrayList를 배열로 바꾸는 방법을 숙지해야 할듯.
 */

import java.util.ArrayList;

class PGS_기능개발 {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] arr = new int[progresses.length];

        for (int i = 0; i < progresses.length; i++) {
            int temp = progresses[i];
            int day = 0;
            while (temp < 100) {
                temp += speeds[i];
                day++;
            }

            arr[i] = day;
        }

        ArrayList<Integer> answer = new ArrayList<>();
        int count = 1;
        int now = arr[0];

        for (int i = 1; i < progresses.length; i++) {
            if (arr[i] > now) {
                answer.add(count);
                now = arr[i];
                count = 1;
            } else {
                count++;
            }
        }
        if (count != 0) {
            answer.add(count);
        }

//        int[] ans = new int[answer.size()];
//        int index = 0;
//        for (Integer i : answer) {
//            ans[index++] = i;
//        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
