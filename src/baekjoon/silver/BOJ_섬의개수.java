package baekjoon.silver;

// 21:40

// DFS 문제
// map / gone 2개 할 필요 없이, map 하나만 두고 체크한 곳을 1에서 0으로 바꿔주면 됨.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_섬의개수 {
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());

            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) break;

            map = new int[h][];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                int[] row = new int[w];
                for (int j = 0; j < w; j++) {
                    if (Integer.parseInt(st.nextToken()) == 1) {
                        row[j] = 1;
                    }
                }
                map[i] = row;
            }

            System.out.println(count(h, w));
        }
    }

    private static int count(int h, int w) {
        int result = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == 1) {
                    dfs(i, j, h, w);
                    result++;
                }
            }
        }

        return result;
    }

    private static void dfs(int h, int w, int hLim, int wLim) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.push(new int[]{h, w});
        map[h][w] = 0;

        int[] dh = {0, 0, 1, -1, 1, 1, -1, -1};
        int[] dw = {1, -1, 0, 0, 1, -1, 1, -1};

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int hh = now[0];
            int ww = now[1];

            for (int i = 0; i < 8; i++) {
                int nh = hh + dh[i];
                int nw = ww + dw[i];

                if (nh < 0 || hLim <= nh || nw < 0 || wLim <= nw) {
                    continue;
                }

                if (map[nh][nw] == 1) {
                    queue.push(new int[]{nh, nw});
                    map[nh][nw] = 0;
                }
            }
        }
    }
}
