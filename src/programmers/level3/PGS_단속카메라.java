package programmers.level3;

/*
- 33:15

- 그리디

- queue(PriorityQueue)에 출발시간 오름차순으로, route 정보를 저장합니다.
- arr(ArrayList)에는 현재 도로 지점에서 단속카메라를 만나지 않은 route의 진출지점만을 저장합니다.
- 그리고 모든 차들이 단속될 때까지 while문을 돕니다.
  - 현재 도로의 지점(now)을 Math.min(queue의 다음 진입지점, arr의 최솟값)으로 설정합니다.
  - queue의 now 진입지점을 가진 route들에 대해서, arr에 해당 route의 진출지점을 추가합니다.
  - arr에 now 값을 가지고 있다면( = 단속카메라를 만나지 않은 특정 route가 now에서 진출한다면),
    - now에 단속카메라를 추가합니다. (arr 리셋, answer++)

- 참고로 now(현재의 도로 지점)을 -30000부터 30000까지 반복문을 돌릴 수도 있지만, 이러면 한 케이스에 대해 시간초과가 났습니다.
- 그리고 queue에 route 정보를 추가할 때, addAll()를 활용하면 왜 시간초과가 나는지 모르겠습니다....

- 제 풀이보다 [routes를 진출지점 오름차순으로 지정하고 greedy하게 푸는 방식](https://velog.io/@ahnick/programmers-%EB%8B%A8%EC%86%8D%EC%B9%B4%EB%A9%94%EB%9D%BC)이 더 코드가 간결합니다!
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class PGS_단속카메라 {
    public int solution(int[][] routes) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        // addAll()로 추가할 때는 왜 시간초과가 나지..?
//        queue.addAll(Arrays.asList(routes));
//        Collections.addAll(queue, routes);
        for (int[] route : routes) {
            queue.add(route);
        }

        int answer = 0;
        ArrayList<Integer> arr = new ArrayList<>();

        int now = -30000;
        while (!queue.isEmpty() || !arr.isEmpty()) {
            int arrMin = 30001;
            for (int i : arr) {
                arrMin = Math.min(arrMin, i);
            }
            if (queue.isEmpty()) {
                now = arrMin;
            } else if (arr.isEmpty()) {
                now = queue.peek()[0];
            } else {
                now = Math.min(queue.peek()[0], arrMin);
            }

            while (!queue.isEmpty() && queue.peek()[0] == now) {
                arr.add(queue.poll()[1]);
            }
            if (arr.contains(now)) {
                arr = new ArrayList<>();
                answer++;
            }
        }
        return answer;
    }
}

// 정확성 100, 효율성 1개만 시간초과. 아마 -30000부터 30000까지 다 조회해서 그런듯. queue.peek()로 바꾸면 될듯.
/*
class Solution {
    public int solution(int[][] routes) {

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int[] route : routes) {
            queue.add(route);
        }

        int answer = 0;
        ArrayList<Integer> arr = new ArrayList<>();

        for (int i = -30000; i < 30001; i++) {
            // if (queue.isEmpty()) break;
            while (!queue.isEmpty() && queue.peek()[0] == i) {
                arr.add(queue.poll()[1]);
            }

            if (arr.contains(i)) {
                arr = new ArrayList<>();
                answer++;
            }

        }
        return answer;
    }
}
 */
