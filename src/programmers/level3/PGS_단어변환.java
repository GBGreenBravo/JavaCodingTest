package programmers.level3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PGS_단어변환 {
    public static void main(String[] args) {
        System.out.println(solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
    }

    public static int solution(String begin, String target, String[] words) {
        int length = begin.length();

        Queue<ArrayList<String>> a = new LinkedList<>();
        ArrayList<String> b = new ArrayList<>();
        b.add(begin);
        a.add(b);

        while (!a.isEmpty()) {
            Queue<ArrayList<String>> aa = new LinkedList<>();
            while (!a.isEmpty()) {
                ArrayList<String> now = a.poll();

                for (int j = 0; j < words.length; j++) {
                    int diff = 0;

                    for (int k = 0; k < length; k++) {
                        if (now.get(now.size() - 1).charAt(k) != words[j].charAt(k)) {
                            diff++;
                        }
                    }

                    if (diff == 1) {
                        if (words[j].equals(target)) {
                            return now.size();
                        }
                        if (!now.contains(words[j])) {
                            ArrayList<String> noww = new ArrayList<>(now);
                            noww.add(words[j]);
                            aa.add(noww);
                        }
                    }

                }
            }
            a = aa;
        }

        return 0;
    }
}
