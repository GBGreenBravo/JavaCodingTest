package programmers.level2;

// storey가 0이 될 때까지 반복문. <- 결국 카운트 하는 건 0이 아닌 숫자들이 기준이 되기 때문.
// 10으로 나눠떨어지면 continue;
// 10으로 나눈 나머지가 1~4면 그만큼 빼고 10으로 나누고 continue;
// 10으로 나눈 나머지가 6~9면 그만큼 더하고 10으로 나누고 continue;
// 10으로 나눈 나머지가 5면,
// storeyDown(5빼고 10으로 나눈 값)과 storeyUp(5더하고 10으로 나눈 값) 비교.
// storeyUp의 나머지가 1~5면, storeyDown으로 설정하고 continue;
// storeyUp이 0이거나 6~9면, storeyUp으로 설정하고 continue;

public class PGS_마법의엘리베이터 {
    public int solution(int storey) {
        int answer = 0;

        while (storey != 0) {
            if (storey % 10 == 0) {
                storey /= 10;
                continue;
            }
            if (storey % 10 < 5) {
                answer += storey % 10;
                storey -= storey % 10;
                storey /= 10;
                continue;
            } else if (storey % 10 > 5) {
                answer += (10 - storey % 10);
                storey += (10 - storey % 10);
                storey /= 10;
                continue;
            } else {
                answer += 5;
                int storeyUp = (storey + 5) / 10;
                int storeyDown = (storey - 5) / 10;

                if (storeyUp % 10 <= 5 && storeyUp % 10 != 0) {
                    storey = storeyDown;
                    continue;
                } else {
                    storey = storeyUp;
                    continue;
                }
            }
        }

        return answer;
    }
}

// 테스트 13개 중, 1개만 틀림.
// 950의 케이스 고려 못했었음. 1000으로 가는게 이득이지만, 현재 조건에서는 900으로 감.
/* class Solution {
    public int solution(int storey) {
        int answer = 0;

        while (storey != 0) {
            if (storey % 10 == 0) {
                storey /= 10;
                continue;
            }
            if (storey % 10 < 5) {
                answer += storey % 10;
                storey -= storey % 10;
                storey /= 10;
                continue;
            } else if (storey % 10 > 5) {
                answer += (10 - storey % 10);
                storey += (10 - storey % 10);
                storey /= 10;
                continue;
            } else {
                answer += 5;
                int storeyUp = (storey + 5) / 10;
                int storeyDown = (storey - 5) / 10;

                if (storeyUp % 10 <= 5) {
                    storey = storeyDown;
                    continue;
                } else {
                    storey = storeyUp;
                    continue;
                }
            }
        }

        return answer;
    }
} */
