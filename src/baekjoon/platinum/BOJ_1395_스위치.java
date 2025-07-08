package baekjoon.platinum;

// 20250708
// 36:17
// 1 / 1

// counts가 세그먼트 트리의 개념이고, arr은 0과 1 값을 갖는 lazy 배열.
// arr[부모노드]에서 1이면 자식노드들 스위치 바꿔줘야(^= 1) 하고, 0이면 그대로 둠.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1395_스위치 {
	
	static int N, M, counts[];
	static byte[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new byte[N * 4];
		counts = new int[N * 4];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			byte oo = Byte.parseByte(st.nextToken());
			int ss = Integer.parseInt(st.nextToken());
			int tt = Integer.parseInt(st.nextToken());
			if (oo == 0) {
				counts[1] = change(ss, tt, 1, 1, N);
			} else {
				sb.append(ask(ss, tt, 1, 1, N) + "\n");
			}
		}
		System.out.println(sb);
	}
	
	private static void propagate(int now_idx, int left, int right) {
		if (arr[now_idx] == 1) {
			arr[now_idx * 2] ^= 1;
			arr[now_idx * 2 + 1] ^= 1;
			
			int mid = (left + right) / 2;
			counts[now_idx * 2] = (mid - left + 1) - counts[now_idx * 2]; 
			counts[now_idx * 2 + 1] = (right - mid) - counts[now_idx * 2 + 1]; 

			arr[now_idx] = 0;
		}
	}
	
	private static int change(int start, int end, int now_idx, int left, int right) {
		if (right < start || end < left) {
			return counts[now_idx];
		}
		if (start <= left && right <= end) {
			arr[now_idx] ^= 1;
			counts[now_idx] = (right - left + 1) - counts[now_idx];
			return counts[now_idx];
		}
		
		propagate(now_idx, left, right);
		int mid = (left + right) / 2;
		counts[now_idx] = change(start, end, now_idx * 2, left, mid) + change(start, end, now_idx * 2 + 1, mid + 1, right);
		return counts[now_idx];
	}

	
	private static int ask(int start, int end, int now_idx, int left, int right) {
		if (right < start || end < left) {
			return 0;
		}
		if (start <= left && right <= end) {
			return counts[now_idx];
		}
		
		propagate(now_idx, left, right);
		int mid = (left + right) / 2;
		return ask(start, end, now_idx * 2, left, mid) + ask(start, end, now_idx * 2 + 1, mid + 1, right);
	}
}
