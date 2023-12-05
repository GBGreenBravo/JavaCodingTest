package baekjoon.gold;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_연구소 {
    static ArrayList<Integer[]> map = new ArrayList<>();
    static int result = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            Integer[] now = new Integer[m];
            for (int j = 0; j < m; j++) {
                now[j] = sc.nextInt();
            }
            map.add(now);
        }

        ArrayList<int[]> zeroList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map.get(i)[j] == 0) {
                    zeroList.add(new int[]{i, j});
                }
            }
        }

        int answer = 0;

        for (int i = 0; i < zeroList.size(); i++) {
            for (int j = 0; j < zeroList.size(); j++) {
                if (i >= j) {
                    continue;
                }
                for (int k = 0; k < zeroList.size(); k++) {
                    if (j >= k) {
                        continue;
                    }

                    answer = Math.max(answer, spread(zeroList.get(i), zeroList.get(j), zeroList.get(k), n, m));

                }
            }
        }
        System.out.println(answer);

    }

    private static int spread(int[] a, int[] b, int[] c, int n, int m) {

        ArrayList<Integer[]> tempMap = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Integer[] tempIntegerArray = new Integer[m];
            for (int j = 0; j < m; j++) {
                tempIntegerArray[j] = map.get(i)[j];
            }
            tempMap.add(tempIntegerArray);
        }

        tempMap.get(a[0])[a[1]] = 1;
        tempMap.get(b[0])[b[1]] = 1;
        tempMap.get(c[0])[c[1]] = 1;

        Queue<int[]> nowVirus = new LinkedList();
        
        for (int i = 0; i < tempMap.size(); i++) {
            for (int j = 0; j < tempMap.get(0).length; j++) {
                if (tempMap.get(i)[j] == 2) {
                    nowVirus.add(new int[]{i, j});
                }
            }
        }

        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{1, -1, 0, 0};
        while (!nowVirus.isEmpty()) {
            Queue tempNowVirus = new LinkedList();

            int nowVirusSize = nowVirus.size();
            for (int i = 0; i < nowVirusSize; i++) {
                int[] now = nowVirus.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = now[0] + dx[j];
                    int ny = now[1] + dy[j];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && tempMap.get(nx)[ny] == 0) {
                        tempMap.get(nx)[ny] = 2;
                        tempNowVirus.add(new int[]{nx, ny});
                    }
                }
            }

            nowVirus.addAll(tempNowVirus);
        }

        int tempResult = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tempMap.get(i)[j] == 0) {
                    tempResult++;
                }
            }
        }

        return Math.max(result, tempResult);
    }
}
