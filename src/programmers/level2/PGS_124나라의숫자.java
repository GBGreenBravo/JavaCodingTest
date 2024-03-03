package programmers.level2;

/*
25:13

- 124나라의 수 규칙: 자리수만 먼저 구한다면, 제일 앞부터 3등분하며 자리를 구하면 됨.
- 그래서 우선 first()함수로 자리수를 먼저 구하고, 세팅
    - 세팅 : 해당 자리수에서 몇번째인지 값을 세팅해야 하므로, (3의 1제곱)부터 (3의 '자리수-1'제곱)까지의 수를 뺀다.
- second()로 실제 값을 맨 앞자리부터 구한다.
    - 구하는 과정에서 자리수만큼의 반복문을 돌며
    max값의 1/3보다 작다면 answer에 "1"추가,
    max값의 1/3을 넘는다면 1/3 max을 빼고 answer에 "2"추가,
    max값의 2/3을 넘는다면 2/3 max을 빼고 answer에 "4"추가.

- 나는 앞자리부터 구했는데, 뒷자리부터 구하는 코드가 대중적이고 더 간결함.
 */

class PGS_124나라의숫자 {
    static String answer = "";

    public String solution(int n) {

        int count = first(n);
        for (int i = 1; i < count; i++) {
            n -= Math.pow(3, i);
        }

        second(count, n);

        return answer;
    }

    private int first(int num) {
        for (int i = 1; i < 100000; i++) {
            if (num <= Math.pow(3, i)) return i;
            else num -= Math.pow(3, i);
        }
        return 0;
    }

    private void second(int count, int num) {
        for (int i = count; i > 0; i--) {
            int max = (int)(Math.pow(3, i));

            if (num <= (max/3)) {
                answer += "1";

            } else if (num <= (max/3*2)) {
                answer += "2";
                num -= (max/3);
            } else {
                answer += "4";
                num -= (max/3*2);
            }
        }
    }
}
