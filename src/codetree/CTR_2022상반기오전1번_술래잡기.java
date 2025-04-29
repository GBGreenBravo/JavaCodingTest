package codetree;

// 20250429
// 48:31
// 1 / 1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class CTR_2022상반기오전1번_술래잡기 {

    private static int N, M, H, K;
    private static List<Runner> runners = new ArrayList<>();
    private static boolean[][] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            runners.add(new Runner(y, x, d));
        }

        trees = new boolean[N][N];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            trees[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
        }

        Chaser chaser = new Chaser();
        chaser.calFutureMoves();

        int score = 0;
        for (int turn = 1; turn <= K; turn++) {
            for (Runner runner: runners) {
                runner.move(chaser);
            }
            chaser.move();
            score += turn * chaser.catchRunners();
        }
        System.out.println(score);
    }

    private static class Runner {
        int y, x, dy, dx;
        Runner(int y, int x, int d) {
            this.y = y - 1;
            this.x = x - 1;
            if (d == 1) {
                this.dy = 0;
                this.dx = 1;
            } else {
                this.dy = 1;
                this.dx = 0;
            }
        }
        void move(Chaser chaser) {
            if (Math.abs(this.y - chaser.y) + Math.abs(this.x - chaser.x) > 3) return;
            int ny = y + dy;
            int nx = x + dx;
            if (!oob(ny, nx)) {
                if (ny == chaser.y && nx == chaser.x) return;
                this.y = ny;
                this.x = nx;
            } else {
                this.dy *= -1;
                this.dx *= -1;
                this.move(chaser);
            }
        }
    }

    private static class Chaser {
        int y, x, dy, dx;
        int idx;
        int[][] moves = new int[N * N * 2 - 2][4];
        Chaser() {
            this.y = (int) N / 2;
            this.x = (int) N / 2;
            this.dy = -1;
            this.dx = 0;
        }
        void calFutureMoves() {
            int[] DY = {1, 0, -1, 0};
            int[] DX = {0, 1, 0, -1};
            int cy = 0;
            int cx = 0;
            int d = 0;
            boolean[][] visited = new boolean[N][N];
            visited[0][0] = true;
            this.idx = N * N - 2;
            this.moves[this.idx][0] = cy;
            this.moves[this.idx][1] = cx;

            while (!(cy == (int) N / 2 && cx == (int) N / 2)) {
                int ny = cy + DY[d];
                int nx = cx + DX[d];
                if (oob(ny, nx) || visited[ny][nx]) {
                    d = Math.floorMod(d + 1, 4);
                    ny = cy + DY[d];
                    nx = cx + DX[d];
                }
                cy = ny;
                cx = nx;
                visited[cy][cx] = true;
                this.idx++;
                this.moves[this.idx][0] = cy;
                this.moves[this.idx][1] = cx;
            }

            for (int i = 0; N * N - 2 - i >= 0; i++) {
                this.moves[N * N - 2 - i][0] = this.moves[N * N - 2 + i][0];
                this.moves[N * N - 2 - i][1] = this.moves[N * N - 2 + i][1];
            }

            for (int i = 0; i < this.moves.length; i++) {
                cy = this.moves[i][0];
                cx = this.moves[i][1];
                int ny = this.moves[Math.floorMod(i + 1, this.moves.length)][0];
                int nx = this.moves[Math.floorMod(i + 1, this.moves.length)][1];
                this.moves[i][2] = ny - cy;
                this.moves[i][3] = nx - cx;
            }

            this.idx = this.moves.length - 1;
        }
        void move() {
            this.idx = Math.floorMod(this.idx + 1, this.moves.length);
            this.y = this.moves[idx][0];
            this.x = this.moves[idx][1];
            this.dy = this.moves[idx][2];
            this.dx = this.moves[idx][3];
        }
        int catchRunners() {
            Set<Integer> catching_indexes = new HashSet<>();
            for (int d = 0; d < 3; d++) {
                int ny = y + dy * d;
                int nx = x + dx * d;
                if (oob(ny, nx)) break;
                if (trees[ny][nx]) continue;
                catching_indexes.add(ny * N + nx);
            }

            int cnt = 0;
            List<Runner> next_runners = new ArrayList<>();
            for (Runner runner: runners) {
                if (catching_indexes.contains(runner.y * N + runner.x)) {
                    cnt++;
                } else {
                    next_runners.add(runner);
                }
            }
            runners = next_runners;
            return cnt;
        }

    }

    private static boolean oob(int y, int x) {
        return y < 0 || N <= y || x < 0 || N <= x;
    }
}
