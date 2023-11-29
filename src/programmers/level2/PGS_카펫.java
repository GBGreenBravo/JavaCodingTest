package programmers.level2;

class PGS_카펫 {
    public int[] solution(int brown, int yellow) {
        for (int i = yellow; i > 0; i--) {
            if (yellow % i == 0) {
                int rest = yellow / i;
                if (brown == i * 2 + rest * 2 + 4) {
                    return new int[]{i + 2, rest + 2};
                }
            }
        }
        return new int[]{0,0};
    }
}