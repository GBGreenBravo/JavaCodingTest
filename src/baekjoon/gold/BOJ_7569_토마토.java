package baekjoon.gold;

// 20250420
// 44:42
// 1 / 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569_토마토 {
	
	static int M;
	static int N;
	static int H;
	static int[][][] area;
	
	static final int[] DH = {-1, 1, 0, 0, 0, 0};
	static final int[] DY = {0, 0, -1, 1, 0, 0};
	static final int[] DX = {0, 0, 0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		area = new int[H][N][M];
		int target_cnt = 0;
		int rotten_cnt = 0;
		
		Queue<int[]> queue = new LinkedList<>();
		
		for (int h = 0; h < H; h++) {
			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < M; x++) {
					int now = Integer.parseInt(st.nextToken());
					area[h][y][x] = now;
					if (now >= 0) {
						target_cnt++;
						if (now == 1) {
							rotten_cnt++;
							queue.offer(new int[]{h, y, x});
						}
					}
				}
			}
		}
		
		int days = 0;
		while (!queue.isEmpty()) {
			if (target_cnt == rotten_cnt) break;
			Queue<int[]> next_queue = new LinkedList<>();
			while (!queue.isEmpty()) {
				int[] tmp = queue.poll();
				int h = tmp[0];
				int y = tmp[1];
				int x = tmp[2];
				
				for (int i = 0; i < 6; i++) {
					int nh = h + DH[i];
					int ny = y + DY[i];
					int nx = x + DX[i];
					if (oob(nh, ny, nx) || area[nh][ny][nx] != 0) continue;
					area[nh][ny][nx] = 1;
					next_queue.offer(new int[]{nh, ny, nx});
					rotten_cnt++;
				}
			}
			queue = next_queue;
			days++;
		}
		
		int answer;
		if (days == 0 && rotten_cnt != 0) {
			answer = 0;
		} else if (target_cnt != rotten_cnt) {
			answer = -1;
		} else {
			answer = days;
		}
		System.out.println(answer);
	}
	
	private static boolean oob(int h, int y, int x) {
		return h < 0 || H <= h || y < 0 || N <= y || x < 0 || M <= x;
	}

}
