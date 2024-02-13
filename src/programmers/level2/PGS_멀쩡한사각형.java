package programmers.level2;

// 21:42

// x, y 축의 y = (h/w)x 그래프로 이해하고 풀면 됨.
// 각 x 축에서
    // 이전 x의 y값은 내림(before)하고, 현재 x의 y 값은 올림(after)한 다음.
    // after - before을 answer에서 빼주면 됨.

// 자료형에 대한 꼼꼼함이 필요했던 문제.
    // h/w도 그냥 그러면 안 되고, 한 쪽 (double) 해줘야 함!
    // long answer = w * h; 로 해놨어서, w*h가 int 범위를 넘어서는 경우에 answer을 올바르게 선언해주고 있지 않았음.

// 다른 풀이들을 보니, 패턴 찾아서 최대공약수로 푸는 방식도 있음.

public class PGS_멀쩡한사각형 {
    public long solution(int w, int h) {
        long answer = (long)w * h;

        for (long i = 1; i <= w; i++) {
            long before = (long)Math.floor((i - 1) * (long)h/(double)w);
            long after = (long)Math.ceil(i * (long)h/(double)w);

            answer -= after - before;
        }

        return answer;
    }
}
