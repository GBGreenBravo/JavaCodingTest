package baekjoon.gold;

// 20250422
// 34:25
// 1 / 1

// LinkedList/ArrayList 활용해서 다시 푼 풀이는 아래에.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_20055_컨베이어벨트위의로봇 {
	
	private static int N, K;
	private static int stage = 1;
	
	private static int[] powers;
	private static List<Robot> robots = new ArrayList<>();
	private static int zero_cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		powers = new int[2 * N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			powers[i] = Integer.parseInt(st.nextToken());
			if (powers[i] == 0) zero_cnt++;
		}
		
		process();
		
		System.out.println(stage);
	}
	
	private static void process() {
		while (true) {
			rotate();
			
			move_each_robots();
			
			if (powers[0] > 0) {
				powers[0] -= 1;
				if (powers[0] == 0) zero_cnt++;
				robots.add(new Robot(0));
			}
			
			if (zero_cnt >= K) break;
			stage++;
		}
	}
	
	private static void rotate() {
		for (int i = 0; i < robots.size(); i++) {
			Robot robot = robots.get(i);
			robot.rotate();
		}
		if ((robots.size() > 0) && (robots.get(0).idx == N - 1)) {
			robots.remove(0);
		}
		
		int lastPower = powers[2 * N - 1];
		for (int i = 2 * N - 1; i > 0; i--) {
			powers[i] = powers[i - 1];
		}
		powers[0] = lastPower;
	}
	
	private static void move_each_robots() {
		for (int i = 0; i < robots.size(); i++) {
			Robot robot = robots.get(i);
			if ((i != 0) && (robots.get(i - 1).idx == robot.idx + 1)) continue;
			if (powers[robot.idx + 1] == 0) continue;
			robot.idx += 1;
			powers[robot.idx] -= 1;
			if (powers[robot.idx] == 0) zero_cnt++;
		}
		
		if ((robots.size() > 0) && (robots.get(0).idx == N - 1)) {
			robots.remove(0);
		}
	}
	
	private static class Robot {
		int idx;
		Robot(int idx) {
			this.idx = idx;
		}
		void rotate() {
			this.idx += 1;
		}
	}

}

// LinkedList와 ArrayList가 비슷해 보이지만, get(idx)연산에서 차이가 있음.
// LinkedList는 단순 인덱스 접근이 아니라 앞에서부터 노드를 순회해야 해서 O(N)에 가까운 탐색이 발생한다.
// 따라서, 이번 문제처럼 인덱스 접근이 많으면 => ArrayList
// Queue처럼 삽입/삭제 중심의 문제에,
//     + 인덱스 접근 굳이 필요하다면 => LinkedList
//     + (대부분의 BFS) push/pop만 필요하다면 => ArrayDeque
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, K;
	private static int stage = 1;
	
//	private static LinkedList<Belt> belts;
	private static ArrayList<Belt> belts;
	private static int zero_cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
//		belts = new LinkedList<>();
		belts = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			Belt belt = new Belt(Integer.parseInt(st.nextToken()));
//			belts.offer(belt);
			belts.add(belt);
			if (belt.power == 0) zero_cnt++;
		}
		
		process();
		
		System.out.println(stage);
	}
	
	private static class Belt {
		int power;
		boolean hasRobot;
		Belt (int power) {
			this.power = power;
			this.hasRobot = false;
		}
		void reducePower() {
			this.power -= 1;
			if (this.power == 0) zero_cnt++;
		}
	}
	
	private static void process() {
		while (true) {
			rotate();
			
			move_each_robots();
			
			Belt onboarding = belts.get(0);
			if (onboarding.power > 0) {
				onboarding.hasRobot = true;
				onboarding.reducePower();
			}
			
			if (zero_cnt >= K) break;
			stage++;
		}
	}
	
	private static void rotate() {
//		belts.addFirst(belts.pollLast());
		belts.add(0, belts.remove(2 * N - 1));
		Belt offBoarding = belts.get(N - 1);
		if (offBoarding.hasRobot) {
			offBoarding.hasRobot = false;
		}
	}
	
	private static void move_each_robots() {
		for (int i = N - 2; i >= 0; i--) {
			Belt now_belt = belts.get(i);
			if (!now_belt.hasRobot) continue;
			Belt next_belt = belts.get(i + 1);
			if (!next_belt.hasRobot && (next_belt.power > 0)) {
				next_belt.hasRobot = true;
				next_belt.reducePower(); 
				now_belt.hasRobot = false;
			}
		}
		Belt offBoarding = belts.get(N - 1);
		if (offBoarding.hasRobot) {
			offBoarding.hasRobot = false;
		}
	}

}
*/
