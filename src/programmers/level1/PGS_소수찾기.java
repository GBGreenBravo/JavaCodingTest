package programmers.level1;

// 처음으로 intellij 쓰지 않고 푼 문제. 앞으로는 이러한 방식에 익숙해져야 할 것.

// 문제 풀이
// indexArr은 해당 index가 소수면 1로 표시하는 배열.
// 작은 수부터 찾되, 소수 찾으면 그 소수 배수(limit = n)가 index인 곳 값 0으로 만들기.
// 이후 1인곳만 answer++ 해주고, 같은 처리해주면 됨.

public class PGS_소수찾기 {
     public static void main(String[] args) {
         System.out.println(solution(5));
     }

    public static int solution(int n) {
        int answer = 0;

        int[] indexArr = new int[n + 1];
        for (int i = 2; i < indexArr.length; i++) {
            indexArr[i] = 1;
        }

        for (int i = 0; i < indexArr.length; i++) {
            if (indexArr[i] == 0) continue;
            answer++;
            for (int j = 2; j * i < indexArr.length; j++) {
                indexArr[j * i] = 0;
            }
        }

        return answer;
    }
}
