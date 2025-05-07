package baekjoon.platinum;

// 20250507
// 1 / 1

import java.util.*;
import java.io.*;

public class BOJ_1305_광고 {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int L = Integer.parseInt(br.readLine());
      String ad = br.readLine();
      
      int[] table = makeTable(L, ad);
      System.out.println(L - table[L - 1]);
  }
  
    private static int[] makeTable(int str_len, String str) {
      int[] table = new int[str_len];
      
      int idx = 0;
      for (int i = 1; i < str_len; i++) {
        while (idx > 0 && str.charAt(idx) != str.charAt(i)) {
          idx = table[idx - 1];
        }
        if (str.charAt(idx) == str.charAt(i)) {
          idx++;
          table[i] = idx;
        }
      }
      
      return table;
    }
}
