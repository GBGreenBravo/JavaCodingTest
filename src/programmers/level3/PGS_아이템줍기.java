package programmers.level3;

// 1:25:00
//
// 문제를 2번이나 대충 읽어서 계속 수정했음. 제발 지문 잘 읽자!!!!
// 풀이는 아래와 같음.
// lines에 모든 사각형 변 위의, 점과 점 사이(필연적으로 하나가 .5의 값을 가지는)의 값을 저장함.
// 그리고 '가장 바깥 변이 아닌 변', 즉 '다른 사각형이 겹치는 변 위의 lines'를 삭제함.
// 그러고 나서는, 조건 비교해가며 bfs. 끝.
//
// 다른 풀이를 보니 .5로 비교하지 않고, 모두 *2해서 계산하는 방식도 있었음. 이 방식이 float로 전환 안 해줘도 돼서 코드가 더 깔끔함.
// 그리고 bfs()에서 queue 2개로 안 두고, int[3]으로 하고 마지막에는 count 넣는 방식도 있음!

import java.util.*;

public class PGS_아이템줍기 {
    int[][] gone = new int[52][52];
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        Set<String> lines = new HashSet<>();
        for (int[] rect : rectangle) {
            for (int i = 0; rect[3] - i != rect[1]; i++) {
                float value = rect[3] - i - 0.5f;
                lines.add((float)rect[0] + " " + value);
                lines.add((float)rect[2] + " " + value);
            }
            for (int i = 0; rect[2] - i != rect[0]; i++) {
                float value = rect[2] - i - 0.5f;
                lines.add(value + " " + (float)rect[1]);
                lines.add(value + " " + (float)rect[3]);
            }
        }

        ArrayList<String> removeList = new ArrayList<>();
        for (String line : lines) {
            float tx = Float.valueOf(line.split(" ")[0]);
            float ty = Float.valueOf(line.split(" ")[1]);
            for (int[] rect : rectangle) {
                if (rect[0] < tx && tx < rect[2] && rect[1] < ty && ty < rect[3]) {
                    removeList.add(line);
                }
            }
        }
        for (String rem : removeList) {
            lines.remove(rem);
        }

        int answer = bfs(characterX, characterY, itemX, itemY, lines);;
        return answer;
    }

    private int bfs(int characterX, int characterY, int itemX, int itemY, Set<String> lines) {
        int count = -1;
        boolean find = false;

        Queue<int[]> nows = new LinkedList<>();
        Queue<int[]> nexts = new LinkedList<>();
        nows.add(new int[]{characterX, characterY});
        gone[characterX][characterY] = 1;

        while (!nows.isEmpty() && !find) {
            count++;
            // System.out.println("count: " + count);

            while(!nows.isEmpty()) {
                int[] now = nows.poll();
                // System.out.println("now: " + now[0] + " / " + now[1]);

                int x = now[0];
                int y = now[1];
                gone[x][y] = 1;

                if (x == itemX && y == itemY) {
                    find = true;
                    break;
                }

                int[] dx = new int[]{0, 0, 1, -1};
                float[] fx = new float[]{0, 0, +0.5f, -0.5f};
                int[] dy = new int[]{1, -1, 0, 0};
                float[] fy = new float[]{+0.5f, -0.5f, 0, 0};

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    float nfx = x + fx[i];
                    float nfy = y + fy[i];

                    if (0 < nx && nx < 51 && 0 < ny && ny < 51 && gone[nx][ny] != 1 && lines.contains(nfx + " " + nfy)) {
                        if (nexts.contains(new int[]{nx, ny})) continue;
                        nexts.add(new int[]{nx, ny});
                    }
                }
            }

            nows = new LinkedList<>(nexts);
            nexts = new LinkedList<>();
        }
        return count;
    }
}

// 사각형의 변 위 모든 점 담은 lines를 통해서 이 위에 있는 (nx, ny)인지 검증했으나,
// 1차이인데 같은 사각형 위에 있는 점이 아닌 애들의 존재로 인해 아래 해답은 틀림.
/* class Solution {
    int[][] gone = new int[52][52];
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        Set<String> lines = new HashSet<>();
        for (int[] rect : rectangle) {
            for (int i = 0; rect[3] - i != rect[1] - 1; i++) {
                int value = rect[3] - i;
                lines.add(rect[0] + " " + value);
                lines.add(rect[2] + " " + value);
            }
            for (int i = 0; rect[2] - i != rect[0] - 1; i++) {
                int value = rect[2] - i;
                lines.add(value + " " + rect[1]);
                lines.add(value + " " + rect[3]);
            }
        }

        int answer = bfs(characterX, characterY, itemX, itemY, lines);;
        return answer;
    }

    private int bfs(int characterX, int characterY, int itemX, int itemY, Set<String> lines) {
        int count = -1;
        boolean find = false;

        Queue<int[]> nows = new LinkedList<>();
        Queue<int[]> nexts = new LinkedList<>();
        nows.add(new int[]{characterX, characterY});
        gone[characterX][characterY] = 1;

        while (!nows.isEmpty() && !find) {
            count++;
            System.out.println("count: " + count);

            while(!nows.isEmpty()) {
                int[] now = nows.poll();
                System.out.println("now: " + now[0] + " / " + now[1]);

                int x = now[0];
                int y = now[1];
                gone[x][y] = 1;

                if (x == itemX && y == itemY) {
                    find = true;
                    break;
                }

                int[] dx = new int[]{0, 0, 1, -1};
                int[] dy = new int[]{1, -1, 0, 0};

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (0 < nx && nx < 51 && 0 < ny && ny < 51 && gone[nx][ny] != 1 && lines.contains(nx + " " + ny)) {
                        if (nexts.contains(new int[]{nx, ny})) continue;
                        nexts.add(new int[]{nx, ny});
                    }
                }
            }

            nows = new LinkedList<>(nexts);
            nexts = new LinkedList<>();
        }
        return count;
    }
} */

// 점프하던 이슈 해결했으나, 문제 이해를 잘못하고 있었음.
// 나중에 넣는 사각형 아래는 삭제했어야 했는데 안하고 있었음..
/* class Solution {
    int[][] gone = new int[52][52];
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        Set<String> lines = new HashSet<>();
        for (int[] rect : rectangle) {



            for (int i = 0; rect[3] - i != rect[1]; i++) {
                float value = rect[3] - i - 0.5f;
                lines.add((float)rect[0] + " " + value);
                lines.add((float)rect[2] + " " + value);
            }
            for (int i = 0; rect[2] - i != rect[0]; i++) {
                float value = rect[2] - i - 0.5f;
                lines.add(value + " " + (float)rect[1]);
                lines.add(value + " " + (float)rect[3]);
            }
        }

        int answer = bfs(characterX, characterY, itemX, itemY, lines);;
        return answer;
    }

    private int bfs(int characterX, int characterY, int itemX, int itemY, Set<String> lines) {
        int count = -1;
        boolean find = false;

        Queue<int[]> nows = new LinkedList<>();
        Queue<int[]> nexts = new LinkedList<>();
        nows.add(new int[]{characterX, characterY});
        gone[characterX][characterY] = 1;

        while (!nows.isEmpty() && !find) {
            count++;
            System.out.println("count: " + count);

            while(!nows.isEmpty()) {
                int[] now = nows.poll();
                System.out.println("now: " + now[0] + " / " + now[1]);

                int x = now[0];
                int y = now[1];
                gone[x][y] = 1;

                if (x == itemX && y == itemY) {
                    find = true;
                    break;
                }

                int[] dx = new int[]{0, 0, 1, -1};
                float[] fx = new float[]{0, 0, +0.5f, -0.5f};
                int[] dy = new int[]{1, -1, 0, 0};
                float[] fy = new float[]{+0.5f, -0.5f, 0, 0};

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    float nfx = x + fx[i];
                    float nfy = y + fy[i];

                    if (0 < nx && nx < 51 && 0 < ny && ny < 51 && gone[nx][ny] != 1 && lines.contains(nfx + " " + nfy)) {
                        if (nexts.contains(new int[]{nx, ny})) continue;
                        nexts.add(new int[]{nx, ny});
                    }
                }
            }

            nows = new LinkedList<>(nexts);
            nexts = new LinkedList<>();
        }
        return count;
    }
} */

// 위 문제는 해결했으나,,
// 또 문제 잘못 이해하고 있었음...
// 전체 사각형들의 제일 바깥 라인만 탈 수 있었음...
/* class Solution {
    int[][] gone = new int[52][52];
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        Set<String> lines = new HashSet<>();
        for (int[] rect : rectangle) {

            ArrayList<String> removeList = new ArrayList<>();

            for (String line : lines) {
                float tx = Float.valueOf(line.split(" ")[0]);
                float ty = Float.valueOf(line.split(" ")[1]);
                if (rect[0] < tx && tx < rect[2] && rect[1] < ty && tx < rect[3]) {
                    removeList.add(line);
                }
            }
            for (String rem : removeList) {
                lines.remove(rem);
            }

            for (int i = 0; rect[3] - i != rect[1]; i++) {
                float value = rect[3] - i - 0.5f;
                lines.add((float)rect[0] + " " + value);
                lines.add((float)rect[2] + " " + value);
            }
            for (int i = 0; rect[2] - i != rect[0]; i++) {
                float value = rect[2] - i - 0.5f;
                lines.add(value + " " + (float)rect[1]);
                lines.add(value + " " + (float)rect[3]);
            }
        }

        int answer = bfs(characterX, characterY, itemX, itemY, lines);;
        return answer;
    }

    private int bfs(int characterX, int characterY, int itemX, int itemY, Set<String> lines) {
        int count = -1;
        boolean find = false;

        Queue<int[]> nows = new LinkedList<>();
        Queue<int[]> nexts = new LinkedList<>();
        nows.add(new int[]{characterX, characterY});
        gone[characterX][characterY] = 1;

        while (!nows.isEmpty() && !find) {
            count++;
            System.out.println("count: " + count);

            while(!nows.isEmpty()) {
                int[] now = nows.poll();
                System.out.println("now: " + now[0] + " / " + now[1]);

                int x = now[0];
                int y = now[1];
                gone[x][y] = 1;

                if (x == itemX && y == itemY) {
                    find = true;
                    break;
                }

                int[] dx = new int[]{0, 0, 1, -1};
                float[] fx = new float[]{0, 0, +0.5f, -0.5f};
                int[] dy = new int[]{1, -1, 0, 0};
                float[] fy = new float[]{+0.5f, -0.5f, 0, 0};

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    float nfx = x + fx[i];
                    float nfy = y + fy[i];

                    if (0 < nx && nx < 51 && 0 < ny && ny < 51 && gone[nx][ny] != 1 && lines.contains(nfx + " " + nfy)) {
                        if (nexts.contains(new int[]{nx, ny})) continue;
                        nexts.add(new int[]{nx, ny});
                    }
                }
            }

            nows = new LinkedList<>(nexts);
            nexts = new LinkedList<>();
        }
        return count;
    }
} */