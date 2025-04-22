package baekjoon.gold;

// 20250422
// 26:30
// 1 / 1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_21610_마법사상어와비바라기 {
	
	private static int[] DY8 = {0, -1, -1, -1, 0, 1, 1, 1};
	private static int[] DX8 = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	private static int[] DY4 = {-1, -1, 1, 1};
	private static int[] DX4 = {-1, 1, 1, -1};
	
	private static int N, M;
	private static int[][] area;
	
	private static ArrayList<Node> clouds = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		area = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		clouds.add(new Node(N - 1, 0));
		clouds.add(new Node(N - 1, 1));
		clouds.add(new Node(N - 2, 0));
		clouds.add(new Node(N - 2, 1));
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			order(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
		}
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer += area[i][j];
			}
		}
		System.out.println(answer);
	}
	
	private static class Node {
		int y, x;
		Node (int y, int x) {
			this.y = y;
			this.x = x;
		}
		void moveAndRain(int d, int s) {
			this.y += DY8[d] * s;
			while ((this.y < 0) || (N <= this.y)) {
				if (this.y < 0) this.y += N;
				else this.y -= N;
			}
			this.x += DX8[d] * s;
			while ((this.x < 0) || (N <= this.x)) {
				if (this.x < 0) this.x += N;
				else this.x -= N;
			}
			
			area[this.y][this.x] += 1; 
		}
		void copyBug() {
			int more_rain = 0;
			for (int d = 0; d < 4; d++) {
				int ny = this.y + DY4[d];
				int nx = this.x + DX4[d];
				if (!oob(ny, nx) && area[ny][nx] > 0) more_rain++;
			}
			area[this.y][this.x] += more_rain; 
		}
	}
	
	private static void order(int d, int s) {
		for (Node node: clouds) {
			node.moveAndRain(d, s);
		}
		
		for (Node node: clouds) {
			node.copyBug();
		}
		
		HashSet<Integer> cloud_set = new HashSet<>();
		for (Node node: clouds) {
			cloud_set.add(node.y * N + node.x);
		}
		
		ArrayList<Node> next_clouds = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((area[i][j] >= 2) && !cloud_set.contains(i * N + j)) {
					next_clouds.add(new Node(i, j));
					area[i][j] -= 2;
				}
			}
		}
		clouds = next_clouds;
		
	}
	
	private static boolean oob(int y, int x) {
		return y < 0 || N <= y || x < 0 || N <= x;
	}

}
