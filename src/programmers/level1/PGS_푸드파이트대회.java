package programmers.level1;

public class PGS_푸드파이트대회 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 3, 4, 6}));
    }
    public static String solution(int[] food) {
        String answer = "";

        for (int i = 1; i < food.length; i++) {
            for (int j = 0; j < (int) food[i] / 2; j++) {
                answer += String.valueOf(i);
            }
        }

        int leng = answer.length();

        answer += "0";

        for (int i = leng-1; i >= 0; i--) {
            answer += answer.charAt(i);
        }

        return answer;
    }
}
