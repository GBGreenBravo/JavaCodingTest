package programmers.level2;

// 18:03
// 전형적인 bfs 연습용 문제.
// ArrayList 말고 Queue(LinkedList) 쓰는 게 훨씬 효율성 검사가 빨랐음.
    // index로 get()하거나 값으로 search하는 것이 아니면, ArrayList보다 LinkedList가 더 유리하다고 함.
    // https://inpa.tistory.com/entry/JCF-%F0%9F%A7%B1-ArrayList-vs-LinkedList-%ED%8A%B9%EC%A7%95-%EC%84%B1%EB%8A%A5-%EB%B9%84%EA%B5%90
// bfs() 메서드의 인자에 map이랑 gone 담고, bfs에서 while문 돌리는 옵션도 있음을 명시!
    // https://tmdrl5779.tistory.com/216

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PGS_게임맵최단거리 {
    int[][] map;
    int[][] gone;
    Queue<int[]> nowArr = new LinkedList<>();
    Queue<int[]> nextArr = new LinkedList<>();

    public int solution(int[][] maps) {
        map = maps;
        gone = new int[map.length][map[0].length];

        nowArr.add(new int[]{0, 0});
        gone[0][0] = 1;

        while (!nowArr.isEmpty()) {
            for (int[] intArr : nowArr) {
                bfs(intArr);
            }
            nowArr = new LinkedList<>(nextArr);
            nextArr = new LinkedList<>();
        }

        int answer = (gone[map.length - 1][map[0].length - 1] == 0)
                ? -1 : gone[map.length - 1][map[0].length - 1];

        return answer;
    }

    private void bfs(int[] intArr) {
        int a = intArr[0];
        int b = intArr[1];

        int[] da = new int[]{0, 0, 1, -1};
        int[] db = new int[]{1, -1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int na = a + da[i];
            int nb = b + db[i];

            if (0 <= na && na < map.length && 0 <= nb && nb < map[0].length) {
                if (map[na][nb] != 0) {
                    if (gone[na][nb] == 0 || gone[na][nb] > gone[na][nb] + 1) {
                        gone[na][nb] = gone[a][b] + 1;
                        nextArr.add(new int[]{na, nb});
                    }
                }
            }
        }
    }
}

// 아래는 ArrayList 활용한 초기 답변
/* class Solution {
    int[][] map;
    int[][] gone;
    ArrayList<int[]> nowArr = new ArrayList<>();
    ArrayList<int[]> nextArr = new ArrayList<>();

    public int solution(int[][] maps) {
        map = maps;
        gone = new int[map.length][map[0].length];

        nowArr.add(new int[]{0, 0});
        gone[0][0] = 1;

        while (!nowArr.isEmpty()) {
            for (int[] intArr : nowArr) {
                bfs(intArr);
            }
            nowArr = new ArrayList<>(nextArr);
            nextArr = new ArrayList<>();
        }

        int answer = (gone[map.length - 1][map[0].length - 1] == 0)
                ? -1 : gone[map.length - 1][map[0].length - 1];

        return answer;
    }

    private void bfs(int[] intArr) {
        int a = intArr[0];
        int b = intArr[1];

        int[] da = new int[]{0, 0, 1, -1};
        int[] db = new int[]{1, -1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int na = a + da[i];
            int nb = b + db[i];

            if (0 <= na && na < map.length && 0 <= nb && nb < map[0].length) {
                if (map[na][nb] != 0) {
                    if (gone[na][nb] == 0 || gone[na][nb] > gone[na][nb] + 1) {
                        gone[na][nb] = gone[a][b] + 1;
                        nextArr.add(new int[]{na, nb});
                    }
                }
            }
        }
    }
} */
