package baekjoon.gold;

// 20250510
// 21:35
// 1 / 1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2357_최솟값과최댓값 {
	
	private static long[] max_arr;
	private static long[] min_arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int k = 0;
		for (int i = 1; i < 21; i++) {
			if (N <= Math.pow(2, i)) {
				k = i;
				break;
			}
		}
		max_arr = new long[(int) Math.pow(2, k + 1)];
		min_arr = new long[(int) Math.pow(2, k + 1)];
		Arrays.fill(max_arr, Long.MIN_VALUE);
		Arrays.fill(min_arr, Long.MAX_VALUE);
		
		for (int i = 0; i < N; i++) {
			long num = Long.parseLong(br.readLine());
			max_arr[i + (int) Math.pow(2, k)] = num;
			min_arr[i + (int) Math.pow(2, k)] = num;
		}
		
		for (int i = N - 1 + (int) Math.pow(2, k); i > 0; i--) {
			int parent = (int) i / 2;
			max_arr[parent] = Math.max(max_arr[parent], max_arr[i]);
			min_arr[parent] = Math.min(min_arr[parent], min_arr[i]);
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) + (int) Math.pow(2, k) - 1;
			int e = Integer.parseInt(st.nextToken()) + (int) Math.pow(2, k) - 1;
			System.out.println(findMin(s, e) + " " + findMax(s, e));
		}
	}
	
	private static long findMin(int s, int e) {
		long min_value = Long.MAX_VALUE;
		
		while (s <= e) {
			if (s % 2 == 1) {
				min_value = Math.min(min_value, min_arr[s]);
			}
			if (e % 2 == 0) {
				min_value = Math.min(min_value, min_arr[e]);
			}
			s = (int) (s + 1) / 2;
			e = (int) (e - 1) / 2;
		}
		
		return min_value;
	}
	
	private static long findMax(int s, int e) {
		long max_value = Long.MIN_VALUE;
		
		while (s <= e) {
			if (s % 2 == 1) {
				max_value = Math.max(max_value, max_arr[s]);
			}
			if (e % 2 == 0) {
				max_value = Math.max(max_value, max_arr[e]);
			}
			s = (int) (s + 1) / 2;
			e = (int) (e - 1) / 2;
		}
		
		return max_value;
	}

}
