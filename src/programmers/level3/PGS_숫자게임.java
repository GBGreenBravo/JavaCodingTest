package programmers.level3;

// 처음 생각했던 문제 푸는 규칙: A와 B 둘다 오름차순 정렬했을 때, B의 인덱스0부터 가장큰값의인덱스까지 모두 B가 이기는 게 정답. or B가 모두 지는 경우.
// 문제 푸는 규칙을 효율적인 방법으로 도출: B에서 가장 큰 갑부터 오름차순으로 정렬된 A팀을 볼때, 큰 B값이 못 이긴 A값은 다음 A값이 이길 수 없음. 그래서 지금B값보다 작은 애들 중에 지금B값이랑 싸운 A값을 뺀 리스트를 다음에 탐색하면 됨. 그러다 지금B값이 남은 현재A리스트보다 다 작거나 or 현재A리스트가 null이면 끝.

// 우선, A팀의 숫자들 정렬. (Comparator로 하면 n**2 시간복잡도, Collections.sort()쓰면 n log n 시간복잡도)
// B팀의 숫자들을 최대힙(PriorityQueue)에 넣고, 가장 큰 것부터 하나씩 빼며 얘보다 작은 숫자 찾기.
// 찾고나면 반복문 멈추고, nextLimit으로 아예 제일 큰 것부터 탐색하지 않도록, 반복문 구간 줄여주기.
// 종료 조건: temp == -1일 때. (temp는 index 값 말하므로, -1 될 수 없음.)
// 헤맸던 포인트: 안쪽 반복문 돌때, arrA의 작은 값부터 nextLimit-1까지 비교했었음. 그러다가 nextLimit-1부터 내림차순 탐색하니, 효율성 통과할 수 있었음.
// 아무래도 poll()할 때 다음으로 큰 값이 나오는데, 가장 작은 값부터 탐색하는 건 비효율적임.

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class PGS_숫자게임 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,1,3,3,5,5,7}, new int[]{1,1,1,1,2,2,4}));
    }
    public static int solution(int[] A, int[] B) {
        ArrayList<Integer> arrA = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            arrA.add(A[i]);
        }
        Collections.sort(arrA);

        Queue<Integer> arrB = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < B.length; i++) {
            arrB.add(B[i]);
        }

        int answer = 0;
        int limit = arrB.size();
        int nextLimit = arrA.size();

        for (int i = 0; i < limit; i++) {
            int now = arrB.poll();
            int temp = -1;
            for (int j = nextLimit - 1; j >= 0; j--) {
                if (now > arrA.get(j)) {
                    temp = j;
                    break;
                }
            }
            nextLimit = temp;
            if (temp == -1) {
                break;
            }
            answer++;
        }

        return answer;
    }
}

    // 정확성 다 맞았지만, 효율성 다 틀림. 아무래도 이중for문 때문인 듯함. 접근법은 맞는 듯.
    /*public static int solution(int[] A, int[] B) {
        ArrayList<Integer> arrA = new ArrayList<>();
        ArrayList<Integer> arrB = new ArrayList<>();

        for (int i = 0; i < A.length; i++) {
            arrA.add(A[i]);
        }
        arrB.add(0);
        for (int i = 0; i < B.length; i++) {
            arrB.add(B[i]);
        }

        arrA.sort((Comparator.comparingInt(o -> o)));
        arrB.sort((Comparator.comparingInt(o -> o)));

        int answer = 0;

        for (int i = 0; i < arrA.size(); i++) {
            arrB.remove(0);
            int count = 0;
            for (int j = 0; j < arrB.size(); j++) {
                if (arrA.get(j) < arrB.get(j)) {
                    count++;
                }
            }
            answer = Math.max(answer, count);
        }


        return answer;
    }*/

    // 이 친구도 효율성0 정확성100. 탐색하는 범위 줄이고 ArrayList정렬방식 변경했는데도 그러나?
    /*public static int solution(int[] A, int[] B) {
        ArrayList<Integer> arrA = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            arrA.add(A[i]);
        }
        Collections.sort(arrA);

        Queue<Integer> arrB = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < B.length; i++) {
            arrB.add(B[i]);
        }

        int answer = 0;
        int limit = arrB.size();
        int nextLimit = arrA.size();

        for (int i = 0; i < limit; i++) {
            int now = arrB.poll();
            int temp = -1;
            for (int j = 0; j < nextLimit; j++) {
                if (now <= arrA.get(j)) continue;
                temp = j;
            }
            nextLimit = temp;
            if (temp == -1) {
                break;
            }
            answer++;
        }

        return answer;
    }*/
