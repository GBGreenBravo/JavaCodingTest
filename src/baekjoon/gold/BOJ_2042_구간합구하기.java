package baekjoon.gold;

// 20250512
// 19:28
// 1 / 2

// long c = Long.parseLong(st.nextToken());을
// long c = Integer.parseInt(st.nextToken());으로 써서
// 런타임 에러 (NumberFormat) 1번 났음.

import java.util.*;
import java.io.*;
import java.lang.*;

public class BOJ_2042_구간합구하기 {
  
    private static int N, M, K;
    private static long[] arr;
  
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      
      int num = 1;
      while (Math.pow(2, num) < N) num++;
      int first_idx = (int) Math.pow(2, num);
      int last_idx = (int) Math.pow(2, num) - 1 + N;
      arr = new long[last_idx + 1];
      
      for (int i = 0; i < N; i++) {
        arr[i + first_idx] = Long.parseLong(br.readLine());
      }
      for (int i = last_idx; i > 0; i--) {
        arr[(int) i / 2] += arr[i];
      }
      
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < M + K; i++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        long c = Long.parseLong(st.nextToken());
        if (a == 1) {
          changeValue(b + first_idx - 1, c);
        } else {
          sb.append(findAccumulatedSum(b + first_idx - 1, (int) c + first_idx - 1) + "\n");
        }
      }
      System.out.println(sb);
    }
    
    private static void changeValue(int b, long c) {
      long changed = c - arr[b];
      while (b != 0) {
        arr[b] += changed;
        b = (int) b / 2;
      }
    }
  
    private static long findAccumulatedSum(int s, int e) {
      long acc_sum = 0L;
      
      while (s <= e) {
        if (s % 2 == 1) {
          acc_sum += arr[s];
        }
        s = (int) (s + 1) / 2;
        if (e % 2 == 0) {
          acc_sum += arr[e];
        }
        e = (int) (e - 1) / 2;
      }
      
      return acc_sum;
    }
    
}
