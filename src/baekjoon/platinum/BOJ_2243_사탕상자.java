package baekjoon.platinum;

// 20250513
// 25:42
// 1 / 4

// flavor_max를 10**6이 아닌 10**5로 오해해서 1번 틀리고,
// arr을 new int[last_idx + 2]가 아닌 new int[last_idx + 1]로 선언해서 1번 틀리고,
// priority -= arr[left];가 아닌 priority -= left;로 구현해서 1번 틀림.

import java.util.*;
import java.io.*;
import java.lang.*;

public class BOJ_2243_사탕상자 {
  
    static int num = 20;
    static int first_idx = 1048576;
    static int last_idx = 2048575;
    static int[] arr = new int[last_idx + 2];
  
    static int N;
    
    public static void main(String[] args) throws IOException {
      // int flavor_max = 1000000;
      // int num = 1;
      // while (Math.pow(2, num) < flavor_max) num++;
      // int first_idx = (int) Math.pow(2, num);
      // int last_idx = (int) Math.pow(2, num) - 1 + flavor_max;
      // System.out.println(num + " " + first_idx + " " + last_idx);
      
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        if (a == 1) {
          sb.append(popCandy(Integer.parseInt(st.nextToken())) + "\n");
        } else {
          changeAmount(Integer.parseInt(st.nextToken()) + first_idx - 1, Integer.parseInt(st.nextToken()));
        }
      }
      System.out.println(sb);
  }
  
  static int popCandy(int priority) {
    int now = 1;
    while (true) {
      if (first_idx <= now) {
        changeAmount(now, -1);
        return now - first_idx + 1;
      }
      
      int left = now * 2;
      int right = now * 2 + 1;
      if (arr[left] < priority) {
        priority -= arr[left];
        now = right;
      } else {
        now = left;
      }
    }
  }
  
  static void changeAmount(int i, int amount) {
    while (i != 0) {
      arr[i] += amount;
      i = (int) i / 2;
    }
  }
  
}
