package baekjoon.gold;

// 20250430
// 23:53
// 1 / 1

import java.util.*;
import java.io.*;

public class BOJ_1916_최소비용구하기 {
  
  private static int N, M, START, END;
  private static List<List<int[]>> connected;
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    
    st = new StringTokenizer(br.readLine());
    M = Integer.parseInt(st.nextToken());
    
    connected = new ArrayList<>();
    for (int i = 0; i < N + 1; i++) {
      connected.add(new ArrayList<>());
    }
    
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      connected.get(s).add(new int[] {e, c});
    }
    
    st = new StringTokenizer(br.readLine());
    START = Integer.parseInt(st.nextToken());
    END = Integer.parseInt(st.nextToken());
    
    System.out.println(dijkstra());
  }
  
  private static int dijkstra() {
    int[] dist_from_start = new int[N + 1];
//    for (int i = 0; i < N + 1; i++) {
//      dist_from_start[i] = Integer.MAX_VALUE;
//    }
    Arrays.fill(dist_from_start, Integer.MAX_VALUE);
    dist_from_start[START] = 0;
    
    Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
      if (Integer.compare(o1[0], o2[0]) != 0) return Integer.compare(o1[0], o2[0]);
      else return Integer.compare(o1[1], o2[1]);
    });
    queue.offer(new int[] {0, START});
    
    while (!queue.isEmpty()) {
      int[] costNow = queue.poll();
      int cost = costNow[0];
      int now = costNow[1];
      
      if (dist_from_start[now] != cost) continue;
      
      if (now == END) {
        return cost;
      }
      
      for (int[] nextNc: connected.get(now)) {
        int next = nextNc[0];
        int next_cost = nextNc[1];
        if (cost + next_cost < dist_from_start[next]) {
          dist_from_start[next] = cost + next_cost;
          queue.offer(new int[] {cost + next_cost, next});
        }
      }
    }
    
    return -1;
  } 
}
