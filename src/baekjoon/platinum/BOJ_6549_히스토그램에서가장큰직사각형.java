package baekjoon.platinum;

// 20250624
// 50:20
// 1 / 1

// 풀이가 바로 떠오르지 않아서 한 25분 고민하다가 떠올리고, Stack으로 풀었음. 
// (분할정복과 세그먼트트리를 활용한 다른 풀이(https://steady-coding.tistory.com/129)도 있음.)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_6549_히스토그램에서가장큰직사각형 {
	
	static int N;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if (N == 0) break;
			
			long max_answer = 0L; 
			
			Stack<int[]> stack = new Stack<>();  // [높이, 시작된idx]
			stack.add(new int[] {Integer.parseInt(st.nextToken()), 0});
			for (int i = 1; i < N; i++) {
				int[] now = new int[] {Integer.parseInt(st.nextToken()), i};
				if (now[0] > stack.peek()[0]) {
					stack.add(now);
				} else if (now[0] == stack.peek()[0]) {
					continue;
				} else {
					int recent_removed_idx = -1; 
					while (!(stack.isEmpty() || (stack.peek()[0] <= now[0]))) {
						int[] removed = stack.pop();
						recent_removed_idx = removed[1];
						max_answer = Math.max(max_answer,(long)removed[0] * (i - removed[1]));
					}
					if (stack.isEmpty() || stack.peek()[0] < now[0]) {
						stack.add(new int[] {now[0], recent_removed_idx});
					}
				}
			}
			// 남아있는 스택 요소들도 계산
			while (!stack.isEmpty()) {
				int[] removed = stack.pop();
				max_answer = Math.max(max_answer, (long)removed[0] * (N - removed[1]));
			}
			
			answer.append(max_answer + "\n");
		}
		
		System.out.println(answer);
	}

}
