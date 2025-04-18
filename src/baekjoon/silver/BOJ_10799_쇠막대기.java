package baekjoon.silver;

// 09:30

// int now / int answer로 세팅하고,
// 1. 현재 index '(' 인 경우 => 무조건 now++
// 2. 현재 index ')'이고, 이전 index '(' 인 경우 => now--; answer+=now;
// 3. 현재 index ')'이고, 이전 index ')'인 경우 => now--; answer++;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_10799_쇠막대기 {
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
