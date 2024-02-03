package programmers.level2;

// 36:20

// [2]와 같은 경우를 생각하지 못했다.
// citations에 있는 수들만을 h_index의 대상으로 가능하다고 생각하여 오래 걸렸던 문제.
// 이유 불명확하게 꼬일 때는, 일단 [0], [2]와 같이 단순하지만 기본적인 케이스를 적용해보도록 하자.

public class PGS_HIndex {
    public int solution(int[] citations) {
        int[] counts = new int[10001];
        int total = 0;

        for (int i : citations) {
            counts[i] += 1;
            total++;
        }

        int answer = 0;

        for (int i = 1; i < 10001; i++) {
            total -= counts[i - 1];
            if (total >= i) {
                answer = Math.max(answer, i);
            }
        }

        return answer;
    }
}

// 잘못 접근한 방식.
// [2]의 경우 1이 정답이 돼야 하지만, queue에서 꺼낸 수만 hindex의 대상이 되기에 틀린 풀이.
 /*
 public int solution(int[] citations) {
         PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o));

         for (int i : citations) {
             queue.add(i);
         }

         int before = -1;
         int count = 0;
         int answer = -1;

         while (!queue.isEmpty()) {
             int now = queue.poll();

             if (before == now) {
                 count += 1;
             } else {
                 count = 0;
             }
             before = now;

             if (queue.size() + 1 + count >= now) {
                 answer = Math.max(answer, now);
             }

         }

         return answer;
     }*/
