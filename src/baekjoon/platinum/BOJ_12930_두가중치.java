package baekjoon.platinum;

import java.util.*;
import java.io.*;

public class BOJ_12930_두가중치 {
  
    private static int N;
    private static int[][] connected1, connected2;
  
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      
      connected1 = new int[N][N];
      for (int i = 0; i < N; i++) {
        String tmp = br.readLine();
        for (int j = 0; j < N; j++) {
          if (tmp.charAt(j) == '.') continue;
          connected1[i][j] = Character.getNumericValue(tmp.charAt(j));
        }
      }
      
      connected2 = new int[N][N];
      for (int i = 0; i < N; i++) {
        String tmp = br.readLine();
        for (int j = 0; j < N; j++) {
          if (tmp.charAt(j) == '.') continue;
          connected2[i][j] = Character.getNumericValue(tmp.charAt(j));
        }
      }
      
      System.out.println(dijkstra());
  }
  
  private static int dijkstra() {
    int[][] visited = new int[N][(N + 1) * 9 + 1];
    int v_l = (N + 1) * 9 + 1;
    
    Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1] * o1[2], o2[1] * o2[2]));
    queue.offer(new int[] {0, 0, 0});
    
    while (!queue.isEmpty()) {
      int[] cur = queue.poll();
      int now = cur[0];
      int graph1 = cur[1];
      int graph2 = cur[2];
      
      if (visited[now][graph1] != graph2) continue;
      
      if (now == 1) return graph1 * graph2;

      for (int next = 0; next < N; next++) {
        int cost1 = connected1[now][next];
        if (cost1 == 0) continue;
        int cost2 = connected2[now][next];
        
        int next_graph1 = graph1 + cost1;
        int next_graph2 = graph2 + cost2;
        
        if (visited[next][next_graph1] != 0 && visited[next][next_graph1] <= next_graph2) continue;
        visited[next][next_graph1] = next_graph2;
        queue.offer(new int[] {next, next_graph1, next_graph2});
      }
    }
    
    return -1;
  }
}
