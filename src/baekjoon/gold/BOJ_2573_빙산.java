package baekjoon.gold;

// 20250421
// 1 / 1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573_빙산 {
	
	static int[] DY = {0, 0, -1, 1};
	static int[] DX = {-1, 1, 0, 0};
	
	static int N, M;
	static int[][] area, copied_area;

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
		
		int years = 0;
		while (checkSplit() == 0) {
			melt();
			years++;
		} 
		System.out.println((checkSplit() == 2) ? 0 : years);
	}
	
	private static boolean oob(int y, int x) {
		return y < 0 || N <= y || x < 0 || M <= x;
	}
	
	private static int checkSplit() {
		if (copied_area == null) {
			copied_area = new int[N][M];
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copied_area[i][j] = area[i][j];
			}
		}
		
		boolean bfs_done = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copied_area[i][j] != 0) {
					if (!bfs_done) {
						copiedAreaBFS(i, j);
						bfs_done = true;
					} else {
						return 1;
					}
				}
			}
		}
		
		return bfs_done ? 0 : 2;
	}
	
	static class Node {
		int y, x;
		Node (int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	private static void copiedAreaBFS(int sy, int sx) {
		copied_area[sy][sx] = 0;
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(new Node(sy, sx));
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			for (int d = 0; d < 4; d++) {
				int ny = node.y + DY[d];
				int nx = node.x + DX[d];
				if (oob(ny, nx) || (copied_area[ny][nx] == 0)) continue;
				copied_area[ny][nx] = 0;
				queue.offer(new Node(ny, nx));
			}
		}
	}
	
	static class MeltInfo {
		int y, x, degree;
		MeltInfo(int y, int x, int degree) {
			this.y = y;
			this.x = x;
			this.degree = degree;
		}
	}
	
	private static void melt() {
		ArrayList<MeltInfo> melting = new ArrayList<MeltInfo>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (area[i][j] != 0) {
					int zero_cnt = 0;
					for (int d = 0; d < 4; d++) {
						int ni = i + DY[d];
						int nj = j + DX[d];
						if (!oob(ni, nj) && (area[ni][nj] == 0)) zero_cnt++;
					}
					if (zero_cnt != 0) melting.add(new MeltInfo(i, j, zero_cnt));
				}
			}
		}
		for (MeltInfo meltInfo: melting) {
			area[meltInfo.y][meltInfo.x] -= Math.min(meltInfo.degree, area[meltInfo.y][meltInfo.x]); 
		}
	}

}
