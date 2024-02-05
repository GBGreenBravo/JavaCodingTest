package programmers.level4;

// 몇 시간 풀고, 답안 봄

// 처음 답안으로 틀리고 난 후, map을 쓰면, 어차피 index++마다 map 갱신 해줘야할 것 같아서 시간초과 날 것 같아, 안 해줬음. (주석된 부분과 같이)
// 근데 index++마다 안 해줘도 됨. 재귀 돌면서 이미 else의 map.put()에서 갱신함.
// 주석 메서드의 경우 else문 마다 put()해줘서 이미 put()해준 키에 대해서도 또 하게 됨.

import java.util.*;

class PGS_호텔방배정 {
    static HashMap<Long, Long> map;
    long[] answer;

    public long[] solution(long k, long[] room_number) {
        map = new HashMap<>();

        answer = new long[room_number.length];

        for (int i = 0; i < room_number.length; i++) {
            answer[i] = check(room_number[i]);
        }

        return answer;
    }

    private long check(long i) {
        if (!map.containsKey(i)) {
            map.put(i, i + 1);
            return i;
        } else {
            long end = check(map.get(i));
            map.put(i, end);
            return end;
        }
    }

//    private long check(long i) {
//        if (!map.containsKey(i)) {
//            map.put(i, i + 1);
//            return i;
//        } else {
//            long end = check(map.get(i));
//            for (long l = i; i < end; i++) {
//                map.put(i, end);
//            }
//            return end;
//        }
//    }
}

// 정확도 다 맞으나, 효율성 거의 틀림.
// long 제대로 반영 못함. 그리고 시간복잡도 이슈도 있을 듯..?
/*
class Solution {
    public long[] solution(long k, long[] room_number) {
        long[] rooms = new long[(int)(k + 1L)];
        long[] answer = new long[room_number.length];
        long count = 0L;


        for (long l : room_number) {
            long temp = l;
            while(rooms[(int)temp] != 0) {
                temp++;
            }
            rooms[(int)temp] = 1L;
            answer[(int)count++] = temp;
        }


        return answer;
    }
}
 */

// 정확도 다 맞았고, 7개 중에 5개가 시간초과
/*
class Solution {
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];

        PriorityQueue<long[]> queue = new PriorityQueue<>(Comparator.comparingLong(o -> o[0]));

        queue.add(new long[]{room_number[0], room_number[0]});
        answer[0] = room_number[0];

        for (int i = 1; i < room_number.length; i++) {
            long target = room_number[i];

            PriorityQueue<long[]> tempQueue = new PriorityQueue<>(Comparator.comparingLong(o -> o[0]));

            while (!queue.isEmpty()) {
                long[] now = queue.poll();

                if (now[0] <= target && target <= now[1]) {
                    answer[i] = now[1] + 1;
                    tempQueue.add(new long[]{now[0], now[1] + 1});
                    tempQueue.addAll(queue);
                    break;
                }

                else if (target < now[0]) {
                    answer[i] = target;
                    tempQueue.add(new long[]{target, target});
                    tempQueue.add(now);
                    tempQueue.addAll(queue);
                    break;
                }

                else {
                    tempQueue.add(now);
                    if (queue.isEmpty()) {
                        answer[i] = target;
                        tempQueue.add(new long[]{target, target});
                        break;
                    }
                }
            }

            queue.clear();
            queue = new PriorityQueue<>(Comparator.comparingLong(o -> o[0]));

            while (!tempQueue.isEmpty()) {
                if (tempQueue.size() == 1) {
                    queue.add(tempQueue.poll());
                    break;
                }

                long[] a = tempQueue.poll();
                long[] b = tempQueue.poll();

                if (a[1] + 1 == b[0]) {
                    tempQueue.add(new long[]{a[0], b[1]});
                } else {
                    queue.add(a);
                    tempQueue.add(b);
                }

            }
//             Queue<long[]> tt = new PriorityQueue<>(queue);

//             for (int j = 0; j < queue.size(); j++) {
//                 long[] temp = tt.poll();
//                 System.out.print(temp[0] + " " + temp[1] + " / ");
//             }
//             System.out.println();
        }

        return answer;
    }
}
 */