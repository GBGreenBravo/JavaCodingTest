package baekjoon.gold;

/*
59:12

- 처음에는 모든 정상토마토에서, 썩은토마토까지의 길이 구했음. => 시간초과
- 두번째는 썩은토마토 각각에서, 닿는 정상토마토까지 최소길이 갱신했음 => 시간초과
- 최종적으로는 모든 썩은토마토를 Queue에 넣고, gone 체크하며 전파시켰음.

- 너무나도 당연한 bfs인데, 효율에 대한 생각을 안하고 급하게 시작했음.
- 어떤 문제든 생각 잘 하고 하자;
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class BOJ_토마토 {
    static int m;
    static int n;
    static int[][] box;
    static int[][] result;
    static int[][] gone;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        box = new int[n][];
        result = new int[n][];
        gone = new int[n][];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            box[i] = new int[m];
            result[i] = new int[m];
            gone[i] = new int[m];
            for (int j = 0; j < m; j++) {
                int temp = Integer.parseInt(st.nextToken());
                box[i][j] = temp;
                result[i][j] = temp;
                if (temp == 0) result[i][j] = -2;
                if (temp == 1) result[i][j] = 0;
            }
        }

        LinkedList<int[]> rottens = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 1) rottens.add(new int[]{i, j});
            }
        }
        check(rottens);

        int answer = 0;
        boolean complete = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer = Math.max(answer, result[i][j]);
                if (result[i][j] == -2) {
                    complete = false;
                    break;
                }
            }
        }
        if (complete) System.out.println(answer);
        else System.out.println(-1);
    }

    private static void check(LinkedList<int[]> rottens) {
        Queue<int[]> queue = new LinkedList<>();

        for (int[] rotten : rottens) {
            queue.add(new int[]{rotten[0], rotten[1], 0});
        }

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            if (x < 0 || n <= x || y < 0 || m <= y) continue;
            if (result[x][y] == 0 && now[2] != 0) continue;
            if (result[x][y] == -1) continue;
            if (result[x][y] != 0 && result[x][y] != -2 && result[x][y] <= now[2]) continue;
            if (gone[x][y] == 1) continue;

            gone[x][y] = 1;
            result[x][y] = now[2];

            int[] dx = {0, 0, 1, -1};
            int[] dy = {1, -1, 0, 0};
            for (int k = 0; k < 4; k++) {
                queue.add(new int[]{x + dx[k], y + dy[k], now[2] + 1});
            }
        }
    }
}

// 시간초과
// 정상 토마토마다 썩은 토마토까지 걸리는 거리를 계산했는데, 이것보다 썩은 토마토에서 각 토마토로 걸리는 게 더 나을듯.
/*
class BOJ_토마토 {
    static int m;
    static int n;
    static int[][] box;
    static int[][] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        box = new int[n][];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            box[i] = new int[m];
            for (int j = 0; j < m; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 0) checkDay(i, j);
            }
        }

        int answer = 0;
        boolean complete = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer = Math.max(answer, result[i][j]);
                if (result[i][j] == -2) {
                    complete = false;
                    break;
                }
            }
        }
        if (complete) System.out.println(answer);
        else System.out.println(-1);
    }

    private static void checkDay(int i, int j) {
        int day = cal(i, j);
        result[i][j] = day;
    }

    private static int cal(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] gone = new int[n][m];

        queue.add(new int[]{i, j, 0});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            if (x < 0 || n <= x || y < 0 || m <= y) continue;
            if (gone[x][y] == 1 || (box[x][y] == -1 && now[2] != 0)) continue;

            if (box[x][y] == 1) {
                return now[2];
            }
            gone[x][y] = 1;

            int[] dx = {0, 0, 1, -1};
            int[] dy = {1, -1, 0, 0};
            for (int k = 0; k < 4; k++) {
                queue.add(new int[]{x + dx[k], y + dy[k], now[2] + 1});
            }
        }

        return -2;
    }
}
 */

// 썩은 토마토에서 계산해도, 44%까지 채점하다가 시간초과
/*
class BOJ_토마토 {
    static int m;
    static int n;
    static int[][] box;
    static int[][] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        box = new int[n][];
        result = new int[n][];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            box[i] = new int[m];
            result[i] = new int[m];
            for (int j = 0; j < m; j++) {
                int temp = Integer.parseInt(st.nextToken());
                box[i][j] = temp;
                result[i][j] = temp;
                if (temp == 0) result[i][j] = -2;
                if (temp == 1) result[i][j] = 0;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 1) check(i, j);
            }
        }

        int answer = 0;
        boolean complete = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer = Math.max(answer, result[i][j]);
                if (result[i][j] == -2) {
                    complete = false;
                    break;
                }
            }
        }
        if (complete) System.out.println(answer);
        else System.out.println(-1);
    }

    private static void check(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{i, j, 0});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            if (x < 0 || n <= x || y < 0 || m <= y) continue;
            if (result[x][y] == 0 && now[2] != 0) continue;
            if (result[x][y] == -1) continue;
            if (result[x][y] != 0 && result[x][y] != -2 && result[x][y] <= now[2]) continue;

            result[x][y] = now[2];

            int[] dx = {0, 0, 1, -1};
            int[] dy = {1, -1, 0, 0};
            for (int k = 0; k < 4; k++) {
                queue.add(new int[]{x + dx[k], y + dy[k], now[2] + 1});
            }
        }
    }
}
 */