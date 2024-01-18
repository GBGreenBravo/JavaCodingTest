package baekjoon.silver;

// 09:30

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_쇠막대기 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String arrs = st.nextToken();

        int answer = 0;
        int now = 0;
        char before = '(';

        for (int i = 0; i < arrs.length(); i++) {
            if (arrs.charAt(i) == '(') {
                now++;
            } else if (arrs.charAt(i) == ')' && before == '(') {
                now--;
                answer += now;
            } else {
                now--;
                answer++;
            }

            before = arrs.charAt(i);
        }

        System.out.println(answer);
    }
}
