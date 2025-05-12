package baekjoon.gold;

// 20250512
// 39:16
// 1 / 4

// arr을 long[]이 아닌, int[]로 선언해서 틀렸음.
// DIV가 int범위 안이고 어차피 그 mod값들만 들어가니, int[]로 선언해도 괜찮다고 생각했으나,
// value와 arr[s]가 모두 int면, value * arr[s] 값이 int범위를 초과하게 되므로 %연산 전에 값이 틀리게 되는 것.

import java.util.*;
import java.io.*;
import java.lang.*;

public class BOJ_11505_구간곱구하기 {
    
    static int N, M, K;
    static long[] arr;
    static int DIV = 1000000007;
  
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
      arr = new long[(int) Math.pow(2, num + 1)];
      Arrays.fill(arr, 1L);
      
      for (int i = 0; i < N; i++) {
        arr[i + first_idx] = Long.parseLong(br.readLine());
      }
      for (int i = last_idx; i > 1; i--) {
        arr[(int) i / 2] = (arr[(int) i / 2] * arr[i]) % DIV;
      }
      
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < M + K; i++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        if (a == 1) {
          changeValue(b + first_idx - 1, c);
        } else {
          sb.append(findAccMul(b + first_idx - 1, c + first_idx - 1) + "\n");
        }
      }
      System.out.println(sb);
  }
  
  static void changeValue(int b, int c) {
    arr[b] = c;
    while (b != 1) {
      int parent = (int) b / 2;
      int other = (b % 2 == 1) ? parent * 2 : parent * 2 + 1;
      arr[parent] = (arr[b] * arr[other]) % DIV;
      b = (int) b / 2;
    }
  }
  
  static long findAccMul(int s, int e) {
    long value = 1;
    
    while (s <= e) {
      if (s % 2 == 1) {
        value = (value * arr[s]) % DIV;
      }
      s = (int) (s + 1) / 2;
      if (e % 2 == 0) {
        value = (value * arr[e]) % DIV;
      }
      e = (int) (e - 1) / 2;
    }
    
    return value;
  }
  
}
