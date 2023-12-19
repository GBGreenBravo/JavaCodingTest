package programmers.level2;

// 반복문을 통해 같은 대진에서 만날 때까지 값을 구하는 것으로 구현했습니다.
// 더 좋은 방식으로는, "min/max로 작은/큰 값 미리 지정하고 들어가기, answer = 0으로 하고 반복문 한번 더 돌아서 종료 조건 a==b로 하기"가 있습니다.

public class PGS_예상대진표 {
    public int solution(int n, int a, int b) {
        int answer = 1;
        while (check(a, b)) {
            a = fight(a);
            b = fight(b);
            answer++;
        }

        return answer;
    }

    public boolean check(int a, int b) {
        if (a / 2 == b / 2 + 1) {
            if (a % 2 + 1 == b % 2){
                return false;
            }
        }
        if (b / 2 == a / 2 + 1) {
            if (b % 2 + 1 == a % 2){
                return false;
            }
        }

        return true;
    }

    public int fight (int i) {
        if (i % 2 == 0) return i / 2;
        if (i % 2 == 1) return i / 2 + 1;

        return -1;
    }
}
