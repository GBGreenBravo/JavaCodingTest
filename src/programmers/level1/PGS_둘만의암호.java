package programmers.level1;

import java.util.HashSet;

class PGS_둘만의암호 {
    public String solution(String s, String skip, int index) {
        String answer = "";

        HashSet<Integer> checkSkip = new HashSet<>();
        for (int i = 0; i < skip.length(); i++) {
            checkSkip.add((int) skip.charAt(i));
        }
        for (int i = 0; i < s.length(); i++) {
            int now = (int)s.charAt(i);
            for (int j = 0; j < index; j++) {
                now += 1;
                if (now > 122) {
                    now -= 26;
                }
                if (checkSkip.contains(now)) {
                    j--;
                }
            }
            answer += String.valueOf((char)now);
        }

        return answer;
    }
}