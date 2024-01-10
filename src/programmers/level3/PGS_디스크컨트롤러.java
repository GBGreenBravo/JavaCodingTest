package programmers.level3;

// 1:12:00
//
// 세팅:
    // 우선, 작업 요청 들어온 순서대로 add할 수 있도록, 요청시간 오름차순으로 jobs 정렬.
    // 그리고 PriorityQueue로 queue에 있는 것들 중, 소요시간 가장 짧은 게 먼저 처리될 수 있도록 준비.
    // answer은 각 처리된 작업이 "요청부터 종료까지 걸린 시간"을 담아줄 int.
    // time은 말 그대로 시간 계산용 int.
    // count는 가장 최근에 queue에 들어간 jobs의 index
// while문:
    // 만약 현재 시간보다 늦거나 같은 시간 요청의 job이 있다면 다 queue에 추가.
    // (종료 조건) jobs를 queue에 다 추가했고, queue가 비어있을 때.
    // queue가 비어있으면 다음에 추가할 job의 요청시간으로 옮기고,
    // queue가 비어있지 않으면, 하나 꺼내서 그만큼의 작업 처리해주면 됨.

import java.util.*;

public class PGS_디스크컨트롤러 {
    public int solution(int[][] jobs) {

        Arrays.sort(jobs, Comparator.comparingInt(o -> o[0]));

        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        int answer = 0;
        int time = 0;
        int count = 0;

        // queue.add(jobs[count++]);

        while (true) {
            while (count < jobs.length && jobs[count][0] <= time) {
                queue.add(jobs[count++]);
            }

            if (count == jobs.length && queue.isEmpty()) break;


            if (!queue.isEmpty()) {
                int[] now = queue.poll();

                if (time <= now[0]) time = now[0];
                answer += time + now[1] - now[0];

                time += now[1];
            } else {
                time = jobs[count][0];
            }
        }

        return (int) (answer / jobs.length);
    }
}

// [[0, 6], [8, 4]] 와 같이 중간에 공백 있는 순간에 queue에 없는데 poll()하려 함.
// 그래서 queue가 empty일 때는 시간만 다음차례로 당겨주는 프로세스 추가함. 그리고 while문 들어가기 전에 추가해줬던 프로세스 삭제.
/* public class Solution {
    public int solution(int[][] jobs) {

        Arrays.sort(jobs, Comparator.comparingInt(o -> o[0]));

        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        int answer = 0;

        int time = 0;

        int count = 0;

        queue.add(jobs[count++]);


        while (true) {
            if (count == jobs.length && queue.isEmpty()) break;
            int[] now = queue.poll();

            if (time <= now[0]) time = now[0];
            answer += time + now[1] - now[0];

            time += now[1];

            while (count < jobs.length && jobs[count][0] <= time) {
                queue.add(jobs[count++]);
            }

        }

        return (int) (answer / jobs.length);
    }
} */
