package baekjoon.gold;

// 20250422
// 20:50
// 1 / 1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503_로봇청소기 {
	
	// 북 동 남 서
	static int[] DY = {-1, 0, 1, 0};
	static int[] DX = {0, 1, 0, -1};
	
	static int N, M;
	static boolean[][] cleaned, wall;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cleaned = new boolean[N][M];
		
		st = new StringTokenizer(br.readLine());
		Robot robot = new Robot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		wall = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) wall[i][j] = true;
			}
		}
		
		System.out.println(clean(robot));
	}
	
	static class Robot {
		int y, x, d;
		Robot(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}
	
	private static int clean(Robot robot) {
		int cleaned_cnt = 0;
		
		while (true) {
			if (!cleaned[robot.y][robot.x]) {
				cleaned[robot.y][robot.x] = true; 
				cleaned_cnt++;
				continue;
			}
			
			int near_cleaned_cnt = 0;
			for (int d = 0; d < 4; d++) {
				int ny = robot.y + DY[d];
				int nx = robot.x + DX[d];
				if (oob(ny, nx) || wall[ny][nx] || cleaned[ny][nx]) continue;
				near_cleaned_cnt++;
			}
			
			if (near_cleaned_cnt == 0) {
				int ny = robot.y - DY[robot.d];
				int nx = robot.x - DX[robot.d];
				if (!wall[ny][nx]) {
					robot.y = ny;
					robot.x = nx;
				} else {
					return cleaned_cnt;
				}
			} else {
				robot.d = (robot.d + 3) % 4;
				int ny = robot.y + DY[robot.d];
				int nx = robot.x + DX[robot.d];
				if (!oob(ny, nx) && !wall[ny][nx] && !cleaned[ny][nx]) {
					robot.y = ny;
					robot.x = nx;
				}
			}
		}
	}
	
	private static boolean oob(int y, int x) {
		return y < 0 || N <= y || x < 0 || M <= x;
	}

}
