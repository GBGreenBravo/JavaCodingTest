package baekjoon.silver;

// 20250420
// 1 / 3

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2615_오목 {

	static int[][] board;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		
		board = new int[19][19];
		for (int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 19; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int j = 0; j < 19; j++) {
			for (int i = 0; i < 19; i++) {
				if (board[i][j] != 0) {
					check(i, j);	
				}
				if (result != 0) break;
			}
			if (result != 0) break;
		}
		
		if (result == 0) {
			System.out.println(result);
		}
	}
	
	static int[][] direction_arr = {{-1, -1, 1, 1}, {-1, 0, 1, 0}, {-1, 1, 1, -1}, {0, -1, 0, 1}};

	private static void check(int y, int x) {
		for (int d = 0; d < 4; d++) {
			int in_a_row = 1;
			
			int dy = direction_arr[d][0];
			int dx = direction_arr[d][1];
			int ny = y + dy;
			int nx = x + dx;
			while (!oob(ny, nx) && board[ny][nx] == board[y][x]) {
				ny += dy;
				nx += dx;
				in_a_row++;
			}
			
			dy = direction_arr[d][2];
			dx = direction_arr[d][3];
			ny = y + dy;
			nx = x + dx;
			while (!oob(ny, nx) && board[ny][nx] == board[y][x]) {
				ny += dy;
				nx += dx;
				in_a_row++;
			}
			
			if (in_a_row == 5) {
				result = board[y][x];
				System.out.println(result);
				System.out.println((y + 1) + " " + (x + 1));
				return;
			}
		}
	}
	
	private static boolean oob(int y, int x) {
		return y < 0 || 19 <= y || x < 0 || 19 <= x;
	}
	
}
