package baekjoon.platinum;

// 20250507
// 1 / 1

import java.io.*;

public class BOJ_4354_문자열제곱 {
  
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      String now = br.readLine();
      while (!now.equals(".")) {
        int last_table_num = makeTable(now)[now.length() - 1];
        
        if (now.length() % (now.length() - last_table_num) == 0) {
          System.out.println((int) (now.length() / (now.length() - last_table_num)));
        } else {
          System.out.println(1);
        }
        
        now = br.readLine();
      }
    }
    
    private static int[] makeTable(String str) {
      int[] table = new int[str.length()];
      
      int idx = 0;
      for (int i = 1; i < str.length(); i++) {
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
