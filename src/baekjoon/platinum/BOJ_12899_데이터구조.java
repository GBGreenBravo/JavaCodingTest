package baekjoon.platinum;

// 20250624
// 33:02
// 1 / 1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12899_데이터구조 {

    static int N;
    static StringBuilder answer = new StringBuilder();

    static int[] arr = new int[4194304];
    static int added_idx = 2097152;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            if (t == 1) {
                add(added_idx - 1 + x);
            } else {
                remove(x);
            }
        }

        System.out.println(answer);

//		int a = 1;
//		for (int i = 0; i < 40; i++) {
//			a += (int) Math.pow(2, i);
//			if (Math.pow(2, i) > 2000000) {
//				System.out.println(a + " " + i);
//				break;
//			}
//		}

    }

    private static void add(int idx) {
        while (idx != 0) {
            arr[idx] += 1;
            idx /= 2;
        }
    }

    private static void remove(int order) {
        int now = 1;
        while (added_idx > now) {
            arr[now] -= 1;
            int left = now * 2;
            int right = now * 2 + 1;
            if (order > arr[left]) {
                order -= arr[left];
                now = right;
            } else {
                now = left;
            }
        }
        arr[now] -= 1;
        answer.append((now - added_idx + 1) + "\n");
    }

}
