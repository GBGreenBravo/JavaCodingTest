package programmers.level2;

// 54:26
//
// 모든 문자가 A가 될때까지 합니다.
// check 메서드를 활용해서 A가 아닌 문자에서 좌우 탐색을 합니다.
// check 메서드:
    // 현재 index의 문자가 A가 아니면 N을 기준으로 비교하여 그 차이를 now에 더하고, name에도 A를 반영시킵니다.
    // 그런 다음, 모든 문자가 A라면 멈추고 answer에 최소값을 넣습니다.
    // A가 아닌 문자가 하나롣 있다면, 좌우를 각각 탐색해서 발견한 index에서 다음 check()메서드를 수행합니다.
//
// 저는 처음에, 좌우 탐색 중 가까운 곳에서 먼저 A 아닌 곳을 발견하면 반대편 탐색도 종료했었습니다.
// 어차피 좌우 탐색 각자 수행하고 무조건 한쪽에서 먼저 찾았다고 반대편도 종료해야하는 것은 아니기에, 좌우 각자 종료하는 것으로 로직 변경했습니다.
// 아직 반례를 찾지는 못했으나,
//
// 기존 코드에서 정확도 이슈가 있어서 로직을 재점검하여 수정한 것이 유의했습니다.
// 반례를 찾지 못하더라도, 논리가 정확하고 시간복잡도 이슈가 없으면 코드를 새 논리로 과감히 수정해야 함.

public class PGS_조이스틱 {
    int answer = Integer.MAX_VALUE;
    public int solution(String name) {

        check(name, 0, 0);

        if (answer == Integer.MAX_VALUE) answer = 0;

        return answer;
    }

    public void check(String name, int index, int now) {
        if (name.charAt(index) != 'A') {
            if (name.charAt(index) <= 'N') {
                now += name.charAt(index) - 'A';
            } else {
                now += '[' - name.charAt(index);
            }
            String before = name.substring(0, index);
            String after = name.substring(index + 1, name.length());
            name = before + "A" + after;
        }

        boolean keepGoing = false;
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) != 'A') keepGoing = true;
        }
        if (!keepGoing) {
            answer = Math.min(answer, now);
            return;
        }

        boolean stopLeft = false;
        boolean stopRight = false;
        for (int i = 1; i < name.length(); i++) {
            now++;
            int right = (index + i < name.length()) ? index + i : index + i - name.length();
            int left = (index - i >= 0) ? index - i : index - i + name.length();
            if (name.charAt(right) != 'A' && !stopRight) {
                check(name, right, now);
                stopRight = true;
            }
            if (name.charAt(left) != 'A' && !stopLeft) {
                check(name, left, now);
                stopLeft = true;
            }
            if (stopLeft && stopRight) return;
        }
    }
}

// 정확성 85.2. 4개 틀림.
// "B"와 같은 케이스 반영 못함.
/* import java.util.ArrayList;

class Solution {
    int answer = Integer.MAX_VALUE;
    public int solution(String name) {

        check(name, 0, 0);

        if (answer == Integer.MAX_VALUE) answer = 0;

        return answer;
    }

    public void check(String name, int index, int now) {
        if (name.charAt(index) != 'A') {
            if (name.charAt(index) <= 'N') {
                now += name.charAt(index) - 'A';
            } else {
                now += '[' - name.charAt(index);
            }
            String before = name.substring(0, index);
            String after = name.substring(index + 1, name.length());
            name = before + "A" + after;
        }
        int tempNow = now;
        for (int i = 1; i < name.length(); i++) {
            tempNow++;
            boolean stop = false;
            int right = (index + i < name.length()) ? index + i : index + i - name.length();
            int left = (index - i >= 0) ? index - i : index - i + name.length();
            if (name.charAt(right) != 'A') {
                check(name, right, tempNow);
                stop = true;
            }
            if (name.charAt(left) != 'A') {
                check(name, left, tempNow);
                stop = true;
            }
            if (stop) return;
            if (left == right || left + 1 == right) {
                answer = Math.min(answer, now);
            }
        }
    }
} */

// 정확도 88.9. 3개 틀림.
/* import java.util.ArrayList;

class Solution {
    int answer = Integer.MAX_VALUE;
    public int solution(String name) {

        check(name, 0, 0);

        if (answer == Integer.MAX_VALUE) answer = 0;

        return answer;
    }

    public void check(String name, int index, int now) {
        if (name.charAt(index) != 'A') {
            if (name.charAt(index) <= 'N') {
                now += name.charAt(index) - 'A';
            } else {
                now += '[' - name.charAt(index);
            }
            String before = name.substring(0, index);
            String after = name.substring(index + 1, name.length());
            name = before + "A" + after;
        }

        boolean keepGoing = false;
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) != 'A') keepGoing = true;
        }
        if (!keepGoing) {
            answer = Math.min(answer, now);
            return;
        }

        int tempNow = now;
        for (int i = 1; i < name.length(); i++) {
            tempNow++;
            boolean stop = false;
            int right = (index + i < name.length()) ? index + i : index + i - name.length();
            int left = (index - i >= 0) ? index - i : index - i + name.length();
            if (name.charAt(right) != 'A') {
                check(name, right, tempNow);
                stop = true;
            }
            if (name.charAt(left) != 'A') {
                check(name, left, tempNow);
                stop = true;
            }
            if (stop) return;
        }
    }
} */
