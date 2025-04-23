package kim.minhyeop;

// 20250423
// 28:57
// 1 / 1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3190_뱀 {
	
	// 동 남 서 북
	private static int[] DY = {0, 1, 0, -1};
	private static int[] DX = {1, 0, -1, 0};
	
	private static int N, K, L;
	private static boolean[][] apple_area;
	private static boolean[][] snake_area;
	
	private static Map<Integer, String> turn_infos = new HashMap<>(); 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		snake_area = new boolean[N][N];
		snake_area[0][0] = true;
		Snake snake = new Snake();
		
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		apple_area = new boolean[N][N];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			apple_area[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
		}
		
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			turn_infos.put(Integer.parseInt(st.nextToken()), st.nextToken().trim());
		}
		
		int time = 0;
		while (true) {
			time++;
			
			int[] nynx = snake.nextHead();
			int ny = nynx[0];
			int nx = nynx[1];
			if (oob(ny, nx) || snake_area[ny][nx]) break;
			
			snake.move();
			
			if (turn_infos.containsKey(time)) {
				if (turn_infos.get(time).equals("D")) snake.clockwise();
				else snake.counterclockwise();
			}
			
		}
		System.out.println(time);
	}
	
	private static class Snake {
		int y, x, d;
		Queue<int[]> position;
		Snake() {
			this.y = 0;
			this.x = 0;
			this.d = 0;
			this.position = new ArrayDeque<>();
			position.add(new int[] {0, 0});
		}
		void clockwise() {
			this.d = Math.floorMod((d + 1), 4);
		}
		void counterclockwise() {
			this.d = Math.floorMod((d - 1), 4);
		}
		int[] nextHead() {
			return new int[] {this.y + DY[d], this.x + DX[d]};
		}
		void move() {
			int ny = this.y + DY[d];
			int nx = this.x + DX[d];
			this.y = ny;
			this.x = nx;
			this.position.offer(new int[] {ny, nx});
			snake_area[ny][nx] = true;
			if (apple_area[ny][nx]) {
				apple_area[ny][nx] = false;
			} else {
				int[] bybx = this.position.poll();
				snake_area[bybx[0]][bybx[1]] = false;
			}
		}
	}
	
	private static boolean oob(int y, int x) {
		return y < 0 || N <= y || x < 0 || N <= x;
	}
	
}
