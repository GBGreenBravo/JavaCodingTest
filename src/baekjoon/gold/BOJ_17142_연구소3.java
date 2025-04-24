package baekjoon.gold;

// 20250424
// 39:12
// 1 / 1

// int[][] 복사 시 clone()을 활용하려면, 
// clone()이 내부 배열은 얕은복사를 하기에, 
// for문 안에서 row.clone()을 해줘야 한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17142_연구소3 {
	
	private static int[] DY = {0, 0, 1, -1};
	private static int[] DX = {1, -1, 0, 0};
	
	private static int N, M;
	private static int[][] area;
	private static List<Node> viruses = new ArrayList<>();
	private static int[] dfs_arr;
	private static int min_answer = Integer.MAX_VALUE;

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
				if (area[i][j] == 2) {
					area[i][j] = 0;
					viruses.add(new Node(i, j));
				}
			}
		}
		
		dfs_arr = new int[M];
		dfs(0, 0);
		
		System.out.println((min_answer == Integer.MAX_VALUE) ? -1 : min_answer);
	}
	
	private static void check() {
//		int[][] copied_area = new int[N][N];		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				copied_area[i][j] = area[i][j];
//			}
//		}
		int[][] copied_area = new int[N][N];
		for (int i = 0; i < N; i++) {
			copied_area[i] = area[i].clone();
		}
		
		Queue<Node> queue = new ArrayDeque<>();
		for (int i: dfs_arr) {
			Node node = viruses.get(i);
			queue.offer(node);
			copied_area[node.y][node.x] = 1;
		}
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			for (int d = 0; d < 4; d++) {
				int ny = node.y + DY[d];
				int nx = node.x + DX[d];
				if (oob(ny, nx) || (copied_area[ny][nx] != 0)) continue;
				copied_area[ny][nx] = copied_area[node.y][node.x] + 1;
				queue.offer(new Node(ny, nx));
			}
		}
		
		for (Node virus: viruses) {
			copied_area[virus.y][virus.x] = -1;
		}
		
		int now_answer = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (copied_area[i][j] == 0) return;
				now_answer = Math.max(now_answer, copied_area[i][j]);
			}
		} 
		min_answer = Math.min(min_answer, now_answer - 1); 
	}
	
	private static void dfs(int start_idx, int depth) {
		if (depth == M) {
			check();
			return;
		}

		for (int i = start_idx; i < viruses.size(); i++) {
			dfs_arr[depth] = i;
			dfs(i + 1, depth + 1);
		}
	}

	private static class Node {
		int y, x;
		Node (int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	private static boolean oob(int y, int x) {
		return y < 0 || N <= y || x < 0 || N <= x;
	}
}
