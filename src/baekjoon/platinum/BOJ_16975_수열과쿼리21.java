package baekjoon.platinum;

// 20250707
// 32:07
// 1 / 1

// 느리게 갱신되는 세그먼트 트리 (Segment Tree with Lazy Propagation)
// 감 잡았으니, 이제 다른 사람들 코드 보면서 최적화하자.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16975_수열과쿼리21 {
	
	static int N, M, start_idx;
	static long[] arr, lazy;
	static int[] starts, ends;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		start_idx = 1;
		while (start_idx < N) {
			start_idx *= 2;
		}
		
		arr = new long[start_idx * 2];
		lazy = new long[start_idx * 2];
		starts = new int[start_idx * 2];
		ends = new int[start_idx * 2];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i + start_idx] = Long.parseLong(st.nextToken());
			starts[i + start_idx] = i + start_idx;
			ends[i + start_idx] = i + start_idx;
		}
		
		for (int i = start_idx - 1; i > 0; i--) {
			arr[i] = arr[i * 2] + arr[i * 2 + 1];
			
			if (starts[i * 2] == 0) {
				continue;
			} else if (starts[i * 2 + 1] == 0) {
				starts[i] = starts[i * 2];
				ends[i] = ends[i * 2];
			} else {
				starts[i] = Math.min(starts[i * 2], starts[i * 2 + 1]);
				ends[i] = Math.max(ends[i * 2], ends[i * 2 + 1]);
			}
		}
		
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			byte flag = Byte.parseByte(st.nextToken());
			if (flag == 1) {
				add_value(1, Integer.parseInt(st.nextToken()) + start_idx - 1, Integer.parseInt(st.nextToken()) + start_idx - 1, Long.parseLong(st.nextToken()));
			} else {
				sb.append(cal_target(1, Integer.parseInt(st.nextToken()) + start_idx - 1) + "\n");
			}
		}
		
		System.out.println(sb);
		
	}
	
	private static void add_value(int idx, int s_idx, int e_idx, long value) {
		if (e_idx < starts[idx] || ends[idx] < s_idx) {
			return;
		}
		
		if (s_idx == starts[idx] && e_idx == ends[idx]) {
			arr[idx] += value * (starts[idx] - ends[idx] + 1);
			lazy[idx] += value;
			return;
		}
		
		if (lazy[idx] != 0) {
			lazy[idx * 2] += lazy[idx];
			lazy[idx * 2 + 1] += lazy[idx];
			arr[idx * 2] += lazy[idx] * (ends[idx * 2] - starts[idx * 2] + 1);
			arr[idx * 2 + 1] += lazy[idx] * (ends[idx * 2 + 1] - starts[idx * 2 + 1] + 1);
			lazy[idx] = 0;
		}

		int mid = ends[idx * 2];
		if (mid < s_idx) {
			add_value(idx * 2 + 1, s_idx, e_idx, value);
		} else if (e_idx <= mid) {
			add_value(idx * 2, s_idx, e_idx, value);
		} else {
			add_value(idx * 2, s_idx, mid, value);
			add_value(idx * 2 + 1, mid + 1, e_idx, value);
		}
		
	}
	
	private static long cal_target(int idx, int target_idx) {
		if (!(starts[idx] <= target_idx && target_idx <= ends[idx])) {
			return 0L;
		}
		
		if (idx == target_idx) {
			return arr[idx];
		}
		
		if (lazy[idx] != 0) {
			lazy[idx * 2] += lazy[idx];
			lazy[idx * 2 + 1] += lazy[idx];
			arr[idx * 2] += lazy[idx] * (ends[idx * 2] - starts[idx * 2] + 1);
			arr[idx * 2 + 1] += lazy[idx] * (ends[idx * 2 + 1] - starts[idx * 2 + 1] + 1);
			lazy[idx] = 0;
		}
		
		int mid = ends[idx * 2];
		if (target_idx <= mid) {
			return cal_target(idx * 2, target_idx);
		} else {
			return cal_target(idx * 2 + 1, target_idx);
		}
	}

}
