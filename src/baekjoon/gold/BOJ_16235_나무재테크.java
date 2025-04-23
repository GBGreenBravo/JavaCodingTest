package baekjoon.gold;

// 20240423
// 36:17
// 1 / 2

// 79번째 라인의 reverse()를 하지 않아서, 한 번 틀림.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_16235_나무재테크 {
	
	private static int[] DY = {-1, -1, -1, 0, 1, 1, 1, 0};
	private static int[] DX = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	private static int N, M, K;
	private static int[][] added;
	private static Node[][] area;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		added = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				added[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		area = new Node[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				area[i][j] = new Node();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			area[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1].trees.add(new Tree(Integer.parseInt(st.nextToken())));
		}
		
		for (int i = 0; i < K; i++) {
			springAndSummer();
			autumn();
			winter();
		}
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer += area[i][j].trees.size();
			}
		}
		System.out.println(answer);
	}
	
	private static void springAndSummer() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ArrayList<Tree> next_trees = new ArrayList<>();
				int added_food = 0;
				for (int t = area[i][j].trees.size() - 1; t >= 0; t--) {
					Tree tree = area[i][j].trees.get(t);
					if (area[i][j].food >= tree.age) {
						next_trees.add(tree);
						area[i][j].food -= tree.age;
					} else {
						added_food += (tree.age / 2);
					}
				}
				Collections.reverse(next_trees);
				area[i][j].trees = next_trees;
				area[i][j].food += added_food;
				
				for (Tree tree: area[i][j].trees) {
					tree.age++;
				}
			}
		}
	}
	
	private static void autumn() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (Tree tree: area[i][j].trees) {
					if (tree.age % 5 == 0) {
						for (int d = 0; d < 8; d++) {
							int ni = i + DY[d];
							int nj = j + DX[d];
							if (!oob(ni, nj)) {
								area[ni][nj].trees.add(new Tree());
							}
						}
					}
				}
			}
		}
	}
	
	private static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				area[i][j].food += added[i][j];
			}
		}
	}
	
	private static class Node {
		int food;
		ArrayList<Tree> trees;
		Node() {
			this.food = 5;
			this.trees = new ArrayList<>();
		}
	}
	
	private static class Tree {
		int age;
		Tree() {
			this.age = 1;
		}
		Tree(int age) {
			this.age = age;
		}
	}
	
	private static boolean oob(int y, int x) {
		return y < 0 || N <= y || x < 0 || N <= x;
	}

}
