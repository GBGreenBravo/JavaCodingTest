package baekjoon.gold;

// 20250423
// 13:48
// 1 / 1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노 {
	
	private static int N, M;
	private static int max_answer = Integer.MIN_VALUE;
	private static int[][] area;
	private static int[][][] shape_arr = new int[][][] {
		{{0, 1}, {0, 2}, {0, 3}},
		{{1, 0}, {2, 0}, {3, 0}},
		
		{{0, 1}, {1, 0}, {1, 1}},
		
		{{1, 0}, {2, 0}, {2, 1}},
		{{1, 0}, {2, 0}, {2, -1}},
		{{0, 1}, {0, 2}, {1, 2}},
		{{0, 1}, {0, 2}, {-1, 2}},
		{{-1, 0}, {-2, 0}, {-2, 1}},
		{{-1, 0}, {-2, 0}, {-2, -1}},
		{{0, -1}, {0, -2}, {1, -2}},
		{{0, -1}, {0, -2}, {-1, -2}},
		
		{{1, 0}, {1, 1}, {2, 1}},
		{{1, 0}, {1, -1}, {2, -1}},
		{{0, 1}, {-1, 1}, {-1, 2}},
		{{0, 1}, {1, 1}, {1, 2}},
		
		{{-1, -1}, {-1, 0}, {-1, 1}},
		{{1, -1}, {1, 0}, {1, 1}},
		{{-1, 1}, {0, 1}, {1, 1}},
		{{-1, -1}, {0, -1}, {1, -1}}	
	};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		area = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				check(i, j);
			}
		} 
		
		System.out.println(max_answer);
	}
	
	private static void check(int y, int x) {
		for (int[][] shape: shape_arr) {
			int now_score = area[y][x];
			for (int[] dydx: shape) {
				int ny = y + dydx[0];
				int nx = x + dydx[1];
				if (oob(ny, nx)) {
					now_score = 0;
					break;
				}
				now_score += area[ny][nx];
			}
			max_answer = Math.max(max_answer, now_score);
		}
	}

	private static boolean oob(int y, int x) {
		return y < 0 || N <= y || x < 0 || M <= x;
	}
}
