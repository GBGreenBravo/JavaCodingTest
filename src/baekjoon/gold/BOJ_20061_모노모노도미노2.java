package baekjoon.gold;

// 20250427
// 38:42
// 1 / 1

// 행 초기화 시 new boolean[i]; 해도 되지만,
// Arrays.fill(area[i], false); 가 더 명시적일 수 있음.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20061_모노모노도미노2 {
	
	private static int N;
	private static boolean[][] green = new boolean[6][4];
	private static boolean[][] blue = new boolean[6][4];
	private static int score = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			put(green, new Block(t, x, true));
			put(blue, new Block(t, 3 - y, false));
			checkFilled(green);
			checkFilled(blue);
			checkLight(green);
			checkLight(blue);
		}
		
		System.out.println(score);
		System.out.println(countBlocks(green) + countBlocks(blue));
	}
	
	private static void put(boolean[][] area, Block block) {
		going_down:
		while (true) {
			for (int[] dydx: block.positions) {
				int ny = block.y + 1 + dydx[0];
				int nx = block.x + dydx[1];
				if (oob(ny, nx) || area[ny][nx]) break going_down;
			}
			block.y++;
		}
		for (int[] dydx: block.positions) {
			int ny = block.y + dydx[0];
			int nx = block.x + dydx[1];
			area[ny][nx] = true;
		}
	}
	
	private static void checkFilled(boolean[][] area) {
		for (int i = 0; i < 6; i++) {
			boolean filled = true; 
			for (int j = 0; j < 4; j++) {
				if (!area[i][j]) {
					filled = false;
					break;
				}
			} 
			if (filled) {
				score++;
//				area[i] = new boolean[4];
				Arrays.fill(area[i], false);
			}
		}
		
		for (int i = 4; i >= 0; i--) {
			int row = i;
			while (row != 5) {
				boolean empty_below = true;
				for (int j = 0; j < 4; j++) {
					if (area[row + 1][j]) {
						empty_below = false;
						break;
					}
				}
				if (empty_below) {
					area[row + 1] = area[row].clone();
//					area[row] = new boolean[4];
					Arrays.fill(area[row], false);
					row++;
				} else break;
			}
		}
	}
	
	private static void checkLight(boolean[][] area) {
		int row_cnt = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				if (area[i][j]) {
					row_cnt++;
					break;
				}
			}
		}
		
		if (row_cnt == 0) return;
		
		for (int i = 5; i >= 2; i--) {
			area[i] = area[i - row_cnt].clone();
		}
		for (int i = 0; i < 2; i++) {
//			area[i] = new boolean[4];
			Arrays.fill(area[i], false);
		}
	}
	
	private static int countBlocks(boolean[][] area) {
		int cnt = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if (area[i][j]) cnt++;
			}
		}
		return cnt;
	}
	
	private static class Block {
		int y, x;
		int[][] positions;
		Block(int t, int x, boolean is_green) {
			this.y = 0;
			this.x = x;
			switch (t) {
				case 1: {
					positions = new int[][] {{0, 0}};
					break;
				}
				case 2: {
					if (is_green) {
						positions = new int[][] {{0, 0}, {0, 1}};
					} else {
						positions = new int[][] {{0, 0}, {1, 0}};
					}
					break;
				}
				case 3: {
					if (is_green) {
						positions = new int[][] {{0, 0}, {1, 0}};
					} else {
						positions = new int[][] {{0, 0}, {0, -1}};
					}	
					break;
				}
			}
		}
	}
	
	private static boolean oob(int y, int x) {
		return y < 0 || 6 <= y || x < 0 || 4 <= x;
	}

}
