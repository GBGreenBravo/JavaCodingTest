package kim.minhyeop;

// 20250423
// 52:13
// 1 / 1

// 정렬/비교 처음 접해서 오래 걸렸음. 다양한 방식 있으므로 다양하게 익혀야 함.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_21608_상어초등학교 {
	
	private static int[] DY = {0, 0, -1, 1};
	private static int[] DX = {-1, 1, 0, 0};
	
	private static int N;
	private static Map<Integer, Set<Integer>> favorites;
	private static int[][] area;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		area = new int[N][N];
		favorites = new HashMap<>();
		int[] placing_order = new int[N * N];
		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			int now_student = Integer.parseInt(st.nextToken());
			placing_order[i] = now_student;
			Set<Integer> now_favorites = new HashSet<>();
			for (int j = 0; j < 4; j++) {
				now_favorites.add(Integer.parseInt(st.nextToken()));
			}
			favorites.put(now_student, now_favorites);
		}
		
		for (int i = 0; i < N * N; i++) {
			place(placing_order[i]);
		}
		
		int score = 0;
		int[] score_arr = {0, 1, 10, 100, 1000};
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				score += score_arr[countFavorite(i, j, favorites.get(area[i][j]))];
			}
		}
		System.out.println(score);
	}

	private static void place(int now_student) {
		Candidate seat = new Candidate(N, N, -1, -1);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (area[i][j] == 0) {
					Candidate candidate = new Candidate(i, j, countFavorite(i, j, favorites.get(now_student)), countZero(i, j));
					if (candidate.compareTo(seat) < 0) {
						seat = candidate;
					}
				}
			}
		}
		area[seat.y][seat.x] = now_student;
	}
	
	private static int countFavorite(int y, int x, Set<Integer> favorite_set) {
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int ny = y + DY[d];
			int nx = x + DX[d];
			if (!oob(ny, nx) && (favorite_set.contains(area[ny][nx]))) cnt++;
		}
		return cnt;
	}
	
	private static int countZero(int y, int x) {
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int ny = y + DY[d];
			int nx = x + DX[d];
			if (!oob(ny, nx) && (area[ny][nx] == 0)) cnt++;
		}
		return cnt;
	}
	
	private static class Candidate implements Comparable<Candidate> {
		int y, x, favorite_cnt, zero_cnt;
		Candidate (int y, int x, int f, int z) {
			this.y = y;
			this.x = x;
			this.favorite_cnt = f;
			this.zero_cnt = z;
		}
		@Override
		public int compareTo(Candidate c) {
			if (this.favorite_cnt > c.favorite_cnt) return -1;
			else if (this.favorite_cnt < c.favorite_cnt) return 1;
			
			if (this.zero_cnt > c.zero_cnt) return -1;
			else if (this.zero_cnt < c.zero_cnt) return 1;
			
			if (this.y < c.y) return -1;
			else if (this.y > c.y) return 1;
			
			if (this.x < c.x) return -1;
			else return 1;
		}
	}
	
	private static boolean oob(int y, int x) {
		return y < 0 || N <= y || x < 0 || N <= x;
	}
	
}
