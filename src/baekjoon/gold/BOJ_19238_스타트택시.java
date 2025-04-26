package baekjoon.gold;

// 20250426
// 44:11
// 1 / 2

// 출발지 찾을 때, 택시랑 같은 위치 손님 체크 바로 안 해서, 한 번 틀림.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_19238_스타트택시 {
	
	private static int[] DY = {0, 0, -1, 1};
	private static int[] DX = {-1, 1, 0, 0};
	
	private static int N, M, fuel, taxi_y, taxi_x;
	private static boolean[][] area;
	private static Set<Integer> starts = new HashSet<>();
	private static Map<Integer, int[]> ends = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		
		area = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				area[i][j] = st.nextToken().equals("1");
			}
		}
		
		st = new StringTokenizer(br.readLine());
		taxi_y = Integer.parseInt(st.nextToken()) - 1;
		taxi_x = Integer.parseInt(st.nextToken()) - 1;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start_num = (Integer.parseInt(st.nextToken()) - 1) * N + (Integer.parseInt(st.nextToken()) - 1);
			int[] end_num = {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
			starts.add(start_num);
			ends.put(start_num, end_num);
		}
		
		System.out.println(solve());
	}
	
	private static int solve() {
		for (int i = 0; i < M; i++) {
			int[] result = find_start();
			if ((result[2] == -1) || (fuel - result[2] <= 0)) {
				return -1;
			}
			taxi_y = result[0];
			taxi_x = result[1];
			fuel -= result[2];
			
			result = find_end();
			if ((result[2] == -1) || (fuel - result[2] < 0)) {
				return -1;
			}
			taxi_y = result[0];
			taxi_x = result[1];
			fuel += result[2];
		}
		
		return fuel;
	}
	
	private static int[] find_start() {
		if (starts.contains(taxi_y * N + taxi_x)) {
			starts.remove(taxi_y * N + taxi_x);
			return new int[] {taxi_y, taxi_x, 0};
		}
		
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {taxi_y, taxi_x, 0});
		visited[taxi_y][taxi_x] = true;
		
		List<int[]> candidates = new ArrayList<>();
		while (!queue.isEmpty()) {
			int q_s = queue.size();
			for (int i = 0; i < q_s; i++) {	
				int[] yx = queue.poll();
				int y = yx[0];
				int x = yx[1];
				int cost = yx[2];
				for (int d = 0; d < 4; d++) {
					int ny = y + DY[d];
					int nx = x + DX[d];
					if (oob(ny, nx) || area[ny][nx] || visited[ny][nx]) continue;
					visited[ny][nx] = true;
					queue.offer(new int[] {ny, nx, cost + 1});
					if (starts.contains(ny * N + nx)) candidates.add(new int[] {ny, nx, cost + 1});
				}
			}
			if (!candidates.isEmpty()) {
				Collections.sort(candidates, (yx1, yx2) -> {
					if (Integer.compare(yx1[0], yx2[0]) != 0) {
						return Integer.compare(yx1[0], yx2[0]);
					}
					else return Integer.compare(yx1[1], yx2[1]);
				});
				
				int[] first = candidates.get(0);
				starts.remove(first[0] * N + first[1]);
				return first;
			}
		}
		return new int[] {0, 0, -1};
	}
	
	private static int[] find_end() {
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {taxi_y, taxi_x, 0});
		visited[taxi_y][taxi_x] = true;
		
		int[] eyx = ends.get(taxi_y * N + taxi_x);
		int ey = eyx[0];
		int ex = eyx[1];
		
		while (!queue.isEmpty()) {
			int[] yx = queue.poll();
			int y = yx[0];
			int x = yx[1];
			int cost = yx[2];
			for (int d = 0; d < 4; d++) {
				int ny = y + DY[d];
				int nx = x + DX[d];
				if (oob(ny, nx) || area[ny][nx] || visited[ny][nx]) continue;
				if ((ny == ey) && (nx == ex)) {
					return new int[] {ny, nx, cost + 1};
				}
				visited[ny][nx] = true;
				queue.offer(new int[] {ny, nx, cost + 1});
			}
		}
		return new int[] {0, 0, -1};
	}

	private static boolean oob(int y, int x) {
		return y < 0 || N <= y || x < 0 || N <= x;
	}

}
