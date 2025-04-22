package baekjoon.gold;

// 20250422
// 31:09
// 1 / 1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15686_치킨배달 {
	
	private static int N, M;
	private static ArrayList<Node> houses = new ArrayList<>();
	private static ArrayList<Node> chickens = new ArrayList<>();
	
	private static ArrayList<Integer> dfs_arr = new ArrayList<>();
	private static boolean[] visited;
	
	private static int min_answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int flag = Integer.parseInt(st.nextToken());
				if (flag == 1) {
					houses.add(new Node(i, j));
				} else if (flag == 2) {
					chickens.add(new Node(i, j));
				}
			}
		}
		
		visited = new boolean[chickens.size()];
		dfs(0);
		
		System.out.println(min_answer);
	}
	
	private static void dfs(int start_idx) {
		
		if (dfs_arr.size() == M) {
			checkAnswer();
			return;
		}
		
		for (int i = start_idx; i < chickens.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs_arr.add(i);
				dfs(i + 1);
				dfs_arr.remove(dfs_arr.size() - 1);
				visited[i] = false;
			}
		}
	}
	
	private static void checkAnswer() {
		int now_answer = 0;
		for (int i = 0; i < houses.size(); i++) {
			Node house = houses.get(i);
			int min_distance = Integer.MAX_VALUE;
			for (int j: dfs_arr) {
				min_distance = Math.min(min_distance, house.calDistance(chickens.get(j)));
			}
			now_answer += min_distance;
			if (min_answer <= now_answer) return;
		}
		min_answer = now_answer;
	}
	
	private static class Node {
		int y, x;
		Node (int y, int x) {
			this.y = y;
			this.x = x;
		}
		int calDistance(Node node) {
			return Math.abs(this.y - node.y) + Math.abs(this.x - node.x);
		}
	}

}
