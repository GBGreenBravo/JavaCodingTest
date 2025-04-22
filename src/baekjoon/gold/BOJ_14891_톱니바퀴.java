package baekjoon.gold;

// 20250422
// 21:22
// 1 / 1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14891_톱니바퀴 {
	
	private static ArrayList<ArrayList<Byte>> wheels;
	private static int K;
	private static boolean[] rotated;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		wheels = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			ArrayList<Byte> wheel = new ArrayList<>();
			char[] tmp = st.nextToken().toCharArray();
			for (int j = 0; j < 8; j++) {
				wheel.add(Byte.valueOf(String.valueOf(tmp[j])));
			}
			wheels.add(wheel);
		}
		
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		for (int i = 0; i < K; i++) {
			rotated = new boolean[4];
			
			st = new StringTokenizer(br.readLine());
			rotate(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
		}
		
		int score = 0;
		for (int i = 0; i < 4; i++) {
			score += wheels.get(i).get(0) << i;
		}
		System.out.println(score);
	}
	
	private static void rotate(int rotating_index, int rotating_direction) {
		rotated[rotating_index] = true;
		int left = rotating_index - 1;
		int right = rotating_index + 1;
		if (0 <= left && !rotated[left] && (wheels.get(rotating_index).get(6) != wheels.get(left).get(2))) {
			rotate(left, -1 * rotating_direction);
		}
		if (right < 4 && !rotated[right] && (wheels.get(rotating_index).get(2) != wheels.get(right).get(6)) ) {
			rotate(right, -1 * rotating_direction);
		}
		
		if (rotating_direction == 1) {
			byte tmp = wheels.get(rotating_index).remove(7);
			wheels.get(rotating_index).add(0, tmp);
		} else {
			byte tmp = wheels.get(rotating_index).remove(0);
			wheels.get(rotating_index).add(tmp);			
		}
	}

}
