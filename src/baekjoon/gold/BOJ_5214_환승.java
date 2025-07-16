package baekjoon.gold;

// 20250716
// 19:59
// 1 / 1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_5214_환승 {
	
	static int N, K, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] visited = new int[N + 1]; 
		byte[] visited_hypertube = new byte[M]; 
		int[][] hypertube = new int[M][K];
		Map<Integer, Set<Integer>> connected = new HashMap<>();
		for (int i = 1; i < N + 1; i++) {
			connected.put(i,  new HashSet<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < K; j++) {
				int vv = Integer.parseInt(st.nextToken());
				hypertube[i][j] = vv;
				connected.get(vv).add(i);
			}
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(1);
		visited[1] = 1;
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			if (now == N) {
				break;
			}
			for (int h_idx : connected.get(now)) {
				if (visited_hypertube[h_idx] == 0) {
					visited_hypertube[h_idx] = 1;
					for (int next : hypertube[h_idx]) {
						if (visited[next] == 0) {
							visited[next] = visited[now] + 1;
							queue.add(next);
						}
					}
				}
			}
		}
		
		if (visited[N] != 0) {
			System.out.println(visited[N]);
		} else {
			System.out.println(-1);
		}
		
	}

}
