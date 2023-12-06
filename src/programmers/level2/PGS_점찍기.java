package programmers.level2;

public class PGS_점찍기 {
    public static void main(String[] args) {
        System.out.println(solution(1, 5));
    }
    public static long solution(int k, int d) {
        long answer = 0;

        for (int i = 0; k * i <= d; i++) {
            int now = i * k;
            answer += ((int) (Math.sqrt(Math.pow(d, 2) - Math.pow(now, 2)))) / k + 1;
        }

        return answer;
    }
}

// 문제를 이해하기 위해 x**2 + y**2 = r**2 바탕으로 원 긋고,
// 원의 방정식과, 피타고라스정리 이용해서 최대 거리 d인 곳의 좌표를 구하면 됨.
// 0부터 시작해서 x축의 k의 배수가 for문으로 돌면서 탐색합니다.
// 반복문 조건 설정에서 k * i <= d 와 같이 해줌으로써, x축 선상의 원까지만 가도록 해줌.
// now를 통해 k의 배수(x축)를 계산하고,
// Math.sqrt(Math.pow(d, 2) - Math.pow(now, 2))는 now(x축)에서 갖는 최대 값을 계산.
// 그리고 이를 k로 나눈 몫에 1을 추가해주면 해당 now에 가능한 y값의 개수 구할 수 있음.