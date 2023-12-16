package programmers.level2;

// solution메서드의 이중for문을 활용해서, 괄호 하나씩 회전하고 올바른지 판단했습니다.
// (,{,[ 의 경우는 각각 a,b,c 를 추가했고,
// ),},] 의 경우는
// now가 비어있으면 "d"를 반환하고,
// now의 끝자리가 짝지어진 괄호가 아니어도 "d"를 반환하고,
// now의 끝자리가 짝지어진 괄호면 now의 끝자리를 빼고 반환하도록 했습니다.
// 그래서 "d"가 반환되면 안쪽 for문을 break하고 다음 괄호 회전을 수행하도록 했습니다.
// 안쪽 for문의 끝까지 체크하고 now가 비어야만 answer++를 수행하도록 했습니다.
// i + j로 괄호회전 계산해도 되지만, Stack 활용해도 좋을 듯 합니다.

public class PGS_괄호회전하기 {
    public int solution(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            String now = "";

            for (int j = 0; j < s.length(); j++) {
                now = renew(s, i + j, now);
                if (now == "d") break;
                if (j == s.length() - 1 && now.isEmpty()) answer++;
            }
        }

        return answer;
    }

    public String renew(String s, int n, String now) {
        if (n >= s.length()) n -= s.length();

        char[] inputChars = {'(', ')', '{', '}', '[', ']'};
        int here = 6;
        for (int i = 0; i < 6; i++) {
            if (s.charAt(n) == inputChars[i]) here = i;
        }
        String[] strings = {"a", "a", "b", "b", "c", "c"};

        if (here % 2 == 0) return now + strings[here];
        if (here % 2 == 1) {
            if (now.isEmpty()) return "d";
            if (now.endsWith(strings[here])) {
                return now.substring(0, now.length() - 1);
            } else {
                return "d";
            }
        }

        return "d";
    }
}

// 아래의 코드는 ({[)}]와 같이 닫는 순서 고려 안한 케이스 반영 못함.
/*public int solution(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            int[] arr = new int[]{0, 0, 0};

            for (int j = 0; j < s.length(); j++) {
                arr = renew(s, i + j, arr);
                if (!check(arr)) break;
                if (j == s.length() - 1) {
                    if (arr[0] != 0 || arr[1] != 0 || arr[2] != 0) {
                        break;
                    }
                    answer++;
                }
            }

        }
        return answer;
    }

    public boolean check(int[] arr) {
        for (int i = 0; i < 3; i++) {
            if (arr[i] < 0) {
                return false;
            }
        }
        return true;
    }

    public int[] renew(String s, int n, int[] arr) {
        if (n >= s.length()) n -= s.length();

        if (s.charAt(n) == '(') arr[0] += 1;
        if (s.charAt(n) == ')') arr[0] -= 1;
        if (s.charAt(n) == '{') arr[1] += 1;
        if (s.charAt(n) == '}') arr[1] -= 1;
        if (s.charAt(n) == '[') arr[2] += 1;
        if (s.charAt(n) == ']') arr[2] -= 1;


        return arr;
    }*/
