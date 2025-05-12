package baekjoon.gold;

// 20250512
// 21:54
// 1 / 1

import java.util.*;
import java.io.*;
import java.lang.*;

public class BOJ_10868_최솟값 {
  
    private static int N, M;
    private static int[] arr;
  
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      int num = 1;
      while (Math.pow(2, num) < N) {
        num++;
      }
      
      int first_idx = (int) Math.pow(2, num);
      int last_idx = (int) Math.pow(2, num) - 1 + N;
      
      arr = new int[last_idx + 1];
      Arrays.fill(arr, Integer.MAX_VALUE);
      for (int i = 0; i < N; i++) {
        arr[first_idx + i] = Integer.parseInt(br.readLine());
      }
      
      for (int i = last_idx; i > 0; i--) {
        int parent = (int) i / 2;
        if (arr[i] < arr[parent]) arr[parent] = arr[i];
      }
      
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()) + first_idx - 1;
        int e = Integer.parseInt(st.nextToken()) + first_idx - 1;
        sb.append(findMin(s, e) + "\n");
      }
      System.out.println(sb);
  }
  
  private static int findMin(int s, int e) {
    int min_value = Integer.MAX_VALUE;
    
    while (s <= e) {
      if (s % 2 == 1) min_value = Math.min(min_value, arr[s]);
      s = (int) (s + 1) / 2;
      
      if (e % 2 == 0) min_value = Math.min(min_value, arr[e]);
      e = (int) (e - 1) / 2;
    }
    
    return min_value;
  }
}
