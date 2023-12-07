package programmers.level3;

// queue에 담아서 bfs로 풀어도 정확하지만, 효율성 안됨.
// 문제를 정확히 보면 아래와 오른쪽으로만 가기 때문에, 책 읽는 순서로 풀면 됨.
// 왜인지는 모르겠지만, 중요 포인트는 "마지막에 %1000000007 하는 게 아니라, 중간준간에 %1000000007 해주는 것!!" 왜!?!??

public class PGS_등굣길 {
    public static void main(String[] args) {
        System.out.println(solution(4, 3, new int[][]{{2, 2}}));
    }

    public static int solution(int m, int n, int[][] puddles) {
        int[][] area = new int[n][m];

        area[0][0] = 1;
        for (int[] puddle : puddles) {
            area[puddle[1] - 1][puddle[0] - 1] = -1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (area[i][j] == 0) {
                    if (j - 1 >= 0 && area[i][j - 1] != -1) {
                        area[i][j] += area[i][j - 1];
                    }
                    if (i - 1 >= 0 && area[i - 1][j] != -1) {
                        area[i][j] += area[i - 1][j];
                    }
                    area[i][j] %= 1000000007;
                }
            }
        }

        return area[n - 1][m - 1];
    }

    // ---------------------------------------------------------------------------------------------------------------------------------------------//
    // ---------------------------------------------------------------------------------------------------------------------------------------------//

    // 아래 답변; 정확성 테스트는 다 통과했지만, 효율성 테스트에서 다 탈락. 아마 renew()에서 전체 탐색하는 부분을 Queue로 구현해주면 될듯.
    /*public static void main(String[] args) {
        System.out.println(solution(4, 3, new int[][]{{2, 2}}));
    }

    static int[][] area;
    static int[][] gone;

    public static int solution(int m, int n, int[][] puddles) {
        area = new int[m][n];
        gone = new int[m][n];

        gone[0][0] = 1;
        for (int[] puddle : puddles) {
            area[puddle[0] - 1][puddle[1] - 1] = -1;
        }

        boolean again = true;

        while (again) {
            again = renew(m, n);
        }

        return gone[m - 1][n - 1] % 1000000007;
    }

    public static boolean renew(int m, int n) {
        boolean again = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (gone[i][j] != 0) {
                    continue;
                }

                int now = 0;

                int[] di = {0, 0, 1, -1};
                int[] dj = {1, -1, 0, 0};

                for (int k = 0; k < 4; k++) {
                    int ni = i + di[k];
                    int nj = j + dj[k];

                    if (ni < 0 || ni >= m || nj < 0 || nj >= n || area[ni][nj] == -1) {
                        continue;
                    }

                    now += gone[ni][nj];
                }
                if (now != 0) {
                    gone[i][j] = now;
                    again = true;
                }
            }
        }
        return again;
    }*/

    // ---------------------------------------------------------------------------------------------------------------------------------------------//

    // 이 답변도 정확성은 100인데 효율성 다 틀림. 나름 효율성은 높였다고 생각했는데,, 그래도 시간초과.
    /*public static void main(String[] args) {
        System.out.println(solution(4, 3, new int[][]{{2, 2}}));
    }

    static int[][] area;
    static int[][] gone;

    public static int solution(int m, int n, int[][] puddles) {
        area = new int[m][n];
        gone = new int[m][n];

        gone[0][0] = 1;
        for (int[] puddle : puddles) {
            area[puddle[0] - 1][puddle[1] - 1] = -1;
        }

        ArrayList<Integer[]> arr = new ArrayList<>();
        arr.add(new Integer[]{0, 0});

        while (!arr.isEmpty()) {
            ArrayList<Integer[]> tempArr = new ArrayList<>();
            for (Integer[] nowInteger : arr) {
                tempArr.addAll(renew(nowInteger[0], nowInteger[1], m, n));
            }
            arr = tempArr;
        }

        return gone[m - 1][n - 1] % 1000000007;
    }

    public static ArrayList<Integer[]> renew(int a, int b, int m, int n) {
        ArrayList<Integer[]> renewArr = new ArrayList<>();

        int[] da = {0, 0, 1, -1};
        int[] db = {1, -1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int na = a + da[i];
            int nb = b + db[i];

            if (na < 0 || na >= m || nb < 0 || nb >= n || gone[na][nb] != 0 || area[na][nb] == -1) {
                continue;
            }

            int tempInt = 0;

            for (int j = 0; j < 4; j++) {
                int nna = na + da[j];
                int nnb = nb + db[j];

                if (nna < 0 || nna >= m || nnb < 0 || nnb >= n || gone[nna][nnb] == 0) {
                    continue;
                }
                tempInt += gone[nna][nnb];
            }

            if (tempInt != 0) {
                gone[na][nb] = tempInt;
                renewArr.add(new Integer[]{na, nb});
            }
        }

        return renewArr;
    }*/

    // 아래와 오른쪽으로만 가도록 코드 짜도 틀렸음..
    /*public static void main(String[] args) {
        System.out.println(solution(4, 3, new int[][]{{2, 2}}));
    }

    static int[][] area;
    static int[][] gone;

    public static int solution(int m, int n, int[][] puddles) {
        area = new int[m][n];
        gone = new int[m][n];

        gone[0][0] = 1;
        for (int[] puddle : puddles) {
            area[puddle[0] - 1][puddle[1] - 1] = -1;
        }

        ArrayList<Integer[]> arr = new ArrayList<>();
        arr.add(new Integer[]{0, 0});

        while (!arr.isEmpty()) {
            ArrayList<Integer[]> tempArr = new ArrayList<>();
            for (Integer[] nowInteger : arr) {
                tempArr.addAll(renew(nowInteger[0], nowInteger[1], m, n));
            }
            arr = tempArr;
        }

        return gone[m - 1][n - 1] % 1000000007;
    }

    public static ArrayList<Integer[]> renew(int a, int b, int m, int n) {
        ArrayList<Integer[]> renewArr = new ArrayList<>();

        int[] da = {0, 1, 0, -1};
        int[] db = {1, 0, -1, 0};

        for (int i = 0; i < 2; i++) {
            int na = a + da[i];
            int nb = b + db[i];

            if (na < 0 || na >= m || nb < 0 || nb >= n || gone[na][nb] != 0 || area[na][nb] == -1) {
                continue;
            }

            int tempInt = 0;

            for (int j = 2; j < 4; j++) {
                int nna = na + da[j];
                int nnb = nb + db[j];

                if (nna < 0 || nna >= m || nnb < 0 || nnb >= n || gone[nna][nnb] == 0) {
                    continue;
                }
                tempInt += gone[nna][nnb];
            }

            if (tempInt != 0) {
                gone[na][nb] = tempInt;
                renewArr.add(new Integer[]{na, nb});
            }
        }

        return renewArr;
    }*/

    // 이 방법도 획기적으로 효율적이게 됐다고 생각했는데 효율성 다 틀림..
    /*public static void main(String[] args) {
        System.out.println(solution(4, 3, new int[][]{{2, 2}}));
    }

    public static int solution(int m, int n, int[][] puddles) {
        int[][] area = new int[n][m];

        area[0][0] = 1;
        for (int[] puddle : puddles) {
            area[puddle[1] - 1][puddle[0] - 1] = -1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (area[i][j] == -1) {
                    continue;
                }
                if (j - 1 >= 0 && area[i][j - 1] != -1) {
                    area[i][j] += area[i][j - 1];
                }
                if (i - 1 >= 0 && area[i - 1][j] != -1) {
                    area[i][j] += area[i - 1][j];
                }
            }
        }

        return area[n - 1][m - 1] % 1000000007;
    }*/
}
