package programmers.level2;

/*
1시간 넘게 풀다가 답안 참고함.

- 처음에 좌측상단을 기준으로 계산하는 방식으로 구현해서, 매번 새로이 점검해야 했기에 시간초과가 났습니다.
- DP를 활용하여, 우측하단을 꼭짓점으로 하는 방식으로 계산하면, 시간초과가 나지 않습니다.
    - 첫 행과 첫 열에 대해서는 그대로.
    - 다음부터는 (i, j-1), (i-1, j), (i-1, j-1)의 최소값을 구한 후,
    여기에 1을 더한 값을 (i, j)에 저장하면 됩니다.
 */

public class PGS_가장큰정사각형찾기 {
    static int[][] arr;
    static int[][] answers;
    public int solution(int[][] board) {
        arr = board;
        answers = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            answers[i] = new int[board[0].length];
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) continue;

                answers[i][j] = 1;
                if (i == 0 || j == 0) continue;

                answers[i][j] = Math.min(answers[i - 1][j], Math.min(answers[i][j - 1], answers[i - 1][j - 1])) + 1;
            }
        }

        int answer = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                answer = Math.max(answer, answers[i][j]);
            }
        }

        return (int) Math.pow(answer, 2);
    }
}

// 정확성 100, 효율성 모두 시간 초과.
/*
class Solution {
    static int[][] arr;
    public int solution(int[][] board) {
        arr = board;

        int answer = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) continue;

                for (int k = 0; k <= Math.min(board.length - i, board[0].length - j) - 1; k++) {
                    if (checkSquare(i, j, i + k, j + k)) {
                        answer = (int) Math.max(answer, Math.pow(k + 1, 2));
                    } else {
                        break;
                    }
                }
            }
        }

        return answer;
    }

    private boolean checkSquare(int row1, int col1, int row2, int col2) {

        for (int i = col1; i <= col2; i++) {
            if (arr[row2][i] == 0) {
                return false;
            }
        }

        for (int i = row1; i <= row2; i++) {
            if (arr[i][col2] == 0) {
                return false;
            }
        }

        return true;
    }
}
 */

// DP 활용하여, 우측하단 꼭짓점 계산.
// 하지만, 얘도 정확성 100 / 효율성 0
// 이미 계산돼 있는 [i, j-1], [i-1, j]를 활용하지 못해서 틀림.
/*
class Solution {
    static int[][] arr;
    static int[][] answers;
    public int solution(int[][] board) {
        arr = board;
        answers = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            answers[i] = new int[board[0].length];
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) continue;

                answers[i][j] = 1;

                if (i == 0 || j == 0) continue;

                int before = answers[i - 1][j - 1];
                if (before == 0) continue;

                answers[i][j] = checkSquare(i, j, before);

            }
        }

        int answer = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                answer = Math.max(answer, answers[i][j]);
            }
        }

        return (int) Math.pow(answer, 2);
    }

    private int checkSquare(int row, int col, int before) {

        int colValue = 0;
        for (int j = col; j >= col - before; j--) {
            if (arr[row][j] == 0) {
                break;
            } else {
                colValue++;
            }
        }

        int rowValue = 0;
        for (int i = row; i >= row - before; i--) {
            if (arr[i][col] == 0) {
                break;
            } else {
                rowValue++;
            }
        }

        return Math.min(colValue, rowValue);
    }
}
 */