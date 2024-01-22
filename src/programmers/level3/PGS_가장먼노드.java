package programmers.level3;

// 17:30

// 양방향의 edge(간선)들을 Map<Integer, LinkedList<Integer>>의 간선 map에 담아줌.
// 1로부터의 거리를 담은 result를 int[n+1] 배열로 선언함.
// bfs로 거리 계산 후 count--만큼의 거리르 가진 노드의 수를 반환하면 됨.

// 그래프 문제는 거의 처음이라 걱정했는데, 막 해도 풀렸던 문제.

import java.util.*;

class PGS_가장먼노드 {
    public int solution(int n, int[][] edge) {
        Map<Integer, LinkedList<Integer>> lines = new HashMap<>();

        for (int[] ed : edge) {
            int a = ed[0];
            int b = ed[1];
            if (!lines.containsKey(a)) {
                LinkedList<Integer> tempArr = new LinkedList<>();
                tempArr.add(b);
                lines.put(a, tempArr);
            }
            else {
                LinkedList<Integer> tempArr = lines.get(a);
                tempArr.add(b);
                lines.put(a, tempArr);
            }
            if (!lines.containsKey(b)) {
                LinkedList<Integer> tempArr = new LinkedList<>();
                tempArr.add(a);
                lines.put(b, tempArr);
            }
            else {
                LinkedList<Integer> tempArr = lines.get(b);
                tempArr.add(a);
                lines.put(b, tempArr);
            }
        }

        int[] result = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            result[i] = -1;
        }

        Queue<Integer> now = new LinkedList<>();
        now.add(1);
        result[1] = -2;
        int count = 0;
        while (!now.isEmpty()) {
            count++;
            Queue<Integer> next = new LinkedList<>();
            while (!now.isEmpty()) {
                int temp = now.poll();
                LinkedList<Integer> tempArr = lines.get(temp);
                for (int i : tempArr) {
                    if (result[i] == -1) {
                        result[i] = count;
                        next.add(i);
                    }
                }
            }
            now = new LinkedList<>(next);
        }

        int answer = 0;
        count--;
        for (int i = 1; i < n + 1; i++) {
            if (result[i] == count) answer++;
        }

        return answer;
    }
}

// 정답 답안. 위 코드는 조금 더 리팩토링을 거친 것.
/*
public int solution(int n, int[][] edge) {
    Map<Integer, LinkedList<Integer>> lines = new HashMap<>();

    for (int[] ed : edge) {
        int a = ed[0];
        int b = ed[1];
        if (!lines.containsKey(a)) {
            LinkedList<Integer> tempArr = new LinkedList<>();
            tempArr.add(b);
            lines.put(a, tempArr);
        }
        else {
            LinkedList<Integer> tempArr = lines.get(a);
            tempArr.add(b);
            lines.put(a, tempArr);
        }
        if (!lines.containsKey(b)) {
            LinkedList<Integer> tempArr = new LinkedList<>();
            tempArr.add(a);
            lines.put(b, tempArr);
        }
        else {
            LinkedList<Integer> tempArr = lines.get(b);
            tempArr.add(a);
            lines.put(b, tempArr);
        }
    }

    int[] result = new int[n + 1];
    for (int i = 0; i < n + 1; i++) {
        result[i] = -1;
    }

    Queue<Integer> now = new LinkedList<>();
    now.add(1);
    result[1] = -2;
    int count = 0;
    while (!now.isEmpty()) {
        count++;
        Queue<Integer> next = new LinkedList<>();
        while (!now.isEmpty()) {
            int temp = now.poll();
            LinkedList<Integer> tempArr = lines.get(temp);
            for (int i : tempArr) {
                if (result[i] != -1) continue;
                else {
                    result[i] = count;
                    next.add(i);
                }
            }
        }
        now = new LinkedList<>(next);
    }

    int answer = 0;
    count--;
    for (int i = 1; i < n + 1; i++) {
        if (result[i] == count) answer++;
    }

    return answer;
}*/
