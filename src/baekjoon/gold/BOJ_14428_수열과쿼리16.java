package baekjoon.gold;

// 20250512
// 38:23
// 1 / 1

import java.util.*;
import java.io.*;
import java.lang.*;

public class BOJ_14428_수열과쿼리16 {
  
    static int N, M;
    static int[] arr, nums;
  
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;

      N = Integer.parseInt(br.readLine());
      nums = new int[N + 1];
      
      st = new StringTokenizer(br.readLine());
      for (int i = 1; i < N + 1; i++) {
        nums[i] = Integer.parseInt(st.nextToken());
      }
      nums[0] = Integer.MAX_VALUE;
      
      int num = 1;
      while (Math.pow(2, num) < N) num++;
      int first_idx = (int) Math.pow(2, num);
      int last_idx = (int) Math.pow(2, num) - 1 + N;
      arr = new int[last_idx + 2];
      for (int i = 0; i < N; i++) {
        arr[i + first_idx] = i + 1;
      }
      for (int i = last_idx; i > 0; i--) {
        int parent = (int) i / 2;
        if (nums[arr[i]] < nums[arr[parent]]) {
          arr[parent] = arr[i];
        } else if (nums[arr[i]] == nums[arr[parent]] && arr[i] < arr[parent]) {
          arr[parent] = arr[i];
        }
      }
      
      StringBuilder sb = new StringBuilder();
      M = Integer.parseInt(br.readLine());
      for (int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        if (a == 1) {
          nums[b] = c;
          changeValue(b + first_idx - 1, c);
        } else {
          sb.append(findMinValueIdx(b + first_idx - 1, c + first_idx - 1) + "\n");
        }
      }
      System.out.println(sb);
    }
    
    static void changeValue(int i, int v) {
      while (i != 1) {
        int parent = (int) i / 2;
        int other = (i % 2 == 1) ? parent * 2 : parent * 2 + 1;
        if (nums[arr[i]] < nums[arr[other]]) 
          arr[parent] = arr[i];
        else if (nums[arr[i]] == nums[arr[other]] && arr[i] < arr[other]) 
          arr[parent] = arr[i];
        else 
          arr[parent] = arr[other];
        i = parent;
      }
    }
  
    static int findMinValueIdx(int s, int e) {
      int min_value_idx = 0;
      
      while (s <= e) {
        if (s % 2 == 1) {
          if (nums[arr[s]] < nums[min_value_idx]) min_value_idx = arr[s];
          else if (nums[arr[s]] == nums[min_value_idx] && arr[s] < min_value_idx) min_value_idx = arr[s];
        }
        s = (int) (s + 1) / 2;
        if (e % 2 == 0) {
          if (nums[arr[e]] < nums[min_value_idx]) min_value_idx = arr[e];
          else if (nums[arr[e]] == nums[min_value_idx] && arr[e] < min_value_idx) min_value_idx = arr[e];
        }
        e = (int) (e - 1) / 2;
      }
      
      return min_value_idx;
    }
  
}
