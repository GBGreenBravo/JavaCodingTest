package codetree;

// 20250429
// 57:49
// 1 / 2

// 공격자의 최근공격시점 갱신(63번째 줄) 안 해줘서 틀림.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class CTR_2023상반기오전1번_포탑부수기 {
	
	private static int[] DY4 = {0, 1, 0, -1};
	private static int[] DX4 = {1, 0, -1, 0};
	private static int[] DY8 = {-1, -1, -1, 0, 1, 1, 1, 0};
	private static int[] DX8 = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	private static int N, M, K;
	private static Turret[][] area;
	private static int remain_turret_cnt = 0;
	private static Turret weakest;
	private static Turret strongest;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		area = new Turret[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int power = Integer.parseInt(st.nextToken());
				if (power != 0) remain_turret_cnt++;
				area[i][j] = new Turret(i, j, power);
			}
		}
		
		for (int turn = 1; turn <= K; turn++) {
			if (remain_turret_cnt == 1) break;
			
			weakest = new Turret(-1, -1, Integer.MAX_VALUE);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (area[i][j].power == 0) continue;
					if (weakest.compareTo(area[i][j]) > 0) weakest = area[i][j];
				}
			}
			weakest.power += N + M;
			weakest.affected_turn = turn;
			weakest.recent_attacked = turn;
			
			strongest = new Turret(-1, -1, Integer.MIN_VALUE);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (i == weakest.y && j == weakest.x) continue;
					if (strongest.compareTo(area[i][j]) < 0) strongest = area[i][j];
				}
			}
			
			if (!weakest.laser(strongest, turn)) weakest.shell(strongest, turn);
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (area[i][j].power != 0 && area[i][j].affected_turn != turn) area[i][j].power++;
				}
			}
		}
		
		int max_power = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (max_power < area[i][j].power) max_power = area[i][j].power;
			}
		}
		System.out.println(max_power);
		
	}
	
	private static class Turret implements Comparable<Turret> {
		int y, x, power, recent_attacked, affected_turn;
		Turret (int y, int x, int power) {
			this.y = y;
			this.x = x;
			this.power = power;
			this.recent_attacked = 0;
			this.affected_turn = 0;
		}
		@Override
		public int compareTo(Turret o) {
			if (Integer.compare(this.power, o.power) != 0) return Integer.compare(this.power, o.power);
			if (Integer.compare(this.recent_attacked, o.recent_attacked) != 0) return -Integer.compare(this.recent_attacked, o.recent_attacked);
			if (Integer.compare(this.y + this.x, o.y + o.x) != 0) return -Integer.compare(this.y + this.x, o.y + o.x);
			return -Integer.compare(this.x, o.x);
		}
		
		boolean laser(Turret target, int turn) {
			Queue<Object[]> queue = new ArrayDeque<>();
			queue.offer(new Object[] {this.y, this.x, new ArrayList<int[]>()});
			
			boolean[][] visited = new boolean[N][M];
			visited[this.y][this.x] = true;
			
			while (!queue.isEmpty()) {
				Object[] yxroute = queue.poll();
				int cy = (int) yxroute[0];
				int cx = (int) yxroute[1];
				List<int[]> route = (List<int[]>) yxroute[2];
				
				if (cy == target.y && cx == target.x) {
					for (int r = 0; r < route.size() - 1; r++) {
						int[] yx = route.get(r);
						area[yx[0]][yx[1]].attacked((int) this.power / 2, turn);
					}
					int[] yx = route.get(route.size() - 1);
					area[yx[0]][yx[1]].attacked(this.power, turn);
					
					return true;
				}
				
				for (int d = 0; d < 4; d++) {
					int ny = Math.floorMod(cy + DY4[d], N);
					int nx = Math.floorMod(cx + DX4[d], M);
					if (visited[ny][nx] || area[ny][nx].power == 0) continue;
					visited[ny][nx] = true;
					List<int[]> next_route = route.stream().map(int[]::clone).collect(Collectors.toList());
					next_route.add(new int[] {ny, nx});
					queue.offer(new Object[] {ny, nx, next_route});
				}
			}
			
			
			return false;
		}
		
		void shell(Turret target, int turn) {
			int ty = target.y;
			int tx = target.x;
			target.attacked(this.power, turn);
			
			for (int d = 0; d < 8; d++) {
				int ny = Math.floorMod(ty + DY8[d], N);
				int nx = Math.floorMod(tx + DX8[d], M);
				if (area[ny][nx].power == 0 || (ny == this.y && nx == this.x)) continue;
				area[ny][nx].attacked((int) this.power / 2, turn);
			}
		}
		
		void attacked(int minus_power, int turn) {
			this.power -= minus_power;
			this.affected_turn = turn;
			if (this.power <= 0) {
				this.power = 0;
				remain_turret_cnt--;
			}
		}
	}
}
