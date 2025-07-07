package baekjoon.platinum;

// 20250707
// 1:42:31
// 1 / 1

// 풀고 보니, 느리게 갱신되는 세그먼트 트리 (Segment Tree with Lazy Propagation)
// 전역변수로 start & end를 관리하다 보니, 구현하면서 헷갈리는 부분이 많았음.
// 인자로 찾는 범위 넘겨주는 풀이가 더 간편할 듯함.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10999_구간합구하기2 {
	
	static long N, M, K;
	static int start_idx;
	static long[] arr, changed;
	static int[] starts, ends;
	static int start, end;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long N = Long.parseLong(st.nextToken());
		long M = Long.parseLong(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		
		start_idx = 1;
		while (start_idx < N) {
			start_idx *= 2;
		}
		arr = new long[start_idx * 2];		
		changed = new long[start_idx * 2];		
		starts = new int[start_idx * 2];
		ends = new int[start_idx * 2];
		
		for (int i = 0; i < N; i++) {
			arr[i + start_idx] = Long.parseLong(br.readLine());
			starts[i + start_idx] = i + start_idx;
			ends[i + start_idx] = i + start_idx;
		}
		for (int i = start_idx - 1; i > 0; i--) {
			arr[i] = arr[i * 2] + arr[i * 2 + 1];
			if (starts[i * 2] == 0) continue;
			if (starts[i * 2 + 1] == 0) {
				starts[i] = starts[i * 2];
				ends[i] = ends[i * 2];
			} else {
	 			starts[i] = Math.min(starts[i * 2],  starts[i * 2 + 1]);
				ends[i] = Math.max(ends[i * 2],  ends[i * 2 + 1]);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int aa = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken()) + start_idx - 1;
			end = Integer.parseInt(st.nextToken()) + start_idx - 1;
			if (aa == 1) {
				add_between(1, Long.parseLong(st.nextToken()));
			} else {
				sb.append(cal_sum_between(1) + "\n");
			}
		}
		
		System.out.println(sb);
	}
	
	private static void add_between(int idx, long value) {
		if (end < starts[idx] || ends[idx] < start) {
			return;
		}
		if (start <= starts[idx] && ends[idx] <= end) {
			arr[idx] += value * (ends[idx] - starts[idx] + 1);
			changed[idx] += value;
		} else if (changed[idx] != 0) {
			arr[idx] += value * (Math.min(ends[idx], end) - Math.max(starts[idx], start) + 1);
			arr[idx * 2] += changed[idx] * (ends[idx * 2] - starts[idx * 2] + 1);
			arr[idx * 2 + 1] += changed[idx] * (ends[idx * 2 + 1] - starts[idx * 2 + 1] + 1);
			changed[idx * 2] += changed[idx];
			changed[idx * 2 + 1] += changed[idx];
			changed[idx] = 0;
			add_between(idx * 2, value);
			add_between(idx * 2 + 1, value);
		} else {
			arr[idx] += value * (Math.min(ends[idx], end) - Math.max(starts[idx], start) + 1);
			add_between(idx * 2, value);
			add_between(idx * 2 + 1, value);
		}
	}

	private static long cal_sum_between(int idx) {
		if (ends[idx] < start || end < starts[idx]) {
			return 0L;
		}
		if (start <= starts[idx] && ends[idx] <= end) {
			return arr[idx];
		} else if (changed[idx] != 0) {
			arr[idx * 2] += changed[idx] * (ends[idx * 2] - starts[idx * 2] + 1);
			arr[idx * 2 + 1] += changed[idx] * (ends[idx * 2 + 1] - starts[idx * 2 + 1] + 1);
			changed[idx * 2] += changed[idx];
			changed[idx * 2 + 1] += changed[idx];
			changed[idx] = 0;
			return cal_sum_between(idx * 2) + cal_sum_between(idx * 2 + 1);
		} else {
			return cal_sum_between(idx * 2) + cal_sum_between(idx * 2 + 1); 
		}
	}
	
}
