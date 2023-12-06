package programmers.level1;

public class PGS_문자열나누기 {
    public static void main(String[] args) {
        System.out.println(solution("abracadabra"));
    }
    public static int solution(String s) {
        int answer = 0;

        String tempString = "";

        char criteria = 0;
        int num = 0;

        for (char c : s.toCharArray()) {
            if (tempString.isEmpty()) {
                criteria = c;
                num = 0;
            }
            if (c == criteria) {
                num++;
                tempString += String.valueOf(c);
            } else {
                num--;
                tempString += String.valueOf(c);
            }
            if (num == 0) {
                answer++;
                tempString = "";
            }
        }

        if (!tempString.isEmpty()) {
            answer++;
        }

        return answer;
    }
}
