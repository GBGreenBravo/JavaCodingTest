package baekjoon.platinum;

// 20250430
// 19:52
// 1 / 1

import java.util.*;
import java.io.*;

public class BOJ_1162_도로포장 {
  
  private static int N, M, K, START, END;
  private static List<List<int[]>> connected = new ArrayList<>();
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    N = Integer.parseInt(st.nextToken());
    START = 1;
    END = N;
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    
    for (int i = 0; i < N + 1; i++) {
      connected.add(new ArrayList<>());
    }
    
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      connected.get(s).add(new int[] {e, c});
      connected.get(e).add(new int[] {s, c});
    }
    
    System.out.println(dijkstra());
  }
  
  private static long dijkstra() {
    long[][] dist_from_start = new long[K + 1][N + 1];
    for (int i = 0; i < K + 1; i++) {
      Arrays.fill(dist_from_start[i], Long.MAX_VALUE);
    }
    dist_from_start[0][START] = 0;
    
    Queue<long[]> queue = new PriorityQueue<>((o1, o2) -> {
      if (Long.compare(o1[0], o2[0]) != 0) return Long.compare(o1[0], o2[0]);
      if (Long.compare(o1[1], o2[1]) != 0) return Long.compare(o1[1], o2[1]);
      return Long.compare(o1[2], o2[2]);
    });
    queue.offer(new long[] {0L, 0L, START});
    
    while (!queue.isEmpty()) {
      long[] costUsedNow = queue.poll();
      long cost = costUsedNow[0];
      int used = (int) costUsedNow[1];
      int now = (int) costUsedNow[2];
      
      if (dist_from_start[used][now] != cost) continue;
      
      if (now == END) {
        return cost;
      }
      
      for (int[] nextNc: connected.get(now)) {
        int next = nextNc[0];
        int next_cost = nextNc[1];
        if (cost + next_cost < dist_from_start[used][next]) {
          dist_from_start[used][next] = cost + next_cost;
          queue.offer(new long[] {cost + next_cost, used, next});
          if (used < K && cost < dist_from_start[used + 1][next]) {
            dist_from_start[used + 1][next] = cost;
            queue.offer(new long[] {cost, used + 1, next});
          }
        }
      }
    }
    
    return -1;
  }
}
