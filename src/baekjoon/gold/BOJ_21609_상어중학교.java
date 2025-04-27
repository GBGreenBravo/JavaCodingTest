package baekjoon.gold;

// 20250427
// 1:46:30
// 1 / 6

// Comparable을 상속받아 compareTo()를 오버라이딩할 때,
// a.compare(b)와 b.compare(a)가 0이 아닌 같은 값(1이나 -1)이 되어서는 안된다!	
// 이유 : 자바 정렬 라이브러리 (Collections.sort 등)는 compareTo가 "일관성 있는 비교"를 한다고 가정함.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21609_상어중학교 {
	
	private static int[] DY = {0, 0, 1, -1};
	private static int[] DX = {1, -1, 0, 0};
	private static int N, M;
	private static int[][] area;
	private static int score;

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
		
		while (true) {
			BlockGroup blockGroup = findBlockGroup();
			if (blockGroup == null) break;
			gravity();
			rotateCounterclockwise();
			gravity();
		}
		
		System.out.println(score);
	}
	
	private static BlockGroup findBlockGroup() {
		BlockGroup candidate = null;
		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (area[i][j] > 0 && !visited[i][j]) {
					visited[i][j] = true;
					Queue<int[]> queue = new ArrayDeque<>();
					queue.offer(new int[] {i, j});
					
					List<Block> blocks = new ArrayList<>();
					blocks.add(new Block(i, j, area[i][j]));
					
					while (!queue.isEmpty()) {
						int[] yx = queue.poll();
						for (int d = 0; d < 4; d++) {
							int ny = yx[0] + DY[d];
							int nx = yx[1] + DX[d];
							if (oob(ny, nx) || visited[ny][nx] || !(area[ny][nx] == 0 || area[ny][nx] == area[i][j])) continue;
							visited[ny][nx] = true;
							queue.offer(new int[] {ny, nx});
							blocks.add(new Block(ny, nx, area[ny][nx]));
						}
					}
					
					for (Block block: blocks) {
						if (block.type == 0) visited[block.y][block.x] = false; 
					}
					
					if (blocks.size() > 1) {
						BlockGroup nowGroup = new BlockGroup(blocks);
						if (candidate == null || nowGroup.compareTo(candidate) < 0) {
							candidate = nowGroup;
						}
					}
				}
			}
		}
		if (candidate != null) {
			for (Block block: candidate.blocks) {
				area[block.y][block.x] = -2; 
			}
			score += Math.pow(candidate.blocks.size(), 2);
			return candidate;
		} else {
			return null;
		}
	}
	
	private static void gravity() {
		for (int j = 0; j < N; j++) {
			for (int i = N - 2; i >= 0; i--) {
				if (area[i][j] == -2 || area[i][j] == -1) continue;
				
				int row = i;
				while (row != N - 1) {
					if (area[row + 1][j] == -2) {
						area[row + 1][j] = area[row][j];
						area[row][j] = -2;
						row++;
					} else {
						break;
					}
				}
			}	
		}
	}
	
	private static void rotateCounterclockwise() {
		int[][] copied_area = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copied_area[N - 1 - j][i] = area[i][j];
			}
		}
		area = copied_area;
	}
	
	private static class BlockGroup implements Comparable<BlockGroup>{
		List<Block> blocks;
		int rainbow_cnt;
		Block criteria;
		BlockGroup(List<Block> blocks) {
			Collections.sort(blocks);
			this.blocks = blocks;
			this.rainbow_cnt = 0;
			for (Block block: blocks) {
				if (block.type == 0) this.rainbow_cnt++;
			}
			this.criteria = blocks.get(0);
		}
		@Override
		public int compareTo(BlockGroup o) {
			if (Integer.compare(this.blocks.size(), o.blocks.size()) != 0) return -Integer.compare(this.blocks.size(), o.blocks.size());
			if (Integer.compare(this.rainbow_cnt, o.rainbow_cnt) != 0) return -Integer.compare(this.rainbow_cnt, o.rainbow_cnt);
			if (Integer.compare(this.criteria.y, o.criteria.y) != 0) return -Integer.compare(this.criteria.y, o.criteria.y);
			return -Integer.compare(this.criteria.x, o.criteria.x);
		}
	}
	
	private static class Block implements Comparable<Block>{
		int y, x, type;
		Block(int y, int x, int type) {
			this.y = y;
			this.x = x;
			this.type = type;
		}
		@Override
		public int compareTo(Block o) {
//			if (this.type == 0 || this.type == -1) return 1;
			if (this.type == 0 && o.type != 0) return 1;
			if (this.type != 0 && o.type == 0) return -1;
			
			if (Integer.compare(this.y, o.y) != 0) return Integer.compare(this.y, o.y);
			else return Integer.compare(this.x, o.x);
		}
	}

	private static boolean oob(int y, int x) {
		return y < 0 || N <= y || x < 0 || N <= x;
	}
}
