package baekjoon.gold;

// 42:30

// map에 있는 바이러스를 전부 하나씩 전파시키기 보다,
// 목표 지점 (x, y)로 부터 가장 가까운 바이러스 지점들 중 가장 낮은 번호를 찾아내기 위해 bfs 활용함.

// 올바른 풀이법은 바로 깨우쳤으나,
// if (map[x][y] != 0) answer = map[x][y]; 이렇게 목표 지점에 이미 바이러스 있는 경우를 생각 못해서 시간이 좀 걸림.

// 다른 풀이로는, bfs 쓰지 않고, 그냥 존재하는 바이러스들과의 거리 구해서 가장 가까운 것들 중 가장 낮은 번호의 바이러스 구하는 풀이도 있음.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_경쟁적전염 {
    static int[][] map;
    static boolean[][] gone;
    static ArrayList<int[]> now;
    static HashSet<int[]> next;
    static HashSet<Integer> answers;

    public static void main(String[]args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map =  new int[n][n];
        gone =  new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;

        now = new ArrayList<>();
        answers = new HashSet<>();

        now.add(new int[]{x, y});

        gone[x][y] = true;
        boolean stop = false;

        for (int i = 0; i < s; i++) {
            next = new HashSet<>();
            for (int[] arr : now) {
                if (check(arr[0], arr[1]))
                    stop = true;
            }
            if (stop) break;

            for (int[] nex : next) {
                gone[nex[0]][nex[1]] = true;
            }

            now = new ArrayList<>(next);
        }

        int answer = Integer.MAX_VALUE;
        if (answers.isEmpty()) answer = 0;
        else {
            for (int i : answers) {
                if (i < answer) answer = i;
            }
        }

        if (map[x][y] != 0) answer = map[x][y];

        System.out.println(answer);
    }

    private static boolean check(int x, int y) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        boolean check = false;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < map.length && 0 <= ny && ny < map[0].length) {
                if (map[nx][ny] != 0 && !gone[nx][ny]) {
                    answers.add(map[nx][ny]);
                    check = true;
                } else if (map[nx][ny] == 0 && !gone[nx][ny]) {
                    next.add(new int[]{nx, ny});
                }
            }

        }

        return check;
    }
}

// 런타임 에러
// map =  new int[n][k]; 아니고 map =  new int[n][n]; 임
// 문제 대충 읽음......
/*
public class BOJ_경쟁적전염 {
    static int[][] map;
    static boolean[][] gone;
    static ArrayList<int[]> now;
    static ArrayList<int[]> next;
    static ArrayList<Integer> answers;

    public static void main(String[]args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map =  new int[n][k];
        gone =  new boolean[n][k];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < k; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;

        now = new ArrayList<>();
        answers = new ArrayList<>();

        now.add(new int[]{x, y});
        gone[x][y] = true;
        boolean stop = false;

        for (int i = 0; i < s; i++) {
            next = new ArrayList<>();
            for (int[] arr : now) {
                if (check(arr[0], arr[1]))
                    stop = true;
            }
            if (stop) break;
            now = new ArrayList<>(next);
        }

        int answer = Integer.MAX_VALUE;
        for (int i : answers) {
            if (i < answer) answer = i;
        }
        if (answer == Integer.MAX_VALUE) answer = 0;

        System.out.println(answer);
    }

    private static boolean check(int x, int y) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        boolean check = false;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < map.length && 0 <= ny && ny < map[0].length) {
                if (map[nx][ny] != 0 && !gone[nx][ny]) {
                    gone[nx][ny] = true;
                    answers.add(map[nx][ny]);
                    check = true;
                } else if (map[nx][ny] == 0 && !gone[nx][ny]) {
                    gone[nx][ny] = true;
                    next.add(new int[]{nx, ny});
                }
            }

        }

        return check;
    }
}
 */
