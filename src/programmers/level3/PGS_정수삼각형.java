package programmers.level3;

class PGS_정수삼각형 {

    public static void main(String[] args) {
        System.out.println(solution(new int[][] {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}}));
    }

    public static int solution(int[][] arr) {
        for (int i = arr.length-2; i >= 0; i--) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] += Math.max(arr[i+1][j], arr[i+1][j+1]);
            }
        }

        return arr[0][0];
    }

}